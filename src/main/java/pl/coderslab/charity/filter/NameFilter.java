package pl.coderslab.charity.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.Email;
import pl.coderslab.charity.repository.UserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static java.util.Objects.isNull;

@WebFilter("/*")
@Component
@RequiredArgsConstructor
public class NameFilter implements Filter {
    private final UserRepository userRepository;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.getByEmail(authentication.getName());
            String username = isNull(user) ? "" : user.getName();
            request.setAttribute("name", username);
        }
        request.setAttribute("email", new Email());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
