package com.ithellam.boot.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.ithellam.boot.model.Product;

public interface IProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(final String name);
}
