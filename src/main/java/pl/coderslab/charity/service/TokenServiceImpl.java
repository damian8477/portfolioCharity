package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service_interface.TokenService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    @Override
    public void save(Token token) {
        tokenRepository.deleteAllByUserId(token.getUser().getId());
        tokenRepository.save(token);
    }

    @Transactional
    @Override
    public void deleteAllByUserId(Long userId) {
        List<Token> tokens = tokenRepository.findAll();
        tokens.forEach(token -> tokenRepository.deleteById(token.getId()));
    }

    @Override
    public Token getByToken(String token) {
        return tokenRepository.getByToken(token);
    }

    @Override
    public void deleteById(Long id) {
        tokenRepository.deleteById(id);
    }
}
