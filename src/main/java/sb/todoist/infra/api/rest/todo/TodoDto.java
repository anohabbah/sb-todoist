package sb.todoist.infra.api.rest.todo;

import lombok.With;

@With
public record TodoDto(String description, boolean complete) {
}
