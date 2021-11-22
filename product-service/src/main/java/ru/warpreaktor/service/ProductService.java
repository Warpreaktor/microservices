package ru.warpreaktor.service;


import ru.warpreaktor.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

}
