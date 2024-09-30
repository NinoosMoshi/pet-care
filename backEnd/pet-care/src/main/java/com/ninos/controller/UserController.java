package com.ninos.controller;

import com.ninos.dto.EntityConverter;
import com.ninos.dto.UserDTO;
import com.ninos.exception.ResourceNotFoundException;
import com.ninos.exception.UserAlreadyExistsException;
import com.ninos.model.User;
import com.ninos.request.RegistrationRequest;
import com.ninos.request.UserUpdateRequest;
import com.ninos.response.ApiResponse;
import com.ninos.service.user.UserService;
import com.ninos.utils.FeedBackMessage;
import com.ninos.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(UrlMapping.USERS)
public class UserController {

    private final UserService userService;
    private final EntityConverter<User, UserDTO> entityConverter;

    @PostMapping(UrlMapping.REGISTER_USER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest request){
        try {
            User theUser = userService.register(request);
            UserDTO registeredUser = entityConverter.mapEntityToDto(theUser, UserDTO.class);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.SUCCESS, registeredUser));

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping(UrlMapping.UPDATE_USER)
    public ResponseEntity<ApiResponse> update(@PathVariable Long userId, @RequestBody UserUpdateRequest request){
        try {
            User theUser = userService.update(userId, request);
            UserDTO updateUser = entityConverter.mapEntityToDto(theUser, UserDTO.class);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, updateUser));

        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_USER_BY_ID)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long userId) {
        try {
            User user = userService.findById(userId);
            UserDTO theUser = entityConverter.mapEntityToDto(user, UserDTO.class);
            return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse(FeedBackMessage.FOUND, theUser));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping(UrlMapping.DELETE_USER_BY_ID)
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        }catch(ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping(UrlMapping.GET_ALL_USERS)
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDTO> theUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse(FeedBackMessage.FOUND, theUsers));
    }
    

}
