package sb.todoist.infra.spi.db.todo;

import org.mapstruct.Mapper;
import sb.todoist.domain.todo.TodoItem;

@Mapper
public interface TodoItemDocumentMapper {

    TodoItemDocument toDocument(TodoItem todoItem);

    TodoItem toDomain(TodoItemDocument document);
}
