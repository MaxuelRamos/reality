package com.playmatch.bbb

import com.playmatch.bbb.controller.ApplicantController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc

abstract class BaseIntegrationTest extends Specification {

    @Autowired
    protected MockMvc mvc;

}