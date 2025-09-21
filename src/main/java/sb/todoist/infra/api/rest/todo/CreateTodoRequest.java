package sb.todoist.infra.api.rest.todo;

import jakarta.validation.constraints.NotBlank;
import lombok.With;

@With
public record CreateTodoRequest(@NotBlank String description) {
}
