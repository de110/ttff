package com.example.ttff.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.management.relation.Role;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.dto.MemberDto;
import com.example.ttff.repository.MemberRepository;
import com.example.ttff.repository.RegionRepository;
import com.example.ttff.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

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

        @InjectMocks
        private MemberService memberService;

        @Autowired
        private RegionRepository regionRepository;

        private ObjectMapper objectMapper = new ObjectMapper();

        @Mock
        private MemberRepository memberRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        // @AfterEach
        // void afterEach() {
        // memberRepository.deleteAll();
        // }

        // 회원 가입
        private ResultActions requestSignup(MemberDto.SignupReq dto) throws Exception {
                return mvc.perform(post("/api/signup").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))).andDo(print());
        }

        @Value("${jwt.access.header}")
        private String accessHeader;

        private static final String BEARER = "Bearer ";

        // access Token
        private String getAccessToken() throws Exception {
                LoginMemberDto loginMemberDto = LoginMemberDto.builder()
                                .memberId("test").password("test")
                                .build();
                MvcResult result = mvc.perform(
                                post("/api/login")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(loginMemberDto)))
                                .andExpect(status().isOk()).andReturn();
                log.info("result: " + result.getResponse().getContentAsString());
                String response = result.getResponse().getContentAsString();
                return JsonPath.parse(response).read("$.accessToken");
        }

        @Test
        public void login() throws Exception {
                LoginMemberDto loginMemberDto = LoginMemberDto.builder()
                                .memberId("test")
                                .password("test")
                                .build();
                ResultActions result = mvc.perform(
                                post("/api/login")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(loginMemberDto)));

                result.andExpect(status().isOk()).andDo(print());
        }

        // 테스트용 사용자 정보
        public MemberDto.SignupReq buildSignupReq() {
                Region region = regionRepository.findById(1L).get();
                return MemberDto.SignupReq.builder()
                                .memberId("test")
                                .password("test")
                                .email("email")
                                .name("name")
                                .region(region)
                                .build();
        }

        // 지역 정보 사전 설정
        @Test
        public void setRegion() {
                Region testRegion = Region.builder()
                                .dongNm("dongNm")
                                .build();
                regionRepository.save(testRegion);
        }

        @Test
        public void signup() throws Exception {
                // given
                final MemberDto.SignupReq signupReq = buildSignupReq();

                // when
                ResultActions resultActions = requestSignup(signupReq);

                // then
                resultActions
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.memberId", is(signupReq.getMemberId())));
        }

        @DisplayName("아이디 공백 에러 발생")
        @Test
        public void signup_fail_memberId_isBlank() throws Exception {
                // given
                Region region = regionRepository.findById(1L).get();
                final MemberDto.SignupReq signupReq = MemberDto.SignupReq.builder()
                                .memberId(" ")
                                .password("test")
                                .email("email")
                                .name("name")
                                .region(region)
                                .build();

                // when
                ResultActions resultActions = requestSignup(signupReq);

                // then
                resultActions.andExpect(status().isBadRequest());
        }

        @Test
        public void getUserInfo() throws Exception {
                String accessToken = getAccessToken();
                log.info("accessToken" + accessToken);
                ResultActions result = mvc.perform(
                                get("/api/member")
                                                .characterEncoding(StandardCharsets.UTF_8)
                                                .header(accessHeader, BEARER + accessToken))
                                .andDo(print());
                result.andExpect(status().isOk()).andReturn();

        }
}