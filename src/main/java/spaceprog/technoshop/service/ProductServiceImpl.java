package spaceprog.technoshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spaceprog.technoshop.exception.NotFoundProductException;
import spaceprog.technoshop.exception.UserNotFoundException;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.model.ProductPhoto;
import spaceprog.technoshop.payload.request.createRequest.CreateProductRequest;
import spaceprog.technoshop.payload.request.updateRequest.UpdateProductRequest;
import spaceprog.technoshop.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(NotFoundProductException::new);
    }

    @Override
    public Product createProduct(CreateProductRequest newProduct) throws IOException {
        Product product = new Product(newProduct);
        Set<ProductPhoto> productPhotoSet = new HashSet<>();
        if (!newProduct.getFiles().isEmpty()) {
            for (MultipartFile file : newProduct.getFiles()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uploadPath + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath+"/"+resultFilename));

                ProductPhoto productPhoto = new ProductPhoto(resultFilename, product);
                productPhotoSet.add(productPhoto);
            }
        }
        return saveProduct(product);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(UpdateProductRequest newProduct, Long id) throws UserNotFoundException {
        Product product = findById(id);
        //TODO
        return null;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAllProducts(pageable);
    }
}
