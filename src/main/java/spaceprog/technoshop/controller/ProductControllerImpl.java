package spaceprog.technoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductControllerImpl implements ProductController{
    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product getProductById(Long id) {
        return  productService.findById(id);

    }

    @Override
    public Page<Product> getAllProducts(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return productService.findAllProducts(pageable);
    }


}
