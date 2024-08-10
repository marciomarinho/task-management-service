package au.com.riosoftware.task;

import au.com.riosoftware.task.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

    public static final String HOST = "http://localhost:";
    public static final String TASKS_PATH = "/tasks";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM task");
        jdbcTemplate.execute("DELETE FROM task_user");
    }

    @Test
    void shouldCreateSingleTask() {
        Task newTask = new Task("New Task", "Something for our tests", null);
        HttpEntity<Task> request = new HttpEntity<>(newTask);
        Task result = this.restTemplate.postForEntity(HOST + port + TASKS_PATH, request, Task.class).getBody();
        assertNotNull(result);
        assertEquals(result.getTitle(), "New Task");
        assertEquals(result.getDescription(), "Something for our tests");
        assertEquals(result.getUser(), null);
    }

    @Test
    void shouldGetNoTasksDueToEmptyTable() {
        List<Task> result = this.restTemplate.getForObject("http://localhost:" + port + "/tasks",
                List.class);
        assertThat(result).hasSize(0);
    }

    @Test
    void shouldCreateMultipleTasks() {
        List<Task> tasks = List.of(
                new Task("New Task 1", "An awesome task n.1", null),
                new Task("New Task 2", "An awesome task n.2", null),
                new Task("New Task 3", "An awesome task n.3", null)
        );

        for (Task task : tasks) {
            HttpEntity<Task> request = new HttpEntity<>(task);
            Task result = this.restTemplate.postForEntity(HOST + port + TASKS_PATH, request, Task.class).getBody();
            assertNotNull(result);
        }

        List<Task> result = this.restTemplate.getForObject(HOST + port + TASKS_PATH,
                List.class);
        assertThat(result).hasSize(3);
    }

    @Test
    void shouldDeleteTaskWithEmptyTable() {
        this.restTemplate.delete(HOST + port + TASKS_PATH + "/1");
    }

}
