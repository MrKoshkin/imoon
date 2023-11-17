package imoon.config;

import imoon.services.user.AdminInitializer;
import imoon.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private UserService userService;
    private JwtRequestFilter jwtRequestFilter;

//    private PasswordEncoder passwordEncoder;

//    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Здесь должен быть ваш секретный ключ

    // Настройка аутентификации. Указываем, как Spring Security будет проверять учетные записи пользователей
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider);
//    }

    @Lazy
    @Autowired
    public SecurityConfig(UserService userService, JwtRequestFilter jwtRequestFilter) {
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
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
                                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                )
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .formLogin(withDefaults()) // Использует настройки по умолчанию для формы входа
                .logout(logout -> logout.logoutUrl("/logout").permitAll()); // Указываем URL для выхода и разрешаем всем пользователям использовать его

//        // Создаем JwtAuthenticationFilter с передачей userDetailsService и secretKey
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(userDetailsService, secretKey);
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    // Bean для кодирования паролей пользователей
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
