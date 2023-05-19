package com.example.ttff.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.repository.RegionRepository;
import com.example.ttff.service.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class UserApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void signup() throws Exception {

        String memberId = "admin";
        String password = "password";

        Region region = regionRepository.findBySidoNmAndDongNm("서울특별시", "청운효자동").get();

        Member member = Member.builder().memberId(memberId).password(password).region(region).build();

        String url = "http://localhost:" + port + "/api/signup";

        ResponseEntity<Member> responseEntity = restTemplate.postForEntity(url,
                member,
                Member.class);

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

        LoginMemberDto member = LoginMemberDto.builder().memberId(memberId).password(password).build();

        ResponseEntity<Member> responseEntity = restTemplate.postForEntity(url,
                member,
                Member.class);

        // when
        memberService.login(memberId, password);

        // then
        assertThat(responseEntity.getStatusCode());
    }

}