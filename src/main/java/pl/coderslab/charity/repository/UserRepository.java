package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAllByRole(String role);
}
