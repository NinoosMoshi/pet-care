package com.ninos.factory;

import com.ninos.exception.UserAlreadyExistsException;
import com.ninos.model.User;
import com.ninos.repository.UserRepository;
import com.ninos.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleUserFactory implements UserFactory {

    private final UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final PatientFactory patientFactory;
    private AdminFactory adminFactory;


    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("Oops! " + registrationRequest.getEmail() + " already exists!");
        }

        switch (registrationRequest.getUserType()) {
            case "VET" -> {
                return veterinarianFactory.createVeterinarian(registrationRequest);
            }
            case "PATIENT" -> {
                return patientFactory.createPatient(registrationRequest);
            }
            case "ADMIN" -> {
                return adminFactory.createAdmin(registrationRequest);
            }
            default -> {
                return null;
            }
        }

    }
}
