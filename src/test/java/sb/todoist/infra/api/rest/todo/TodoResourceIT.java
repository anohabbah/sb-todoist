package sb.todoist.infra.api.rest.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import sb.todoist.TestcontainersConfiguration;
import sb.todoist.infra.fixtures.TodoItemFixture;
import sb.todoist.infra.spi.db.todo.TodoItemDocument;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoResourceIT {

  private static final String TODO_DESCRIPTION = "description";

  @Autowired
  MockMvcTester mvc;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Test
  void should_create_todo_item() throws Exception {
    // given
    CreateTodoRequest request = TodoItemFixture.aCreateRequest()
                                               .withDescription(TODO_DESCRIPTION);

    MvcTestResult response = mvc.post()
                                .uri("/todos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                                .exchange();

    assertThat(response).hasStatus(HttpStatus.CREATED)
                        .bodyJson()
                        .hasPathSatisfying("description", path -> path.assertThat()
                                                                      .asString()
                                                                      .isEqualTo(TODO_DESCRIPTION))
                        .hasPathSatisfying("complete", path -> path.assertThat()
                                                                   .asBoolean()
                                                                   .isFalse());

    List<TodoItemDocument> items = mongoTemplate.findAll(TodoItemDocument.class);
    assertThat(items).hasSize(1);
  }

  @Test
  void should_not_create_todo_item_when_description_is_empty() {
    MvcTestResult response = mvc.post()
                                .uri("/todos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"description\": \"\"}")
                                .exchange();
    assertThat(response).hasStatus(HttpStatus.BAD_REQUEST);
  }

  @Test
  void should_not_create_todo_item_when_description_is_null() {
    MvcTestResult response = mvc.post()
                                .uri("/todos")
                                .exchange();
    assertThat(response).hasStatus(HttpStatus.BAD_REQUEST);
  }
}
