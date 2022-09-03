package spaceprog.technoshop.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import spaceprog.technoshop.coverter.UserConverter;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.model.User;
import spaceprog.technoshop.payload.request.updateRequest.UpdateUserRequest;
import spaceprog.technoshop.payload.response.UserResponse;
import spaceprog.technoshop.service.UserService;

@RestController
@RequestMapping("api/account")
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserConverter converter;

    public UserControllerImpl(UserService userService, UserConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @Override
    @GetMapping("/cart")
    public Page<Product> getUserCart(@PathVariable Long id, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.findUserCart(id, pageable);
    }

    @GetMapping
    public UserResponse getCurrentUser() {
        User user = userService.getCurrentUser();
        return converter.convertUserToResponse(user);
    }

    @DeleteMapping
    public void deleteAccount() {
        userService.deleteOwnAccount();
    }

    @PutMapping
    public UserResponse updateAccount(UpdateUserRequest newUser) {
        User user = userService.updateOwnAccount(newUser);
        return converter.convertUserToResponse(user);
    }


}
