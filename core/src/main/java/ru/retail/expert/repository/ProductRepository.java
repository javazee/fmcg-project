package ru.retail.expert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.retail.expert.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
