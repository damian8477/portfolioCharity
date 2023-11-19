package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token getByToken(String token);

    void deleteAllByUserId(Long userId);
}
