package spaceprog.technoshop.payload.request.securityRequest;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotBlank(message = "The login must not be empty")
    @Size(min = 2, max = 25, message = "Username must be at least2 and no more than 25 characters")
    private String username;

    @NotBlank
    @Size(min = 6, max = 25,
            message = "password must be at least 6 and no more than 30 characters ")
    private String password;

}
