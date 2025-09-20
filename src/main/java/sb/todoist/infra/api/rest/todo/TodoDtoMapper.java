package sb.todoist.infra.api.rest.todo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sb.todoist.domain.todo.TodoItem;

@Mapper
public interface TodoDtoMapper {

    TodoDto toDto(TodoItem todoItem);

    @Mapping(target = "complete", ignore = true)
    TodoItem toDomain(CreateTodoRequest request);
}
