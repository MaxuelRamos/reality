package com.playmatch.bbb

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.playmatch.bbb.controller.ApplicantController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc

abstract class BaseIntegrationTest extends Specification {

    @Autowired
    protected MockMvc mvc

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    static <T> T parseResponse(MvcResult result, TypeReference typeReference) {
        try {
            String contentAsString = result.getResponse().getContentAsString()
            return MAPPER.readValue(contentAsString, typeReference)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static <T> T parseResponse(MvcResult result, Class klass) {
        try {
            String contentAsString = result.getResponse().getContentAsString()
            return MAPPER.readValue(contentAsString, klass)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}