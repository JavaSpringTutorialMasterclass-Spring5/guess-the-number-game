package academy.learnprogramming.config;

import academy.learnprogramming.interceptor.RequestInterceptor;
import academy.learnprogramming.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // == bean methods ==
    @Bean
    public LocaleResolver localeResolver() {

        //zwraca lacale resolvera bazującego na sesji użytkownika
        return new SessionLocaleResolver();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //definicja endpointu dla strony głównej
        registry.addViewController("/").setViewName(ViewNames.HOME);
        registry.addViewController("/home").setViewName(ViewNames.HOME);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //zarejestrowanie naszego interceptora
        registry.addInterceptor(new RequestInterceptor());

        //utworzenie i zarejestrowanie interceptora, który będezie obsługiwał zmianę locali
        //LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        //localeChangeInterceptor.setParamName("lang");
        //registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(new LocaleChangeInterceptor());
    }


}
