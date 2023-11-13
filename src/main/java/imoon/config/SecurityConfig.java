package imoon.config;

import imoon.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
    private UserService userService;
//    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Здесь должен быть ваш секретный ключ

    // Настройка аутентификации. Указываем, как Spring Security будет проверять учетные записи пользователей
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider);
//    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean для кодирования паролей пользователей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Конфигурация безопасности HTTP запросов.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/api/*"))     // Отключаем CSRF защиту для api
                .cors(withDefaults())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Задаем правила доступа к URL. Например, корневой URL должен быть доступен только для пользователей с ролью ADMIN.
                                .requestMatchers("/test").hasRole("ADMIN")
                                // Для всех остальных запросов пользователь должен быть аутентифицирован (залогинен).
//                                .anyRequest().authenticated()
//                                .requestMatchers("/auth").permitAll()
                                .anyRequest().permitAll()
//                                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)   // Отключение сессий
                )
                .formLogin(withDefaults()) // Использует настройки по умолчанию для формы входа
                .logout(logout -> logout.logoutUrl("/logout").permitAll()); // Указываем URL для выхода и разрешаем всем пользователям использовать его


//        // Создаем JwtAuthenticationFilter с передачей userDetailsService и secretKey
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(userDetailsService, secretKey);
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
