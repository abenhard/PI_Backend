package br.csi.PI_Backend.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AutenticacaoFilter autenticacaoFilter;
    public SecurityConfig(AutenticacaoFilter filtro){
        this.autenticacaoFilter = filtro;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/cadastrar").permitAll()
                                .requestMatchers(HttpMethod.POST,"/pessoa").hasAnyAuthority("ADMIN", "TECNICO","ATENDENTE")
                                .requestMatchers(HttpMethod.PUT,"/pessoa").hasAnyAuthority("ADMIN", "TECNICO","ATENDENTE")
                                .requestMatchers(HttpMethod.POST,"/pessoa/{cpfOrLogin}").hasAnyAuthority("ADMIN", "TECNICO","ATENDENTE")
                                .requestMatchers(HttpMethod.GET,"/funcionario").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/funcionario/tecnicos").hasAnyAuthority("ADMIN", "ATENDENTE")
                                .requestMatchers(HttpMethod.POST,"/funcionario/cadastrar").permitAll()

                                .requestMatchers(HttpMethod.GET, "/ordem").hasAnyAuthority("ADMIN","ATENDENTE")
                                .requestMatchers(HttpMethod.GET, "/ordem/funcionario/{login}").hasAuthority("TECNICO")
                                .requestMatchers(HttpMethod.GET, "/ordem/{orderId}/images").hasAnyAuthority("ADMIN", "TECNICO","ATENDENTE")
                                .requestMatchers(HttpMethod.POST, "/ordem/cadastroPorAtendente").hasAnyAuthority("ADMIN", "ATENDENTE")
                                .requestMatchers(HttpMethod.POST, "/ordem/cadastroPorTecnico").hasAuthority("TECNICO")

                                .requestMatchers(HttpMethod.GET, "/upload/imagens/{folderAndImageName}").hasAnyAuthority("ADMIN", "TECNICO","ATENDENTE")
                                .requestMatchers(HttpMethod.POST, "/upload/imagens/{folderAndImageName}").hasAnyAuthority("ADMIN", "TECNICO","ATENDENTE")

                                .anyRequest().authenticated())
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

