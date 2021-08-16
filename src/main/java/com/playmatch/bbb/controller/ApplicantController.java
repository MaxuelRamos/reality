package com.playmatch.bbb.controller;

import com.playmatch.bbb.exception.ResourceNotFoundException;
import com.playmatch.bbb.mapper.ApplicantMapper;
import com.playmatch.bbb.model.Applicant;
import com.playmatch.bbb.resources.ApplicantResource;
import com.playmatch.bbb.resources.model.ApplicantDTO;
import com.playmatch.bbb.resources.model.ApplicantStatus;
import com.playmatch.bbb.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ApplicantController implements ApplicantResource {

    ApplicantMapper mapper = ApplicantMapper.INSTANCE;

    @Autowired
    ApplicantService service;

    @Override
    public ApplicantDTO apply(ApplicantDTO applicant, MultipartFile video) {
        Applicant result = service.apply(mapper.toEntity(applicant), video);
        return mapper.toDTO(result);
    }

    @Override
    public List<ApplicantDTO> list(ApplicantStatus status) {
        com.playmatch.bbb.model.ApplicantStatus applicantStatus = mapper.statusToEntity(status);
        return mapper.toDTOs(service.list(applicantStatus));
    }

    @Override
    public ApplicantDTO getById(String id) {
        return mapper.toDTO(service.getById(id).orElseThrow(ResourceNotFoundException::new));
    }
}
