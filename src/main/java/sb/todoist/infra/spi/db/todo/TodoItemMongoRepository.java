package sb.todoist.infra.spi.db.todo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoItemMongoRepository extends MongoRepository<TodoItemDocument, String> {
}
