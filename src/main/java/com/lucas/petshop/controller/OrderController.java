package com.lucas.petshop.controller;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST controller that exposes order-related HTTP endpoints under the /orders path
@RestController
@RequestMapping("/orders")
public class OrderController {

    // Spring will inject an implementation of OrderService here
    @Autowired
    private OrderService orderService;

    // GET /orders -> returns a list of orders
    // ResponseEntity wraps the result and allows setting HTTP status/headers if needed
    @GetMapping()
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        // call service to fetch DTOs and return 200 OK with the list in the body
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // GET /orders/{id} -> returns a single order by id
    // @PathVariable binds the {id} segment from the URL to the method parameter
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable Long id){
        // service fetches the order DTO; ok(...) returns 200 with the DTO
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // POST /orders -> create a new order
    // @Valid triggers bean validation on the incoming DTO
    // @RequestBody binds the JSON request body to the OrderRequestDTO parameter
    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequestDTO dto){
        // service creates the order and returns its id (unused here)
        Long id = orderService.createOrder(dto);
        // Return 201 Created with empty body; you could include Location header if desired
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /orders/{id} -> update an existing order
    // uses the same DTO type for simplicity; returns 204 No Content on success
    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequestDTO dto){
        // perform update via service
        orderService.updateOrder(id, dto);
        // 204 No Content indicates the update succeeded but there's no body to return
        return ResponseEntity.noContent().build();
    }

    // DELETE /orders/{id} -> delete (soft or hard depending on service) an order
    // returns 204 No Content when deletion succeeds
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
