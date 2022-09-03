package spaceprog.technoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.payload.request.createRequest.CreateProductRequest;
import spaceprog.technoshop.payload.request.updateRequest.UpdateProductRequest;
import spaceprog.technoshop.service.ProductService;

import java.io.IOException;

@RestController
@RequestMapping("admin/api/product")
@PreAuthorize("hasRole(ROLE_ADMIN)")
public class AdminControllerImpl implements AdminController{

    private final ProductService productService;

    @Autowired
    public AdminControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product createProduct(CreateProductRequest newProduct) throws IOException {
        return productService.createProduct(newProduct);
    }

    @Override
    public Product updateProduct(UpdateProductRequest newProduct, Long id) {
        return productService.updateProduct(newProduct, id);
    }

    @Override
    public void deleteProductById(Long id) {
        productService.deleteById(id);
    }

}
