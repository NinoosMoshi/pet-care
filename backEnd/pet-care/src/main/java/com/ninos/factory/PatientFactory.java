package com.ninos.factory;

import com.ninos.model.Patient;
import com.ninos.model.User;
import com.ninos.repository.PatientRepository;
import com.ninos.request.RegistrationRequest;
import com.ninos.service.user.UserAttributesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory {

    private final PatientRepository patientRepository;
    private final UserAttributesMapper userAttributesMapper;


    public Patient createPatient(RegistrationRequest request) {
        Patient patient = new Patient();
        userAttributesMapper.setCommonAttributes(request,patient);
        return patientRepository.save(patient);
    }
}
