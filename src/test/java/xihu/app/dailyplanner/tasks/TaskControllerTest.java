package xihu.app.dailyplanner.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import xihu.app.dailyplanner.tasks.exceptions.GlobalExceptionHandler;
import xihu.app.dailyplanner.tasks.exceptions.TaskNotFoundException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(TaskController.class)
@Import(GlobalExceptionHandler.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TaskService taskService;

    @Test
    void getSingleTask_returns404WhenNotFound() throws Exception {
        when(taskService.getSingleTask("zle-id"))
                .thenThrow(new TaskNotFoundException("zle-id"));

        mockMvc.perform(get("/tasks/zle-id"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Task not found: zle-id"));
    }
    @Test
    void addTask_returns400WhenValidationFails() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("title:must not be blank"));
    }
}
