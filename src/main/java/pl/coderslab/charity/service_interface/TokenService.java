package pl.coderslab.charity.service_interface;

import pl.coderslab.charity.entity.Token;

public interface TokenService {
    void save(Token token);

    void deleteAllByUserId(Long userId);

    Token getByToken(String token);

    void deleteById(Long id);
}
