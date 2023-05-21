package restfulwebservicesdemo;

import org.springframework.stereotype.Component;
import restfulwebservicesdemo.Domain.User;

import java.util.ArrayList;
import java.util.List;

//let spring manage dependency for this class
@Component
public class UserDAO {
    //save user
    //get user

    // static list of users
    private List<User> users = new ArrayList<>();

    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    // get all users
    public List<User> getUsers() {
        return users;
    }

    // get user
    public User getUser(String id) {
        return users
                .stream()
                .filter(user -> user.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteUserById(String id) {
        Boolean flag;
        flag = users.removeIf(user -> user.getId().toString().equals(id));
        return flag;
    }
}
