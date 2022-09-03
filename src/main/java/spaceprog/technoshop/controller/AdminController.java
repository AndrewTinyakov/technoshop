package spaceprog.technoshop.controller;

import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.payload.request.createRequest.CreateProductRequest;
import spaceprog.technoshop.payload.request.updateRequest.UpdateProductRequest;

import java.io.IOException;

public interface AdminController {


    Product updateProduct(UpdateProductRequest newProduct, Long id);

    void deleteProductById(Long id);

    Product createProduct(CreateProductRequest newProduct) throws IOException;


}
