package com.foodrush.mobile_api.service.impl;

import com.foodrush.mobile_api.dto.request.FoodAddonRequest;
import com.foodrush.mobile_api.dto.response.AddonDto;
import com.foodrush.mobile_api.dto.response.CartResponse;
import com.foodrush.mobile_api.entity.*;
import com.foodrush.mobile_api.exception.AppException;
import com.foodrush.mobile_api.exception.ErrorCode;
import com.foodrush.mobile_api.repository.CartItemRepository;
import com.foodrush.mobile_api.repository.CartRepository;
import com.foodrush.mobile_api.repository.UserRepository;
import com.foodrush.mobile_api.service.CartService;
import com.foodrush.mobile_api.service.FoodService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    ModelMapper modelMapper;
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
    FoodService foodService;
    UserRepository userRepository;

    @Override
    public void addToCart(FoodAddonRequest request, Long id) {
        User user = new User();
        user.setId(id);
        Food food = new Food();
        food.setId(request.getFood_id());

        if (cartRepository.findByUserAndFood(user, food).isPresent()) {
            Cart cart = cartRepository.findByUserAndFood(user, food).get();
            cart.setFood_quantity(cart.getFood_quantity() + request.getFood_quantity());
            cartRepository.save(cart);
            request.getAddon().forEach((e) -> {
                AddOn addOn = new AddOn();
                addOn.setId(e.getAddon_id());
                if (cartItemRepository.findByCartAndAddon(cart, addOn).isPresent()) {
                    CartItem cartItem = cartItemRepository.findByCartAndAddon(cart, addOn).get();
                    cartItem.setAddon_quantity(cartItem.getAddon_quantity() + e.getAddon_quantity());
                    cartItemRepository.save(cartItem);
                } else {
                    CartItem cartItem = new CartItem();
                    cartItem.setAddon(addOn);
                    cartItem.setCart(cart);
                    cartItem.setAddon_quantity(e.getAddon_quantity());
                    cartItemRepository.save(cartItem);
                }
            });

        } else {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setFood(food);
            cart.setFood_quantity(request.getFood_quantity());
            if (!request.getAddon().isEmpty()){
                List<CartItem> list = new ArrayList<>();
                list = new ArrayList<>(request.getAddon().stream().map((e) -> {
                    AddOn addOn = new AddOn();
                    addOn.setId(e.getAddon_id());
                    CartItem cartItem = new CartItem();
                    cartItem.setAddon(addOn);
                    cartItem.setAddon_quantity(e.getAddon_quantity());
                    cartItem.setCart(cart);
                    cartItemRepository.save(cartItem);
                    return cartItem;
                }).toList()) ;
                cart.setCartItems(list);
            }
            cartRepository.save(cart);
        }
    }

    @Override
    public CartResponse getCart(Long id) {
        User user = new User();
        user.setId(id);
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        CartResponse response = new CartResponse();
        response.setCart_id(cart.getId());
        response.setFood(foodService.getFood(cart.getFood().getId()));
        response.setFood_quantity(cart.getFood_quantity());
        response.setAddonList(cart.getCartItems().stream().map((addon) -> {
            AddonDto dto = new AddonDto();
            dto.setName(addon.getAddon().getName());
            dto.setQuantity(addon.getAddon_quantity());
            dto.setPrice(addon.getAddon().getPrice());
            return dto;
        }).toList());
        return response;
    }

    @Override
    public void deleteCart(Long cart_id) {
        if (cartRepository.findById(cart_id).isPresent()){
            Cart cart = cartRepository.findById(cart_id).get();
            cartRepository.delete(cart);
        }

    }
}
