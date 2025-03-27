package com.example.dio.controller;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import com.example.dio.service.CartItemService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class CartItemController {

    private final CartItemService cartItemService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/tables/{tableId}/cart-items/food-items/{foodItemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> addCart(@PathVariable long tableId,
                                                                       @PathVariable long foodItemId,@RequestParam int quantity){
        return ResponseBuilder.created(cartItemService.CreateCartItem(tableId,foodItemId,quantity),"Cart item created");
    }
    
    @PatchMapping("/cart-items/{cartId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> updateQuantity(@PathVariable long cartId,@RequestParam int quantity){
        return ResponseBuilder.ok(cartItemService.updateQuantity(cartId,quantity),"cart quantity updated");
    }

}
