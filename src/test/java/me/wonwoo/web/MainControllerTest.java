package me.wonwoo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.model.Dependencies;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wonwoo on 2016. 11. 20..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  private JacksonTester<Dependencies> json;

  @Before
  public void setup() {
    ObjectMapper objectMapper = new ObjectMapper();
    JacksonTester.initFields(this, objectMapper);
  }


  @Test
  public void dependenciesTest () throws IOException {
    ResponseEntity<Dependencies> response = execute("/ui/dependencies?version=1.4.0.RELEASE", Dependencies.class, null, null);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    final Dependencies body = response.getBody();
    assertThat(body).isNotNull();
    final JsonContent<Dependencies> write = this.json.write(body);
    assertThat(write).isEqualToJson("/dependencies.json");
  }


  private <T> ResponseEntity<T> execute(String contextPath, Class<T> responseType,
                                          String userAgentHeader, String... acceptHeaders) {
    HttpHeaders headers = new HttpHeaders();
    if (userAgentHeader != null) {
      headers.set("User-Agent", userAgentHeader);
    }
    if (acceptHeaders != null) {
      List<MediaType> mediaTypes = new ArrayList<>();
      for (String acceptHeader : acceptHeaders) {
        mediaTypes.add(MediaType.parseMediaType(acceptHeader));
      }
      headers.setAccept(mediaTypes);
    } else {;
      headers.setAccept(Collections.emptyList());
    }
    return restTemplate.exchange(contextPath,
      HttpMethod.GET, new HttpEntity<Void>(headers), responseType);
  }

}