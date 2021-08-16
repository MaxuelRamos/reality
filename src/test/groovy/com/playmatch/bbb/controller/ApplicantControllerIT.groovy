package com.playmatch.bbb.controller

import com.playmatch.bbb.BaseIntegrationTest
import com.playmatch.bbb.resources.model.ApplicantDTO
import groovy.json.JsonOutput
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ApplicantControllerIT extends BaseIntegrationTest {

//    def "apply returns proper result"() {
//        expect: "Status is 200 and the response is 'Hello world!'"
//        when:
//        def results = mvc.perform(post('/applicants/')
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonOutput.toJson(new ApplicantDTO())
//                ))
//
//        then:
//        results.andExpect(status().isCreated())
//
//        and:
//        results.andExpect(jsonPath('$.id').value(null))
//        results.andExpect(jsonPath('$.name').value(null))
//    }
}
