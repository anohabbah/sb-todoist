package sb.todoist;

import org.springframework.boot.SpringApplication;

public class TestTodoistApplication {

    public static void main(String[] args) {
        SpringApplication.from(TodoistApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
