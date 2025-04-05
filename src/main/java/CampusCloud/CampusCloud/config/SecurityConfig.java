package CampusCloud.CampusCloud.config;

import CampusCloud.CampusCloud.model.Usuario;
import CampusCloud.CampusCloud.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public SecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario u = usuarioRepository.findByEmail(username);
            if (u == null) throw new UsernameNotFoundException("Usuario no encontrado");
            String rol = u.getRol().toUpperCase();
            return User.builder()
                       .username(u.getEmail())
                       .password(u.getContraseña())
                       .roles(rol)
                       .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
            .anyRequest().authenticated()
          )
          .formLogin(form -> form
            .loginPage("/login")
            .successHandler(authenticationSuccessHandler())
            .failureUrl("/login?error=true")
          )
          .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
          );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req,
                                                HttpServletResponse res,
                                                Authentication auth)
                                                throws IOException, ServletException {
                String role = auth.getAuthorities().iterator().next().getAuthority();
                if ("ROLE_ESTUDIANTE".equals(role)) {
                    res.sendRedirect("/dashboard/estudiante");
                } else if ("ROLE_DOCENTE".equals(role)) {
                    res.sendRedirect("/dashboard/docente");
                } else {
                    res.sendRedirect("/dashboard");
                }
            }
        };
    }
}
