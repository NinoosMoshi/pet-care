package com.ninos.factory;

import com.ninos.model.User;
import com.ninos.request.RegistrationRequest;

public interface UserFactory {

    public User createUser(RegistrationRequest registrationRequest);

}
