package spaceprog.technoshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spaceprog.technoshop.exception.UserNotFoundException;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.model.User;
import spaceprog.technoshop.payload.request.updateRequest.UpdateUserRequest;

import java.util.Optional;

public interface UserService {
    User findById(Long id) throws UserNotFoundException;

    void saveUser(User user);

    public void register(User user);

    User updateOwnAccount(UpdateUserRequest newUser) throws UserNotFoundException;

    void deleteById(Long id);

    User getCurrentUser();

    Optional<User> findUserByUsername(String name);

    Boolean existsByUsername(String username);

    Page<Product> findUserCart(Long id, Pageable pageable);

    void deleteOwnAccount();
}
