package spaceprog.technoshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spaceprog.technoshop.security.ERole;
import spaceprog.technoshop.security.Role;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
