package ma.enset.apspringetudiant.security;

import lombok.AllArgsConstructor;
import ma.enset.apspringetudiant.security.service.UserDetailsImp;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsImp userDetails;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll();
        http.authorizeRequests().antMatchers("/webjars/**", "/resources/**", "/css/**").permitAll();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/delete/**","/edit/**",
                "/save/**","/formEtudiant/**","/saveedit/**","/registration/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/index/**","/regle/**").hasAuthority("USER");
        //http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
        http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"));

    }

}
