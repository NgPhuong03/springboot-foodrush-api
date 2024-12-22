package com.foodrush.mobile_api.controller;

import com.foodrush.mobile_api.dto.response.ApiResponse;
import com.foodrush.mobile_api.dto.request.FoodAddonRequest;
import com.foodrush.mobile_api.dto.response.CartResponse;
import com.foodrush.mobile_api.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
public class CartController {
    private CartService cartService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<List<CartResponse>>> getCart(@PathVariable Long id){

        ApiResponse<List<CartResponse>> apiResponse =new ApiResponse<>();
        apiResponse.setMessage("OK");
        apiResponse.setResult(cartService.getCart(id));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("{id}")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody FoodAddonRequest foodAddonRequest, @PathVariable Long id){
        cartService.addToCart(foodAddonRequest,id);
        ApiResponse apiResponse =new ApiResponse();
        apiResponse.setMessage("OK");
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("delete/{user_id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable("user_id") Long cart_id){
        cartService.deleteCart(cart_id);
        ApiResponse apiResponse =new ApiResponse();
        apiResponse.setMessage("OK");
        return ResponseEntity.ok(apiResponse);
    }
}
