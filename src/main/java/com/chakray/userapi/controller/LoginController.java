import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chakray.userapi.mapper.UserMapper;
import com.chakray.userapi.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * @param userService
     * @param userMapper
     */
    public LoginController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Sample POST method for login
     * 
     * @param entity Request body
     * @return Echoes the request body
     */
    @PostMapping("path")
    public ResponseEntity<?> postMethodName(@RequestParam String taxId, @RequestParam String userName) throws Exception {
        // TODO implement authentication logic
        var user = userService.getUserLogin(taxId, userName);
        if(user == null){
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

}
