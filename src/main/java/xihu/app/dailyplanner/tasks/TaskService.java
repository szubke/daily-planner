package xihu.app.dailyplanner.tasks;

import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xihu.app.dailyplanner.tasks.exceptions.TaskNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(String title, LocalDate dueDate) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Task newTask = new Task(id, title, dueDate, false);
        return taskRepository.save(newTask);
    }

    public Task updateTaskTitle(String id, String newTitle) {
        Task oldTask = findTaskById(id);
        Task updatedTask = new Task(id, newTitle, oldTask.getDueDate(), oldTask.isCompleted());
        return taskRepository.save(updatedTask);
    }
    public Task updateTaskDueDate(String id, LocalDate localDate) {
        Task oldTask = findTaskById(id);
        Task updatedTask = new Task(id, oldTask.getTitle(), localDate, oldTask.isCompleted());
        return taskRepository.save(updatedTask);
    }


    public Task markAsCompleted(String id) {
        Task oldTask = findTaskById(id);
        Task updatedTask = new Task(id, oldTask.getTitle(),oldTask.getDueDate(),true);
        return taskRepository.save(updatedTask);

    }

    public void deleteTask(String id) {
        Task taskToDelete = findTaskById(id);
        taskRepository.delete(taskToDelete);
    }

    public Task findTaskById(String id) {
        Task taskToFind = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskToFind;
    }

    public Task getSingleTask(String id) {
        return findTaskById(id);
    }
}