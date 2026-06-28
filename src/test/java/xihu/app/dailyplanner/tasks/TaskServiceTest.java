package xihu.app.dailyplanner.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xihu.app.dailyplanner.tasks.exceptions.TaskNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    void addTask_setsTitleAndNotCompleted() {
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.addTask("Kup mleko");

        assertEquals("Kup mleko", result.getTitle());
        assertFalse(result.isCompleted());
        assertNotNull(result.getId());
    }

    @Test
    void findTaskById_throwsWhenTaskNotFound() {
        when(taskRepository.findById("zle-id")).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById("zle-id"));
    }
}
