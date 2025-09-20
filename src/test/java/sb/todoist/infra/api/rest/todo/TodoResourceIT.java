package sb.todoist.infra.api.rest.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import sb.todoist.TestcontainersConfiguration;
import sb.todoist.infra.spi.db.todo.TodoItemDocument;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoResourceIT {

    private static final String TODO_DESCRIPTION = "description";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MockMvcTester mmtt;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void should_create_todo_item() throws Exception {
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "%s"
                                }
                                """.formatted(TODO_DESCRIPTION)))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                          "description": "%s",
                          "complete": false
                        }
                        """.formatted(TODO_DESCRIPTION)));

        List<TodoItemDocument> items = mongoTemplate.findAll(TodoItemDocument.class);
        assertThat(items).hasSize(1);
    }

    @Test
    void should_not_create_todo_item_when_description_is_empty() {
        MvcTestResult response = mmtt.post()
                .uri("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"\"}")
                .exchange();
        assertThat(response).hasStatus(HttpStatus.BAD_REQUEST);
    }

    @Test
    void should_not_create_todo_item_when_description_is_null() {
        MvcTestResult response = mmtt.post().uri("/todos").exchange();
        assertThat(response).hasStatus(HttpStatus.BAD_REQUEST);
    }
}
