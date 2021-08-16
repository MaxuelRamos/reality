package com.playmatch.bbb.resources;

import com.playmatch.bbb.resources.model.ApplicantDTO;
import com.playmatch.bbb.resources.model.ApplicantStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RequestMapping(value = "/applicants")
public interface ApplicantResource {

    @PostMapping(value = "/", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ApplicantDTO apply( @RequestPart(value = "applicant") ApplicantDTO applicant,  @RequestParam MultipartFile file);

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    List<ApplicantDTO> list(@RequestParam(value= "status", required = false) ApplicantStatus status);

    @GetMapping(value= "/{id}", produces = APPLICATION_JSON_VALUE)
    ApplicantDTO getById(@PathVariable("id") String id);
}
