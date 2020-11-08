package ru.gb.trishkin.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.trishkin.shop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
}
