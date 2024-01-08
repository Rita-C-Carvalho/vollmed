package br.com.vollmed.vollmed.infra.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//A ANOTAÇÃO @CONFIGURATION É PARA DIZER QUE A CLASSE É UMA CLASSE DE CONFIGURAÇÃO DO SPRING
// A ANOTAÇÃO @ENABLESPRINGSUCURITY SERVE PARA INDICAR AO SPRING QUE VAMOS PERSONALIZAR AS CONFIGURAÇÕES DE SEGURANÇA

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

/*OBS: o retorno csrf.disable serve para desabilitar a proteção contra ataques do tipo Cross-site Request Forgery, pois
como vamos utilizar Tokens para autenticação, o próprio Token já é uma proteção contra ataques
 */

/*OBS: O @Bean serve para exportar uma classe para oa Spring, fazendo com que ele consiga carregá-la e realize a sua
* injeção de dependência em outras classes*/