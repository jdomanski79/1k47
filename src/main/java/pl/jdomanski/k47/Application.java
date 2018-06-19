package pl.jdomanski.k47;

import java.util.Locale;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
}
	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("pl", "PL"));
        return slr;
}
	
}
