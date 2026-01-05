package com.example.Prueba_SQL_CRUD.Controller;
import com.example.Prueba_SQL_CRUD.Models.UserModel;
import com.example.Prueba_SQL_CRUD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
        Optional<UserModel> userModel = userService.getUserById(id);

        return userModel.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
        try{
            UserModel newUserModel = userService.createUser(userModel);
            return ResponseEntity.ok(newUserModel);
        }catch (RuntimeException ERROR){
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException ERROR){
            return  ResponseEntity.notFound().build();
        }
    }

}
