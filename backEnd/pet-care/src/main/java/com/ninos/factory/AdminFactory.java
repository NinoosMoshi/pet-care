package com.ninos.factory;

import com.ninos.model.Admin;
import com.ninos.model.User;
import com.ninos.repository.AdminRepository;
import com.ninos.request.RegistrationRequest;
import com.ninos.service.user.UserAttributesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFactory {

    private final AdminRepository adminRepository;
    private final UserAttributesMapper userAttributesMapper;


    public Admin createAdmin(RegistrationRequest request) {
        Admin admin = new Admin();
        userAttributesMapper.setCommonAttributes(request,admin);
        return adminRepository.save(admin);
    }
}
