package sb.todoist.infra.spi.db.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sb.todoist.domain.todo.TodoItem;
import sb.todoist.domain.todo.TodoPort;

@Component
@RequiredArgsConstructor
public class TodoAdapter implements TodoPort {

    private final TodoItemMongoRepository repository;

    private final TodoItemDocumentMapper mapper;

    @Override
    public TodoItem create(TodoItem item) {
        TodoItemDocument document = repository.save(mapper.toDocument(item));
        return mapper.toDomain(document);
    }
}
