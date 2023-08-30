package com.example.ttff.domain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.ttff.dto.BoardDto;
import com.example.ttff.dto.LoginMemberDto;
import com.example.ttff.dto.MemberDto;
import com.example.ttff.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration
public class BoardsApiControllerTest {
        @LocalServerPort
        private int port;

        @Autowired
        private MockMvc mvc;

        private ObjectMapper objectMapper = new ObjectMapper();

        @Value("${jwt.access.header}")
        private String accessHeader;

        private static final String BEARER = "Bearer ";

        // 로그인 사용자 토큰 정보
        private String getAccessToken() throws Exception {
                LoginMemberDto loginMemberDto = LoginMemberDto.builder()
                                .memberId("test").password("test")
                                .build();
                MvcResult result = mvc.perform(
                                post("/api/login")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(loginMemberDto)))
                                .andExpect(status().isOk()).andReturn();
                String response = result.getResponse().getContentAsString();
                return JsonPath.parse(response).read("$.accessToken");
        }

        // 게시글 작성
        @Test
        public void Post_create() throws Exception {
                // given
                String title = "title2";
                String content = "content2";
                String accessToken = getAccessToken();

                BoardDto dto = BoardDto.builder().title(title).content(content).build();

                // when
                ResultActions result = mvc.perform(
                                post("/board")
                                                .characterEncoding(StandardCharsets.UTF_8)
                                                .header(accessHeader, BEARER + accessToken)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(dto)))
                                .andDo(print());
                result.andExpect(status().isCreated()).andReturn();

        }

        // 게시글 수정
        @Test
        public void Post_update() throws Exception {

        }

        // 게시글 삭제 성공
        @Test
        public void Post_delete() throws Exception {

        }

        // 카테고리 기준 게시글 정렬
        @Test
        public void Posts_read_by_category() throws Exception {
        }

        // 지역 기준 게시글 정렬
        @Test
        public void Posts_read_by_region() throws Exception {
                String dongNm = "dongNm";

                ResultActions result = mvc
                                .perform(get("/boards").param("dongNm", dongNm)
                                                .contentType(MediaType.APPLICATION_JSON))
                                .andDo(print());
                result.andExpect(status().isOk()).andReturn();
        }

        // 전체 게시글 ?
        @Test
        public void Posts_read_all() throws Exception {

        }

}
