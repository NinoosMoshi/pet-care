package com.ninos.service.user;

import com.ninos.dto.EntityConverter;
import com.ninos.dto.UserDTO;
import com.ninos.exception.ResourceNotFoundException;
import com.ninos.factory.UserFactory;
import com.ninos.model.User;
import com.ninos.repository.UserRepository;
import com.ninos.repository.VeterinarianRepository;
import com.ninos.request.RegistrationRequest;
import com.ninos.request.UserUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final VeterinarianRepository veterinarianRepository;
    private final EntityConverter<User, UserDTO> entityConverter;


    @Override
    public User register(RegistrationRequest request) {
      return userFactory.createUser(request);
    }

    @Override
    public User update(Long userId, UserUpdateRequest request) {
        User user = findById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSpecialization(request.getSpecialization());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () ->{
                    throw new ResourceNotFoundException("User not found");
                });
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> entityConverter.mapEntityToDto(user, UserDTO.class))
                .collect(Collectors.toList());
    }


    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


}
