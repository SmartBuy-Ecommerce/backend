package com.ShopMinds.service;


import com.ShopMinds.dto.UserDto;
import com.ShopMinds.model.User;
import com.ShopMinds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User signup(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }


//    public User login(UserDto userDto) {
//        User user = userRepository.findByEmail(userDto.getEmail())
//                .orElseThrow(()-> new RuntimeException("User not found"));
//    }
}
