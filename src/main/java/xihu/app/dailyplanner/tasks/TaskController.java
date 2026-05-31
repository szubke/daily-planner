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
        return taskService.getTasks();
    }

    @PostMapping("/addTask")
    public Task addTask(@RequestParam String title) {
        return taskService.addSingleTask(title);
    }

    @PostMapping("/tasks/{id}")
    public Task updateTask(@PathVariable String id, @RequestParam String title) {
        return taskService.updateTask(id, title);
    }

}
