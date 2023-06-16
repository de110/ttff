package com.example.ttff.domain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.repository.RegionRepository;
import com.example.ttff.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class UserApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("GET")
    public void callGetTest() throws Exception {
        this.mvc.perform(get("/api/user/admin")).andExpect(status().isOk());
    }

    @Test
    public void signup() throws Exception {

        String memberId = "admin";
        String password = "password";

        UUID uuid = UUID.randomUUID();
        // String id = uuid.toString();

        Region region = Region.builder().sidoNm("sido").sigunguNm("sigungu").dongNm("dong").build();
        regionRepository.save(region);

        // regionRepository.findBySidoNmAndDongNm("sido", "dong").get();

        Member member = Member.builder().memberId(memberId).password(password).region(region).uid(uuid).build();

        String url = "http://localhost:" + port + "/api/signup";

        ResponseEntity<Member> responseEntity = restTemplate.postForEntity(url,
                member,
                Member.class);

        // then
        assertThat(responseEntity.getStatusCode());
    }

    @Test
    public void login() throws Exception {

        // given
        String memberId = "admin";
        String password = "password";

        String url = "http://localhost:" + port + "/api/login";

        LoginMemberDto member = LoginMemberDto.builder().memberId(memberId).password(password).build();

        ResponseEntity<Member> responseEntity = restTemplate.postForEntity(url,
                member,
                Member.class);

        // when
        // memberService.login(memberId, password);

        // then
        assertThat(passwordEncoder.matches("password", passwordEncoder.encode(password)));
    }

    @Test
    public void getTest() throws Exception {
        String url = "http://localhost:" + port + "/api/user/admin";
        MvcResult result = mvc.perform(get(url)).andReturn();
        JSONObject response = new JSONObject(result.getResponse().getContentAsString());
        assertThat(response);
    }

}