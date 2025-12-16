package com.lucas.petshop.dto;

import com.lucas.petshop.model.Product;
import com.lucas.petshop.service.ProductAnimalTypeEnum;
import com.lucas.petshop.service.ProductTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * DTO for partial updates to Product entities.
 *
 * <p>This record carries optional fields that a client may provide when partially updating
 * a Product. Fields are nullable to indicate "not provided"; when non-null the
 * corresponding value will be applied to the target entity by {@link #applyTo(Product)}.</p>
 *
 * <p>Validation annotations on components constrain values when present (e.g. size/positivity).
 * The DTO also contains helper parsing methods to convert String inputs for type/animalType
 * into the corresponding enums.</p>
 */
public record ProductUpdateDTO(

        // Optional product name. If provided, must be between 3 and 80 characters.
        @Size(min = 3, max = 80, message = "PRODUCT NAME SHOULD HAVE BETWEEN 3 TO 80 CHARACTERS")
        String name,

        // Optional product type as String (e.g. "FOOD"). When provided, parsed to ProductTypeEnum.
        @Size(min = 2, max = 60, message = "PRODUCT TYPE SHOULD HAVE BETWEEN 2 TO 60 CHARACTERS")
        String type,

        // Optional animal type as String (e.g. "DOG"). When provided, parsed to ProductAnimalTypeEnum.
        @Size(min = 2, max = 30, message = "PRODUCT ANIMAL TYPE SHOULD HAVE BETWEEN 2 TO 30 CHARACTERS")
        String animalType,

        // Optional brand. If provided, must be between 2 and 30 characters.
        @Size(min = 2, max = 30, message = "PRODUCT BRAND SHOULD HAVE BETWEEN 2 TO 30 CHARACTERS")
        String brand,

        // Optional description. If provided, must be between 10 and 255 characters.
        @Size(min = 10, max = 255, message = "PRODUCT DESCRIPTION SHOULD HAVE BETWEEN 10 - 255 CHARACTERS")
        String description,

        // Optional stock. Must be >= 0 when provided.
        @Min(value = 0, message = "PRODUCT STOCK CANNOT BE NEGATIVE")
        Integer stock,

        // Optional price. Must be positive when provided.
        @Positive(message = "PRODUCT PRICE SHOULD BE HIGHER THAN 0 (ZERO)")
        Double price,

        // Optional size/weight. Must be positive when provided.
        @Positive(message = "PRODUCT SIZE AND WEIGHT SHOULD BE HIGHER THAN 0 (ZERO)")
        Double sizeWeight

){

    /**
     * Apply non-null values from this DTO to the provided Product entity.
     *
     * <p>Only fields that are non-null are applied. This method also parses the
     * String type/animalType into enums using the helper parsers below before setting.
     * Finally, it updates the entity's lastUpdate timestamp.</p>
     */
    public void applyTo(Product product){
        if (name != null) product.setName(name);
        if (type != null) product.setType(parseProductType(type));
        if (animalType != null) product.setAnimalType(parseAnimalType(animalType));
        if (brand != null) product.setBrand(brand);
        if (description != null) product.setDescription(description);
        if (stock != null) product.setStock(stock);
        if (price != null) product.setPrice(price);
        if (sizeWeight != null) product.setSizeWeight(sizeWeight);

        // update timestamp to indicate the entity was modified
        product.setLastUpdate(LocalDateTime.now());

    }

    /**
     * Convert a String into a ProductTypeEnum using case-insensitive matching.
     * If the passed value is null the method returns null.
     * If the value does not match any enum constant an IllegalArgumentException is thrown.
     */
    private static ProductTypeEnum parseProductType(String value) {
        if (value == null) return null;
        for (ProductTypeEnum e : ProductTypeEnum.values()) {
            if (e.name().equalsIgnoreCase(value)) return e;
        }
        throw new IllegalArgumentException("Invalid product type: '" + value + "'");
    }

    /**
     * Convert a String into a ProductAnimalTypeEnum using case-insensitive matching.
     * If the passed value is null the method returns null.
     * If the value does not match any enum constant an IllegalArgumentException is thrown.
     */
    private static ProductAnimalTypeEnum parseAnimalType(String value) {
        if (value == null) return null;
        for (ProductAnimalTypeEnum e : ProductAnimalTypeEnum.values()) {
            if (e.name().equalsIgnoreCase(value)) return e;
        }
        throw new IllegalArgumentException("Invalid animal type: '" + value + "'");
    }
}
