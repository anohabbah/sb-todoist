package sb.todoist.domain.todo;

import lombok.With;

@With
public record TodoItem(String description, boolean complete) {
}
