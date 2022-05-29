package ma.enset.bank_api.sec.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ma.enset.bank_api.sec.entities.AppRole;
import ma.enset.bank_api.sec.entities.AppUser;
import ma.enset.bank_api.sec.entities.RoleUserForm;
import ma.enset.bank_api.sec.service.SecurityServiceI;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BankSecurityController {
    private SecurityServiceI securityServiceI;
    @PostAuthorize("hasAuthority('USER')")
    @GetMapping("/users")
    public List<AppUser>appUsers(){
        return  securityServiceI.listUsers();
    }

    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    public AppUser addUser(@RequestBody AppUser appUser){
        return  securityServiceI.saveNewUser(appUser);
    }

    @PostMapping("/role")
    public AppRole addRole(@RequestBody AppRole appRole){
        return  securityServiceI.saveNewRole(appRole);
    }

    @PostMapping("/addRoleToUser")
    public void addRoletoUser(@RequestBody RoleUserForm form){
        securityServiceI.addRoleToUser(form.getUsername(),form.getRoleName());
    }

    @PostMapping("/refreshToken")
    public  void refresh(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        String authorizationToken=request.getHeader("Authorization");
        if(authorizationToken!=null && authorizationToken.startsWith("Bearer")){
            try{
                String jwt=authorizationToken.substring(7);
                Algorithm algorithm=Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier= JWT.require(algorithm).build();
                DecodedJWT decodedJWT=jwtVerifier.verify(jwt);
                String username=decodedJWT.getSubject();

                AppUser user=securityServiceI.loadUserByUsername(username);
                String jwtacessToken= JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles",user.getAppRoles().stream().map(
                                ga->ga.getRolename()
                        ).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> idToken=new HashMap<>();
                idToken.put("acess-token",jwtacessToken);
                idToken.put("refress-token",jwt);

                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
               throw e;
            }
    }
        else {
            throw new RuntimeException("Refresk token required");
        }


}
    }