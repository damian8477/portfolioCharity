package pl.coderslab.charity.service_interface;

import pl.coderslab.charity.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void deleteById(Long userId);

    User getById(Long userId);

    User getByEmail(String email);

    List<User> findAllByRole(String role);

    boolean existsByEmail(String email);
}
