package ma.enset.bank_api.sec;

import lombok.AllArgsConstructor;
import ma.enset.bank_api.sec.filters.JWTAuthentificationFilter;
import ma.enset.bank_api.sec.filters.JWTAuthorizationFilter;
import ma.enset.bank_api.sec.service.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsImp userDetails;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //User details Service
        auth.userDetailsService(userDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.headers().frameOptions().disable();
        http.authorizeHttpRequests().antMatchers("/login/**",
                "/swagger-ui**",
                "/swagger-ui/**",
                "/v3/**").permitAll();
      http.addFilter(new JWTAuthentificationFilter(authenticationManagerBean()));
      http.authorizeRequests().antMatchers("/refreshToken/**","/accounts/**","/swagger-ui/**").permitAll();
      http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
