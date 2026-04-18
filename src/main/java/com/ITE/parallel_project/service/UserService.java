package com.ITE.parallel_project.service;

import com.ITE.parallel_project.entity.Cart;
import com.ITE.parallel_project.entity.User;
import com.ITE.parallel_project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String name){
        User user = new User();
        user.setName(name);
        user.setCart(new Cart());
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
