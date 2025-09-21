package sb.todoist.infra.fixtures;

import lombok.experimental.UtilityClass;
import org.instancio.Instancio;
import sb.todoist.domain.todo.TodoItem;
import sb.todoist.infra.api.rest.todo.CreateTodoRequest;
import sb.todoist.infra.api.rest.todo.TodoDto;
import sb.todoist.infra.spi.db.todo.TodoItemDocument;
import static org.instancio.Select.field;

@UtilityClass
public class TodoItemFixture {

  public static TodoItem aTodo() {
    return Instancio.of(TodoItem.class)
                    .generate(field("description"), gen -> gen.text().loremIpsum())
                    .generate(field("complete"), gen -> gen.oneOf(false, true))
                    .create();
  }

  public static TodoDto aTodoDto() {
    return Instancio.of(TodoDto.class)
                    .generate(field("description"), gen -> gen.text().loremIpsum())
                    .generate(field("complete"), gen -> gen.oneOf(false, true))
                    .create();
  }

  public static TodoItemDocument aTodoDocument() {
    return Instancio.of(TodoItemDocument.class)
                    .generate(field("description"), gen -> gen.text().loremIpsum())
                    .generate(field("complete"), gen -> gen.oneOf(false, true))
                    .create();
  }

  public static CreateTodoRequest aCreateRequest() {
    return Instancio.of(CreateTodoRequest.class)
                    .generate(field("description"), gen -> gen.text().loremIpsum())
                    .create();
  }
}
