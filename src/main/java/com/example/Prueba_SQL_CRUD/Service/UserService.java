package com.example.Prueba_SQL_CRUD.Service;

import com.example.Prueba_SQL_CRUD.Models.UserModel;
import com.example.Prueba_SQL_CRUD.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel>getUserById(Long id){
        return userRepository.findById(id);
    }

    //guardar
    public UserModel createUser(UserModel userModel){
        if (userRepository.existsByEmail(userModel.getEmail())){
            throw new RuntimeException("El emial ya esta registrado");
        }
        return userRepository.save(userModel);
    }

    //eliminar
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: el usuario no esta registrado");
        }
        userRepository.deleteById(id);
    }
}
