package br.csi.gg_store.infra.security;

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
                .csrf(crsf-> crsf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth.requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"/cadastrar").permitAll()
                                .requestMatchers(HttpMethod.GET,"/produto").permitAll()
                                .requestMatchers(HttpMethod.GET,"/produto/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET,"/marca").permitAll()
                                .requestMatchers(HttpMethod.GET,"/marca/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET,"/categoria").permitAll()
                                .requestMatchers(HttpMethod.GET,"/categoria/{id}").permitAll()

                                .requestMatchers(HttpMethod.GET,"/usuario/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/usuario").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .requestMatchers(HttpMethod.GET,"/usuario/todosUsuarios").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/usuario").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/usuario").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.GET,"/cidade").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/cidade/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/cidade").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/cidade").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/cidade/{id}").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.GET,"/uf").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/uf/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/uf").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/uf").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/uf/{id}").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.GET,"/endereco").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/endereco/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/endereco").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/endereco").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/endereco/{id}").hasAnyAuthority("ROLE_ADMIN")


                                .requestMatchers(HttpMethod.POST,"/produto").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/produto").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/produto/{id}").hasAnyAuthority("ROLE_ADMIN")


                                .requestMatchers(HttpMethod.POST,"/marca").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/marca").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/marca/{id}").hasAnyAuthority("ROLE_ADMIN")


                                .requestMatchers(HttpMethod.POST,"/endereco").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/categoria").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/categoria/{id}").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.GET,"/carrinho").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET,"/carrinho/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/carrinho/todosCarrinhos").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/carrinho/adicionarProduto").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .requestMatchers(HttpMethod.DELETE,"/carrinho/removerProduto/{id}").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/carrinho/removerProduto").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .requestMatchers(HttpMethod.PUT,"/carrinho").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/carrinho/{id}").hasAnyAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.GET,"/compra").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .requestMatchers(HttpMethod.GET,"/compra/todasCompras").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.POST,"/compra/{idEndereco}").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .requestMatchers(HttpMethod.PUT,"/compra/cancelarCompra/{idCompra}").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                .requestMatchers(HttpMethod.PUT,"/compra//concluirCompra/{idCompra}").hasAnyAuthority("ROLE_ADMIN")

                                .anyRequest().authenticated())
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
