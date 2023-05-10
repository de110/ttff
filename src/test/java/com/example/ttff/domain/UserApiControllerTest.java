package com.example.ttff.domain;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class UserApiControllerTest {
    @LocalServerPort
    private int port;

    @Test
    @Transactional
    public void signup() throws Exception {

    }

    @Test
    // @WithMockUser(username = "admin", roles = { "USER", "ADMIN" })
    public void login() throws Exception {

        // given
        String memberId = "admin";
        String password = "password";

        String url = "http://localhost:" + port + "/api/login";

        // when

        // then
    }
}
