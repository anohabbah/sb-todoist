package sb.todoist.infra.api.rest.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoResource {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> create(@Valid @RequestBody CreateTodoRequest request) {
        TodoDto dto = todoService.create(request);
        return ResponseEntity.created(URI.create("/todos")).body(dto);
    }
}
