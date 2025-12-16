package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.service.ProductAnimalTypeEnum;
import com.lucas.petshop.service.ProductTypeEnum;

/**
 * Mapper for converting between Product entities and Product DTOs.
 *
 * <p>This class keeps mapping logic in one place so controllers and services can
 * work with simple data carriers (DTOs). The methods here convert field-by-field
 * between the entity and DTO types.</p>
 *
 * Notes and guidance:
 * - Pay attention to enum fields (ProductTypeEnum / ProductAnimalTypeEnum): the
 *   request/response DTO shapes may use Strings or enums. If DTOs use Strings,
 *   convert enums to Strings in `toDTO` (e.g. `product.getType().name()`), and
 *   parse Strings into enums in `toEntity` (case-insensitive matching with clear
 *   error messages). If DTOs already use enum types, pass enums directly.
 * - Be null-safe: check for null before dereferencing enum fields to avoid
 *   NullPointerException when converting to names.
 * - Keep mapping simple and deterministic. Complex transformation/validation
 *   belongs to service/validator layers, not here.
 */
public class ProductMapper {

    /**
     * Convert Product entity to ProductResponseDTO.
     *
     * Important considerations:
     * - If `ProductResponseDTO` fields are Strings (for type/animalType), use
     *   `product.getType() != null ? product.getType().name() : null` and similar
     *   for animalType to return the enum name safely.
     * - If DTO uses the enum types directly, passing `product.getType()` is fine.
     * - This method intentionally does a shallow copy of fields; it does not
     *   expose the entity reference to callers.
     */
    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getName(),
                // DTO expects enum types, so pass them directly (null-safe)
                product.getType(),
                product.getAnimalType(),
                product.getBrand(),
                product.getDescription(),
                product.getPrice(),
                product.getSizeWeight()
        );
    }

    /**
     * Convert ProductRequestDTO to Product entity.
     *
     * Important considerations:
     * - If `ProductRequestDTO` carries String values for `type`/`animalType`,
     *   parse those Strings into the corresponding enums here (case-insensitive).
     *   Doing so centralizes the parsing logic and keeps services/controllers
     *   simpler.
     * - If `ProductRequestDTO` already exposes enum typed components, set them
     *   directly on the entity.
     * - This method does not persist the entity; persistence belongs in the
     *   service/repository layer.
     */
    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        // DTO exposes enums directly, assign them
        product.setType(dto.type());
        product.setAnimalType(dto.animalType());
        product.setBrand(dto.brand());
        product.setDescription(dto.description());
        product.setStock(dto.stock());
        product.setPrice(dto.price());
        product.setSizeWeight(dto.sizeWeight());

        return product;
    }
}
