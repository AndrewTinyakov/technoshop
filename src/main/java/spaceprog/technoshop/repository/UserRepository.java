package spaceprog.technoshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spaceprog.technoshop.model.Product;
import spaceprog.technoshop.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query(value = "select * from products left join cart on user_id=:userId", nativeQuery = true)
    Page<Product> findUserCart(@Param("userId") Long id, Pageable pageable);

//    Boolean existsByEmail(String email);
}
