package spaceprog.technoshop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "photos")
public class ProductPhoto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String filename;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductPhoto(String filename, Product product) {
        this.filename = filename;
        this.product = product;
    }
}
