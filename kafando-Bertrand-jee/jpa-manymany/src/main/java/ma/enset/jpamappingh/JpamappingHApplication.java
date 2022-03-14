package ma.enset.jpamappingh;

import ma.enset.jpamappingh.entities.Role;
import ma.enset.jpamappingh.entities.User;
import ma.enset.jpamappingh.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpamappingHApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpamappingHApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            User u=new User(); u.setUsername("user1");
            u.setPassword("1234");
            userService.addNewUser(u);
            User u2=new User(); u2.setUsername("user 2");
            u2.setPassword("1234");
            userService.addNewUser(u2);

            Stream.of("STUDENT","USER","ADMIN").forEach(name->{
                Role r1=new Role(); r1.setRoleName(name);
                userService.addNewRole(r1);
            });

            userService.addRoleToUser("user1","STUDENT");
            userService.addRoleToUser("user 2","ADMIN");
            userService.addRoleToUser("user1","USER");

            try{
                User user=userService.authenticate("user1", "1234");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Roles-->");
                user.getRoles().forEach(p->{
                    System.out.println("role: "+p); //attention tostring  il retourne la liste  donc boucle sans fin
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

}
