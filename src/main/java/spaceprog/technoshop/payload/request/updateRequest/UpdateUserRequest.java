package spaceprog.technoshop.payload.request.updateRequest;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "Username must be not blank")
    private String username;

    @NotBlank(message = "Name must be not blank")
    private String name;

    @NotBlank(message = "Surname must be not blank")
    private String surname;


}
