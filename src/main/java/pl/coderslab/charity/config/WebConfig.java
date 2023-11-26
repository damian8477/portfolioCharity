package pl.coderslab.charity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.charity.filter.NameInterceptor;
import pl.coderslab.charity.model.EmailData;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final NameInterceptor nameInterceptor;

    public WebConfig(NameInterceptor nameInterceptor) {
        this.nameInterceptor = nameInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(nameInterceptor);
    }

}