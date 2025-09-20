package sb.todoist.domain.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoUsecase {

    private  final TodoPort port;

    public TodoItem create(TodoItem item) {
        return port.create(item);
    }
}
