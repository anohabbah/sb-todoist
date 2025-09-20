package sb.todoist.infra.api.rest.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sb.todoist.domain.todo.TodoUsecase;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoUsecase usecase;

    private final TodoDtoMapper mapper;

    public TodoDto create(CreateTodoRequest request) {
        return mapper.toDto(usecase.create(mapper.toDomain(request)));
    }
}
