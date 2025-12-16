package com.lucas.petshop.controller;

import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.dto.ProductOrderUpdateDTO;
import com.lucas.petshop.service.ProductOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST controller responsible for handling HTTP requests related to ProductOrder resources
// All endpoints are rooted under the /product-order path
@RestController
@RequestMapping("/product-order")
public class ProductOrderController {

    // Service layer dependency. Spring will inject an implementation at runtime.
    // Field injection is used here for brevity; constructor injection is recommended for tests.
    @Autowired
    private ProductOrderService productOrderService;

    // GET /product-order
    // Returns a list of product-order associations (DTOs). ResponseEntity.ok(...) produces HTTP 200.
    @GetMapping()
    public ResponseEntity<List<ProductOrderResponseDTO>> getAllProductsOrders(){
        // Delegate to service to fetch DTOs and return inside a 200 OK response
        return ResponseEntity.ok(productOrderService.getAllProductsOrders());
    }

    // GET /product-order/{id}
    // Path variable 'id' is bound to the method parameter. Returns a single ProductOrderResponseDTO.
    @GetMapping("/{id}")
    public ResponseEntity<ProductOrderResponseDTO> getById(@PathVariable Long id){
        // If not found, service should throw an exception that can be handled globally (e.g., 404)
        return ResponseEntity.ok(productOrderService.getProductsOrdersById(id));
    }

    // POST /product-order
    // Create a new product-order association. The request body is validated using @Valid.
    // ProductOrderRequestDTO should contain required fields such as productId, orderId, quantity, and unitPrice.
    @PostMapping
    public ResponseEntity<Void> createProductOrder(@Valid @RequestBody ProductOrderRequestDTO dto){
        // Service returns created entity id; here we return 201 Created without a body.
        Long id = productOrderService.createProductOrder(dto);
        // Optionally add a Location header pointing to the newly created resource:
        // URI location = URI.create("/product-order/" + id);
        // return ResponseEntity.created(location).build();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /product-order/{id}
    // Full update of a product-order. Accepts ProductOrderUpdateDTO which should validate incoming fields.
    // Returns 204 No Content on success (no response body).
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductOrder(@PathVariable Long id, @Valid @RequestBody ProductOrderUpdateDTO dto){
        // Delegate update logic to the service layer
        productOrderService.updateProductOrder(id, dto);
        return ResponseEntity.noContent().build();
    }

    // DELETE /product-order/{id}
    // Delete (soft or hard depending on service implementation) the resource.
    // Returns 204 No Content when deletion is successful.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductOrder(@PathVariable Long id){
        productOrderService.deleteProductOrder(id);
        return ResponseEntity.noContent().build();
    }
}
