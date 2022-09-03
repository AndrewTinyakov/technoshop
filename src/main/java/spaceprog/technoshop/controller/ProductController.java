package spaceprog.technoshop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spaceprog.technoshop.model.Product;

public interface ProductController {

    Product getProductById(Long id);

    Page<Product> getAllProducts(Pageable pageable);






}
