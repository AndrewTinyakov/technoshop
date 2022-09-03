package spaceprog.technoshop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spaceprog.technoshop.model.Product;

public interface UserController {
    Page<Product> getUserCart(Long id, Pageable pageable);

}
