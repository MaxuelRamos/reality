package com.playmatch.bbb.repository;

import com.playmatch.bbb.model.Applicant;
import com.playmatch.bbb.model.ApplicantStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, String> {

    boolean existsByEmailAndCpf(String email, String cpf);

    List<Applicant> findAllByStatus(ApplicantStatus status);
}
