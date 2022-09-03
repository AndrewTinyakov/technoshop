package spaceprog.technoshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spaceprog.technoshop.exception.UserNotFoundException;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.model.User;
import spaceprog.technoshop.payload.request.createRequest.CreateProductRequest;
import spaceprog.technoshop.payload.request.updateRequest.UpdateProductRequest;
import spaceprog.technoshop.payload.request.updateRequest.UpdateUserRequest;

import java.io.IOException;

public interface ProductService {
    Product findById(Long id) ;

    Product createProduct(CreateProductRequest newProduct) throws IOException;

    Product saveProduct(Product product);

    Product updateProduct(UpdateProductRequest newProduct, Long id) throws UserNotFoundException;

    void deleteById(Long id);

    Page<Product> findAllProducts(Pageable pageable);
}
