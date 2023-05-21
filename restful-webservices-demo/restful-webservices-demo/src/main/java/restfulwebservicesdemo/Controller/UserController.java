package restfulwebservicesdemo.Controller;

import jakarta.validation.Valid;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restfulwebservicesdemo.Domain.User;
import restfulwebservicesdemo.ExceptionHandler.UserNotFound;
import restfulwebservicesdemo.DAO.UserDAO;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/users")
public class UserController {

     @Autowired
    private MessageSource messageSource;
    // inject DAOService dependency
    private UserDAO userDAOService;

    //Constructor to set userDAO dependency
    public UserController(UserDAO userDAOService) {
        this.userDAOService = userDAOService;
    }

    // get all users
    @GetMapping(value = "/",produces = {"application/json"})
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userDAOService.getUsers());
    }

    // get user
    @GetMapping(value = "/{id}",produces = {"application/xml"})
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userDAOService.getUser(id);
        if (user == null)
            throw new UserNotFound("id " + id + " is not found");
        return ResponseEntity.ok(userDAOService.getUser(id));
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userDAOService.saveUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        Boolean isDeletedUser = userDAOService.deleteUserById(id);
        if (isDeletedUser)
            return new ResponseEntity("successfully removed", HttpStatus.OK);
        else
            throw new UserNotFound(id + " was either removed or not found");
    }

    @GetMapping("/i18n")
    public String internationlised(){
        Locale locale = LocaleContextHolder.getLocale();
       return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }
}
