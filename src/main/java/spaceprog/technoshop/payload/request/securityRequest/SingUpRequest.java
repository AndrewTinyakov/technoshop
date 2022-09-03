package spaceprog.technoshop.payload.request.securityRequest;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SingUpRequest {

    @NotBlank
    @Size(min = 2, max = 25,
            message = "login must be at least 2 and no more than 25 characters ")
    private String username;

    @NotBlank(message = "Password must be not blank" )
    @Size(min = 6, max = 25,
            message = "password must be at least 6 and no more than 30 characters ")
    private String password;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 2)
    private String name;

    @NotBlank(message = "Surname must be not blank")
    @Size(min = 2)
    private String surname;
}
