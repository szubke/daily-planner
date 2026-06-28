package xihu.app.dailyplanner.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import xihu.app.dailyplanner.tasks.exceptions.TaskNotFoundException;

import java.util.Optional;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        Task result = taskService.addTask("Kup mleko",LocalDate.parse("2026-06-06"));

        assertEquals("Kup mleko", result.getTitle());
        assertFalse(result.isCompleted());
        assertNotNull(result.getId());
    }

    @Test
    void findTaskById_throwsWhenTaskNotFound() {
        when(taskRepository.findById("zle-id")).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById("zle-id"));
    }

    @Test
    void deleteTask_findsAndDeleteTask() {
        Task existing = new Task("abc123","mleko",LocalDate.parse("2026-06-06"),false);
        when(taskRepository.findById("abc123")).thenReturn(Optional.of(existing));
        taskService.deleteTask("abc123");

        verify(taskRepository).delete(existing);
    }


    @Test
    void markAsCompleted_setsCompletedToTrue() {
        Task existing = new Task("abc123","mleko",LocalDate.parse("2026-06-06"),false);
        when(taskRepository.findById("abc123")).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.markAsCompleted("abc123");
        assertTrue(result.isCompleted());
        assertEquals("mleko", result.getTitle());
        assertEquals("abc123", result.getId());
        assertEquals(LocalDate.parse("2026-06-06"), result.getDueDate());

    }


    @Test
    void updateTaskTitle_updatesTitle() {
        Task existing = new Task("abc123","mleko",LocalDate.parse("2026-06-06"),false);
        when(taskRepository.findById("abc123")).thenReturn(Optional.of(existing));
        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.updateTaskTitle("abc123","nowa");
        assertFalse(result.isCompleted());
        assertEquals("nowa", result.getTitle());
        assertEquals("abc123", result.getId());
    }
}
