package ma.enset.web.sec.service;

import lombok.AllArgsConstructor;
import ma.enset.web.sec.entities.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserDetailsImp implements UserDetailsService {

    private SecurityServiceI securityServiceI;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=securityServiceI.loadUserByUsername(username);
        //mapping methode 1
      /*  Collection<GrantedAuthority>authorities=new ArrayList<>();
        appUser.getAppRoles().forEach(role->{
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRolename());
            authorities.add(authority);
        });

       */
        //mapping methode 2 api stream
        Collection<GrantedAuthority> authorities1=
                appUser.getAppRoles()
                        .stream()
                        .map(appRole -> new SimpleGrantedAuthority(appRole.getRolename()))
                        .collect(Collectors.toList());


        User user=new User(appUser.getUsername(),appUser.getPassword(),authorities1);
        return  user;
    }
}
