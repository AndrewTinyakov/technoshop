package spaceprog.technoshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import spaceprog.technoshop.payload.request.createRequest.CreateProductRequest;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String productName;

    private String productInfo;

    private int count;

    @OneToOne()
    @JoinColumn(name = "photo_id")
    private ProductPhoto files;

    public Product(CreateProductRequest newProduct) {
        this.productName = newProduct.getProductName();
        this.productInfo = newProduct.getProductInfo();
    }
}
