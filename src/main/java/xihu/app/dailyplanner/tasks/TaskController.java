package xihu.app.dailyplanner.tasks;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import xihu.app.dailyplanner.tasks.mapping.CreateTaskRequest;
import xihu.app.dailyplanner.tasks.mapping.UpdateTaskTitleRequest;
import xihu.app.dailyplanner.tasks.mapping.UpdateTaskDueDateRequest;

import java.util.List;

@RestController
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@Valid @RequestBody CreateTaskRequest request) {

        Task task = taskService.addTask(request.title(), request.localDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskTitle(@PathVariable String id, @Valid @RequestBody UpdateTaskTitleRequest request) {

        Task task = taskService.updateTaskTitle(id, request.title());
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }
    @PostMapping("/tasks/{id}/dueDate")
    public ResponseEntity<Task> updateTaskDueDate(@PathVariable String id, @Valid @RequestBody UpdateTaskDueDateRequest request) {

        Task task = taskService.updateTaskDueDate(id, request.localDate());
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }


    @PostMapping("/tasks/{id}/completed")
    public Task markAsCompleted(@PathVariable String id) {
        return taskService.markAsCompleted(id);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/{id}")
    public Task getSingleTask(@PathVariable String id) {
        return taskService.getSingleTask(id);
    }

}
