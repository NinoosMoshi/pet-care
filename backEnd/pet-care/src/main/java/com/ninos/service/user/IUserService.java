package com.ninos.service.user;

import com.ninos.dto.UserDTO;
import com.ninos.model.User;
import com.ninos.request.RegistrationRequest;
import com.ninos.request.UserUpdateRequest;

import java.util.List;

public interface IUserService {

    User register(RegistrationRequest request);

    User update(Long userId, UserUpdateRequest request);

    void delete(Long userId);

    List<UserDTO> getAllUsers();
}
