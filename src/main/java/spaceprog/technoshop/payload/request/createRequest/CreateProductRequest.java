package spaceprog.technoshop.payload.request.createRequest;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class CreateProductRequest {

    private String productName;

    private String productInfo;

    private Set<MultipartFile> files;

}
