package com.lucas.petshop.controller;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;
import com.lucas.petshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST controller exposing product-related HTTP endpoints under the '/products' path
// Spring will detect this class via component scanning because of @RestController
@RestController
@RequestMapping("/products")
public class ProductController {

    // The service layer that contains business logic for products.
    // Using @Autowired field injection here; constructor injection is recommended for tests and clarity.
    @Autowired
    private ProductService productService;

    // GET /products
    // Returns a list of ProductResponseDTO wrapped in a ResponseEntity with HTTP 200 OK.
    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        // Delegate to service and return result with 200 OK
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /products/{id}
    // Returns a single product by id. @PathVariable binds the path segment to the method param.
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id){
        // Service returns a DTO for the product; ResponseEntity.ok(...) produces 200 OK
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // POST /products
    // Create a new product. @RequestBody binds the JSON payload to ProductRequestDTO.
    // @Valid triggers bean validation annotations declared on ProductRequestDTO.
    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequestDTO dto){
        // Service returns the created entity id; we return 201 Created without a body here.
        Long id = productService.createProduct(dto);
        // Optionally include a Location header with the new resource URL: ResponseEntity.created(URI.create("/products/"+id)).build();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /products/{id}
    // Full update: replace the whole resource with provided data. Returns 204 No Content on success.
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO dto){
        // Delegate update to service layer
        productService.updateProduct(id, dto);
        // 204 No Content indicates success with no response body
        return ResponseEntity.noContent().build();
    }

    // PATCH /products/{id}
    // Partial update: update only provided fields. Using ProductUpdateDTO to represent optional fields.
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO dto){
        // Service applies the patch and returns the updated DTO (if you need it). Here we ignore and return 204.
        ProductResponseDTO update = productService.partialUpdateProduct(id, dto);
        return ResponseEntity.noContent().build();
    }

    // DELETE /products/{id}
    // Delete (soft or hard depending on service) the product and return 204 No Content on success.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
