package ru.gb.trishkin.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gb.trishkin.shop.domain.User;
import ru.gb.trishkin.shop.dto.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean save(UserDto userDto);

    List<User> getAll();

    User findByName(String name);

    void updateProfile(UserDto dto);

    void save(User user);

    User createOrSaveUser(User users);

    void deleteUserById(Long id);

    User getById(Long id);
}
