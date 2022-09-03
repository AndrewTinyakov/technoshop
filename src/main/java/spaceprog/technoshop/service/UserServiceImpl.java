package spaceprog.technoshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.model.User;
import spaceprog.technoshop.payload.request.updateRequest.UpdateUserRequest;
import spaceprog.technoshop.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.error("User with this id doesn't exist");
        } else {
            log.info("User has been found");
        }

        return user;

    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
        log.info("Saved user in the database");
    }

    public void register(User user){
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        userRepository.save(user);
        log.info("Registered user in the database");
    }

    @Override
    public User updateOwnAccount(UpdateUserRequest newUser) {
        User user = this.getCurrentUser();
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());

        userRepository.save((user));
        log.info("Updated user in the database");

        return user;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("User was deleted from the database");
    }

    @Override
    public void deleteOwnAccount() {
        User user = getCurrentUser();
        deleteById(user.getId());
    }

    public Optional<User> findUserByUsername(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        log.info("Found user by username");
        return user;
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = findUserByUsername(auth.getName()).orElse(null);

        log.info("Got current user");
        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Page<Product> findUserCart(Long id, Pageable pageable) {
//        return userRepository.findUserCart(id, pageable);
        return null;
    }
}
