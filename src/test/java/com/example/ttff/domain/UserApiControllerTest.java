package com.example.ttff.domain;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ttff.dto.LoginUserDto;
import com.example.ttff.repository.RegionRepository;
import com.example.ttff.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class UserApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService memberService;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void signup() throws Exception {

        String userId = "admin";
        String password = "password";

        Regions region = regionRepository.findBySidoNmAndDongNm("서울특별시", "청운효자동").get();

        User member = User.builder().userId(userId).password(password).region(region).build();

        String url = "http://localhost:" + port + "/api/signup";

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url,
                member,
                User.class);

        // then
        assertThat(responseEntity.getStatusCode());
        memberService.signup(member);
    }

    @Test
    public void login() throws Exception {

        // given
        String memberId = "admin";
        String password = "password";

        String url = "http://localhost:" + port + "/api/login";

        LoginUserDto member = LoginUserDto.builder().memberId(memberId).password(password).build();

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url,
                member,
                User.class);

        // when
        memberService.login(memberId, password);

        // then
        assertThat(responseEntity.getStatusCode());
    }

}