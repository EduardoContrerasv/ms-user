package cl.duoc.ms_user.repository;

import cl.duoc.ms_user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByAccountStatus(String accountStatus);
}
