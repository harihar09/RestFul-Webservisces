package restfulwebservicesdemo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restfulwebservicesdemo.Domain.FilteredUser;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/filteredUsers")
public class JSONIgnorePropertiesController {

    @GetMapping("/")
    public ResponseEntity<List<FilteredUser>> getAllUsers() {
        List<FilteredUser> userList =
                Arrays.asList(new FilteredUser("1", "adam", "zampa", "30"),
                        new FilteredUser("2", "gill", "rastogi", "40"));
        return new ResponseEntity(userList, HttpStatus.OK);
    }
}
