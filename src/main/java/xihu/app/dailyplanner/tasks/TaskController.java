package xihu.app.dailyplanner.tasks;


import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/addTask")
    public Task addTask(@RequestParam String title) {
        return taskService.addTask(title);
    }

    @PostMapping("/tasks/{id}")
    public Task updateTask(@PathVariable String id, @RequestParam String title) {
        return taskService.updateTask(id, title);
    }

}
