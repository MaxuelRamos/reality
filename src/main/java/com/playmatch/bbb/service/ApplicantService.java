package com.playmatch.bbb.service;

import com.playmatch.bbb.exception.ResourceAlreadyExists;
import com.playmatch.bbb.model.Applicant;
import com.playmatch.bbb.model.ApplicantStatus;
import com.playmatch.bbb.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.playmatch.bbb.utils.AssertionUtils.assertFalse;

@Service
public class ApplicantService {

    @Autowired
    ApplicantRepository repository;

    public Applicant apply(Applicant applicant, MultipartFile video) {

        boolean alreadyExists = repository.existsByEmailAndCpf(applicant.getEmail(), applicant.getCpf());

        assertFalse(alreadyExists)
                .orElseThrow(ResourceAlreadyExists::new);

        applicant.setVideoPath(video.getOriginalFilename());
        applicant.setStatus(ApplicantStatus.PENDING_REVIEW);

        return repository.save(applicant);
    }

    public Optional<Applicant> getById(String id) {
        return repository.findById(id);
    }

    public List<Applicant> list(ApplicantStatus status) {
        return status == null ?
                StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList())
                : repository.findAllByStatus(status);
    }
}
