package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.request.OrderCreateDto;
import com.foodrush.mobile_api.dto.response.*;
import com.foodrush.mobile_api.entity.*;
import com.foodrush.mobile_api.entity.custom.OrderAddonId;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.repository.OrderFoodAddonRepository;
import com.foodrush.mobile_api.repository.OrderFoodRepository;
import com.foodrush.mobile_api.repository.OrderRepository;
import com.foodrush.mobile_api.repository.ShipperRepository;
import com.foodrush.mobile_api.service.FoodService;
import com.foodrush.mobile_api.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderFoodRepository orderFoodRepository;
    private OrderFoodAddonRepository orderFoodAddonRepository;
    private FoodService foodService;
    private ShipperRepository shipperRepository;

    @Transactional
    @Override
    public void createOrder(OrderCreateDto request) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        User user = new User();
        user.setId(request.getUser_id());
        Address address = new Address();
        address.setId(request.getAddress_id());

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setNote(request.getNote());
        order.setCreated_at(now);
        order.setPaymethod(request.isPaymethod());
        order.setStatus("choxacnhan");
        order.setCost(request.getCost());

        orderRepository.save(order);

        request.getList().forEach((e) -> {
            Food food = new Food();
            food.setId(e.getFood_id());

            OrderFood orderFood = new OrderFood();
            orderFood.setOrder(order);
            orderFood.setFood(food);
            orderFood.setQuantity(e.getFood_quantity());
            orderFoodRepository.save(orderFood);

            e.getAddon().forEach((addOnList -> {
                AddOn addOn = new AddOn();
                addOn.setId(addOnList.getAddon_id());

                OrderFoodAddon orderFoodAddon = new OrderFoodAddon();

                OrderAddonId orderAddonId = new OrderAddonId();
                orderAddonId.setAddonId(addOnList.getAddon_id());
                orderAddonId.setOrderFoodId(orderFood.getId());
                orderFoodAddon.setId(orderAddonId);
                orderFoodAddon.setOrderFood(orderFood);
                orderFoodAddon.setAddOn(addOn);
                orderFoodAddon.setQuantity(addOnList.getAddon_quantity());
                orderFoodAddonRepository.save(orderFoodAddon);
            }));
        });

    }

    @Override
    public OrderDetailsDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        OrderDetailsDto response = new OrderDetailsDto();

        AddressDto addressDto = new AddressDto();
        addressDto.setAddress(order.getAddress().getAddress());
        addressDto.setName(order.getUser().getName());
        addressDto.setPhoneNumber(order.getUser().getPhone_number());
        if (order.getShipper() != null){
            response.setShipper_id(order.getShipper().getId());
        }

        List<OrderFood> orderFood = orderFoodRepository.findByOrder(order).get();
        List<FoodAddOnResponse> foodAddOnResponseList = orderFood.stream().map(item -> {
            FoodAddOnResponse foodAddOnResponse = new FoodAddOnResponse();
            foodAddOnResponse.setFood(foodService.getFood(item.getFood().getId()));
            foodAddOnResponse.setFood_quantity(item.getQuantity());

            List<OrderFoodAddon> orderFoodAddonList = orderFoodAddonRepository.findByOrderFood(item);
            if (!orderFoodAddonList.isEmpty()) {
                List<AddonDto> addonDtoLists = orderFoodAddonList.stream().map((orderFoodAddon -> {
                    AddonDto dto = new AddonDto();
                    dto.setName(orderFoodAddon.getAddOn().getName());
                    dto.setPrice(orderFoodAddon.getAddOn().getPrice());
                    dto.setQuantity(orderFoodAddon.getQuantity());

                    return dto;
                })).toList();
                foodAddOnResponse.setAddon_list(addonDtoLists);
            }

            return foodAddOnResponse;
        }).toList();


        response.setUser_id(order.getUser().getId());
        response.setStatus(order.getStatus());
        response.setCreate_at(order.getCreated_at());
        response.setPaid_at(order.getPaid_at());
        response.setCost(order.getCost());
        response.setNote(order.getNote());
        response.setPaymethod(order.isPaymethod());
        response.setAddress(addressDto);
        response.setList(foodAddOnResponseList);

        return response;
    }

    @Override
    public void chooseShipper(Long id, Long shipper_id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Shipper shipper = shipperRepository.findById(shipper_id)
                        .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        if(order.isPaymethod()){
            order.setPaid_at(  new Timestamp(System.currentTimeMillis()));
        }
        order.setShipper(shipper);
        order.setStatus("dangnau");
        orderRepository.save(order);
    }

    @Override
    public void changeStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        if (status.equals("dahuy") || status.equals("danggiao") ){
            order.setStatus(status);
        } else if (status.equals("dagiao")){
            Shipper shipper = order.getShipper();
            shipper.setLatitude(10.882245102818498);
            shipper.setLongitude(106.78249876263239);
            order.setPaid_at(new Timestamp(System.currentTimeMillis()));
            order.setStatus(status);
        } else {
            order.setStatus("choxacnhan");
        }
        orderRepository.save(order);
    }

    @Override
    public Location getLocation(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Shipper shipper = order.getShipper();
        return new Location(shipper.getLatitude(),shipper.getLongitude());
    }

    @Override
    public Location getUserLocation(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        return new Location(order.getAddress().getLatitude(), order.getAddress().getLongitude());
    }


}
