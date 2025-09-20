package sb.todoist.infra.api.rest.todo;

import jakarta.validation.constraints.NotBlank;

public record CreateTodoRequest(@NotBlank String description) {
}
