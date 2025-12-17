package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.mapper.ProductOrderMapper;
import com.lucas.petshop.model.Order;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.model.ProductOrder;
import com.lucas.petshop.repository.OrderRepository;
import com.lucas.petshop.repository.ProductOrderRepository;
import com.lucas.petshop.repository.ProductRepository;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<ProductOrderResponseDTO> getProductsByOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("ORDER NOT FOUND"));

        return productOrderRepository.findByOrder(order)
                .stream()
                .map(ProductOrderMapper::toDTO)
                .toList();
    }

    @Override
    public ProductOrderResponseDTO getProductOrderById(Long id) {

        ProductOrder productOrder = productOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PRODUCT ORDER NOT FOUND"));

        return ProductOrderMapper.toDTO(productOrder);
    }

    @Override
    @Transactional
    public void addProductToOrder(Long id, ProductOrderRequestDTO dto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ORDER NOT FOUND"));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrder(order);
        productOrder.setProduct(product);
        productOrder.setQuantity(dto.quantity());
        productOrder.setUnitPrice(product.getPrice());
        productOrder.setCreatedAt(LocalDateTime.now());

        ProductOrder savedProductOrder = productOrderRepository.save(productOrder);

    }

    @Override
    @Transactional
    public void deleteProductOrder(Long orderId, Long productOrderId){
        var startTime = System.currentTimeMillis();

        ProductOrder existing = getProductOrderIfExists(orderId);

        if(Boolean.TRUE.equals(existing.getDeletedProductOrder())){
            throw new RuntimeException("PRODUCT ORDER ALREADY DELETED");
        }

        existing.setDeletedProductOrder(true);
        existing.setLastUpdate(LocalDateTime.now());

        productOrderRepository.save(existing);

        Timer.measure("[DELETE PRODUCT ORDER] - Sucessfully", startTime);

    }


    private ProductOrder getProductOrderIfExists(long productOrderId) {
        return productOrderRepository.findById(productOrderId)
                .orElseThrow(() -> new RuntimeException("PRODUCT ORDER NOT FOUND"));
    }

}
