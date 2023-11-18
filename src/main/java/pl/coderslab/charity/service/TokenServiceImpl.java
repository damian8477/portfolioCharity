package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service_interface.TokenService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    @Override
    public void save(Token token){
        tokenRepository.deleteAllByUserId(token.getUser().getId());
        tokenRepository.save(token);
    }
}
