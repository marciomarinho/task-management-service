package au.com.riosoftware.task;

import au.com.riosoftware.task.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetAllTasks() {
        List<Task> result = this.restTemplate.getForObject("http://localhost:" + port + "/tasks",
                List.class);
        assertThat(result).hasSize(3);
    }

    @Test
    void shouldCreateTask() {
        Task newTask = new Task("New Task", "Something for our tests", null);
        HttpEntity<Task> request = new HttpEntity<>(newTask);
        Task result = this.restTemplate.postForEntity("http://localhost:" + port + "/tasks", request, Task.class).getBody();
        assertNotNull(result);
        assertEquals(result.getTitle(), "New Task");
        assertEquals(result.getDescription(), "Something for our tests");
        assertEquals(result.getUser(), null);
    }

}
