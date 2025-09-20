package sb.todoist.infra.spi.db.todo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "todos")
public class TodoItemDocument {
    private String description;
    private  boolean complete;
}
