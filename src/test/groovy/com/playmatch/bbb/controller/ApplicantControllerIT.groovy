package com.playmatch.bbb.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.playmatch.bbb.BaseIntegrationTest
import com.playmatch.bbb.model.Applicant
import com.playmatch.bbb.repository.ApplicantRepository
import com.playmatch.bbb.resources.model.ApplicantDTO
import com.playmatch.bbb.resources.model.ApplicantStatus
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile

import java.nio.charset.Charset

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ApplicantControllerIT extends BaseIntegrationTest {

    @Autowired
    ApplicantRepository applicantRepository

    def "apply returns proper result"() {
        expect: "Status is 200"

        def applicant = createApplicantDTO()
        def file = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        MockMultipartFile app = new MockMultipartFile("applicant", "", "application/json", JsonOutput.toJson(applicant).getBytes());

        when:
        def results = mvc.perform(multipart('/applicants/')
                .file("video", file.getBytes())
                .file(makeMultipartTextPart("applicant", JsonOutput.toJson(applicant), "application/json"))
                .contentType(MediaType.MULTIPART_FORM_DATA)
        )

        then:
        results.andExpect(status().isCreated())

        and:
        results.andExpect(jsonPath('$.id').isNotEmpty())
        results.andExpect(jsonPath('$.name').value(applicant.getName()))
        results.andExpect(jsonPath('$.email').value(applicant.getEmail()))
        results.andExpect(jsonPath('$.phone').value(applicant.getPhone()))
        results.andExpect(jsonPath('$.cpf').value(applicant.getCpf()))
    }

    def "list returns proper result"() {
        expect: "Status is 200"

        def applicant = createApplicant()
        applicantRepository.save(applicant)

        when:
        def results = mvc.perform(get('/applicants/').param("status", ApplicantStatus.PENDING_REVIEW.name()))

        then:
        results.andExpect(status().isOk())

        and:
        List<ApplicantDTO> result = parseResponse(results.andReturn(),  new TypeReference<List<ApplicantDTO>>(){} )
        result
        result.size() == 1
        result[0].name == applicant.name
        result[0].email == applicant.email
        result[0].cpf == applicant.cpf
        result[0].phone == applicant.phone
    }

    def "get by id returns proper result"() {
        expect: "Status is 200"

        def applicant = createApplicant()
        applicantRepository.save(applicant)

        when:
        def results = mvc.perform(get('/applicants/' + applicant.id))

        then:
        results.andExpect(status().isOk())

        and:
        ApplicantDTO result = parseResponse(results.andReturn(), ApplicantDTO.class)
        result
        result.name == applicant.name
        result.email == applicant.email
        result.cpf == applicant.cpf
        result.phone == applicant.phone
    }

    private ApplicantDTO createApplicantDTO() {
        def applicant = new ApplicantDTO()
        applicant.setName("name")
        applicant.setEmail("email@example.com")
        applicant.setCpf("12345678901")
        applicant.setPhone("11999999999")
        return applicant
    }

    private Applicant createApplicant() {
        def applicant = new Applicant()
        applicant.setName("name")
        applicant.setEmail("email@example.com")
        applicant.setCpf("12345678901")
        applicant.setPhone("11999999999")
        return applicant
    }

    // make text-part using MockMultipartFile
    private MockMultipartFile makeMultipartTextPart(String requestPartName,
                                                    String value, String contentType) throws Exception {

        return new MockMultipartFile(requestPartName, "", contentType,
                value.getBytes(Charset.forName("UTF-8")));
    }
}
