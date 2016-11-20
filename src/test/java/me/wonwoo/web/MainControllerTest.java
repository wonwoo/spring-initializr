package me.wonwoo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.model.Dependencies;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
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

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  @Autowired
  private TestRestTemplate restTemplate;

  private JacksonTester<Dependencies> json;

  @Before
  public void setup() {
    ObjectMapper objectMapper = new ObjectMapper();
    JacksonTester.initFields(this, objectMapper);
  }


  @Test
  public void dependenciesTest() throws IOException {
    ResponseEntity<Dependencies> response = execute("/ui/dependencies?version=1.4.0.RELEASE", Dependencies.class, null, null);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    final Dependencies body = response.getBody();
    assertThat(body).isNotNull();
    final JsonContent<Dependencies> write = this.json.write(body);
    assertThat(write).isEqualToJson("/dependencies.json");
  }

  @Test
  public void simpleZipProject() {
    downloadZip("/starter.zip?baseDir=&bootVersion=1.4.2.RELEASE&dependencies&description=Demo%20project%20for%20Spring%20Boot&groupId=com.example&javaVersion=1.8&language=java&name=demo&packageName=com.example&style=web&style=jpa&type=maven-project&version=0.0.1-SNAPSHOT")
      .isJavaProject()
      .hasFile(".gitignore")
      .isMavenProject()
      .hasStaticAndTemplatesResources(true);
  }

  @Test
  public void simpleTarProject() {
    downloadTgz("/starter.tgz?baseDir=&bootVersion=1.4.2.RELEASE&dependencies&description=Demo%20project%20for%20Spring%20Boot&groupId=com.example&javaVersion=1.8&language=java&name=demo&packageName=com.example&style=web&style=jpa&type=maven-project&version=0.0.1-SNAPSHOT")
      .isJavaProject()
      .hasFile(".gitignore")
      .isMavenProject()
      .hasStaticAndTemplatesResources(true);
//      .hasSpringBootStarterDependency("web")
//      .hasSpringBootStarterDependency("data-jpa") // alias jpa -> data-jpa
//      .hasSpringBootStarterTest();
  }

  @Test
  public void simpleKotlinZipProject() {
    downloadZip("/starter.zip?baseDir=&bootVersion=1.4.2.RELEASE&dependencies&description=Demo%20project%20for%20Spring%20Boot&groupId=com.example&javaVersion=1.8&language=kotlin&name=demo&packageName=com.example&style=web&style=jpa&type=maven-project&version=0.0.1-SNAPSHOT")
      .isKotlinProject()
      .hasFile(".gitignore")
      .isMavenProject()
      .hasStaticAndTemplatesResources(true);
  }


  @Test
  public void simpleKotlinTarProject() {
    downloadTgz("/starter.tgz?baseDir=&bootVersion=1.4.2.RELEASE&dependencies&description=Demo%20project%20for%20Spring%20Boot&groupId=com.example&javaVersion=1.8&language=kotlin&name=demo&packageName=com.example&style=web&style=jpa&type=maven-project&version=0.0.1-SNAPSHOT")
      .isKotlinProject()
      .hasFile(".gitignore")
      .isMavenProject()
      .hasStaticAndTemplatesResources(true);
  }

  @Test
  public void simpleGroovyZipProject() {
    downloadZip("/starter.zip?baseDir=&bootVersion=1.4.2.RELEASE&dependencies&description=Demo%20project%20for%20Spring%20Boot&groupId=com.example&javaVersion=1.8&language=groovy&name=demo&packageName=com.example&style=web&style=jpa&type=maven-project&version=0.0.1-SNAPSHOT")
      .isGroovyProject()
      .hasFile(".gitignore")
      .isMavenProject()
      .hasStaticAndTemplatesResources(true);
  }


  @Test
  public void simpleGroovyTarProject() {
    downloadTgz("/starter.tgz?baseDir=&bootVersion=1.4.2.RELEASE&dependencies&description=Demo%20project%20for%20Spring%20Boot&groupId=com.example&javaVersion=1.8&language=groovy&name=demo&packageName=com.example&style=web&style=jpa&type=maven-project&version=0.0.1-SNAPSHOT")
      .isGroovyProject()
      .hasFile(".gitignore")
      .isMavenProject()
      .hasStaticAndTemplatesResources(true);
  }




  protected ProjectAssert downloadTgz(String context) {
    final byte[] body = downloadArchive(context);
    return tgzProjectAssert(body);
  }

  protected ProjectAssert downloadZip(String context) {
    final byte[] body = downloadArchive(context);
    return zipProjectAssert(body);
  }

  protected byte[] downloadArchive(String context) {
    return restTemplate.getForObject(context, byte[].class);
  }

  protected ProjectAssert zipProjectAssert(byte[] content) {
    return projectAssert(content, ArchiveType.ZIP);
  }
  protected ProjectAssert tgzProjectAssert(byte[] content) {
    return projectAssert(content, ArchiveType.TGZ);
  }


  protected ProjectAssert projectAssert(byte[] content, ArchiveType archiveType) {
    File archiveFile = writeArchive(content);
    File project ;
    try{
      project = folder.newFolder();
    }catch (Exception e){
      throw new RuntimeException("project exception");
    }

    switch (archiveType) {
      case ZIP:
        try{
          Compress.unzip(archiveFile , project);
        }catch (Exception e){
          throw new RuntimeException("no unzip exception");
        }
        break;
      case TGZ:
        try{
          Compress.unTar(archiveFile , project);
        }catch (Exception e){
          throw new RuntimeException(e);
        }
        break;
    }
    return new ProjectAssert(project);
  }


  protected File writeArchive(byte[] body) {

    File archiveFile = null;
    OutputStream stream = null;
    try {
      archiveFile = folder.newFile();
      stream = new FileOutputStream(archiveFile);
      stream.write(body);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stream != null) {
          stream.close();
        }
      } catch (IOException e) {
      }
    }
    return archiveFile;
  }

  private enum ArchiveType {
    ZIP,

    TGZ
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
    } else {
      ;
      headers.setAccept(Collections.emptyList());
    }
    return restTemplate.exchange(contextPath,
      HttpMethod.GET, new HttpEntity<Void>(headers), responseType);
  }

}