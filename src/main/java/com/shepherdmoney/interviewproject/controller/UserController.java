package com.shepherdmoney.interviewproject.controller;

import com.shepherdmoney.interviewproject.vo.request.CreateUserPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/user")
    public ResponseEntity<Integer> createUser(@RequestBody CreateUserPayload payload) {
        // TODO: Create an user entity with information given in the payload, store it in the database
        //       and return the id of the user in 200 OK response
        User newUser = new User();
        newUser.setName(payload.getName());
        newUser = userRepository.save(newUser);
        return ResponseEntity.ok(newUser.getId().intValue());
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {
        // TODO: Return 200 OK if a user with the given ID exists, and the deletion is successful
        //       Return 400 Bad Request if a user with the ID does not exist
        //       The response body could be anything you consider appropriate
        Optional<User> user = userRepository.findById((long) userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.ok("user deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("user not found.");
        }
    }
}
