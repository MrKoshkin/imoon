package imoon.config;

import imoon.services.MoonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MoonUserDetailsService userDetailsService;

    // Настройка аутентификации. Указываем, как Spring Security будет проверять учетные записи пользователей
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Bean для кодирования паролей пользователей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Конфигурация безопасности HTTP запросов.
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Задаем правила доступа к URL. Например, корневой URL должен быть доступен только для пользователей с ролью ADMIN.
                                .requestMatchers("/greeting").hasRole("ADMIN")
                                // Для всех остальных запросов пользователь должен быть аутентифицирован (залогинен).
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults()) // Использует настройки по умолчанию для формы входа
                .logout(logout -> logout.logoutUrl("/logout").permitAll()); // Указываем URL для выхода и разрешаем всем пользователям использовать его
//                .csrf().disable(); // Отключение CSRF защиты
        return http.build();
    }
}
