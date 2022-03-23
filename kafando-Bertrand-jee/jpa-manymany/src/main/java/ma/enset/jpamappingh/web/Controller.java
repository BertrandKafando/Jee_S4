package ma.enset.jpamappingh.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import ma.enset.jpamappingh.entities.User;
import ma.enset.jpamappingh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {
    private UserService userService;
    @GetMapping("/users/{username}")
    public User usertest(@PathVariable String username){
        User user=userService.findUserByUsername(username);
        return  user;
    }
    //attention boucle infini
}
