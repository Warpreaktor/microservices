package ru.warpreaktor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.warpreaktor.domain.Product;
import ru.warpreaktor.repository.ProductRepository;
import ru.warpreaktor.service.ProductService;

import java.util.List;
import java.util.Optional;

//Класс в котором описывается вся логика
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product){
        productRepository.save(product);
    }

}
