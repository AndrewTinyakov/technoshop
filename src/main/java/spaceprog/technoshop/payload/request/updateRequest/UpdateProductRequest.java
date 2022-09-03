package spaceprog.technoshop.payload.request.updateRequest;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

@Data
public class UpdateProductRequest {

    private String productName;

    private String productInfo;

    private Set<MultipartFile> files;
}
