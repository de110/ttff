// package com.example.ttff.domain;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.test.web.server.LocalServerPort;
// import org.springframework.http.ResponseEntity;
// import org.springframework.transaction.annotation.Transactional;

// import com.example.ttff.repository.BoardRepository;

// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.List;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public class BoardsApiControllerTest {
// @LocalServerPort
// private int port;

// @Autowired
// private TestRestTemplate restTemplate;

// @Autowired
// private BoardRepository boardRepository;

// @Test
// @Transactional
// public void Post_create() throws Exception {

// // given
// String title = "title9";
// String rule = "rule9";

// Board board = Board.builder().title(title).rule(rule).build();

// String url = "http://localhost:" + port + "/board";

// // when
// ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, board,
// Long.class);

// // then
// assertThat(responseEntity.getStatusCode());
// List<Board> all = boardRepository.findAll();
// assertThat(all.get(5).getTitle()).isEqualTo(title);
// assertThat(all.get(5).getRule()).isEqualTo(rule);
// }

// }
