package com.lucas.petshop.controller;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.service.OrderService;
import com.lucas.petshop.service.ProductOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping()
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){

        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDTO dto){
        Long id = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequestDTO dto){
        orderService.updateOrder(id, dto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<Void> addProductToOrder(
            @PathVariable Long id,
            @Valid @RequestBody ProductOrderRequestDTO dto
    ) {
        productOrderService.addProductToOrder(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductOrderResponseDTO>> getOrderProducts(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                productOrderService.getProductsByOrder(id)
        );
    }

    @DeleteMapping("/{id}/products/{productOrderId}")
    public ResponseEntity<Void> removeProductFromOrder(
            @PathVariable Long id,
            @PathVariable Long productOrderId
    ) {
        productOrderService.deleteProductOrder(id, productOrderId);
        return ResponseEntity.noContent().build();
    }

}
