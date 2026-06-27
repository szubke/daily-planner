package xihu.app.dailyplanner.tasks;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Task addTask(String title) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Task newTask = new Task(id, title, false);
        return taskRepository.save(newTask);
    }

    public Task updateTask(String id, String newTitle) {
        Task oldTask = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (oldTask != null) {
            // Zmiana na .isCompleted()
            Task updatedTask = new Task(id, newTitle, oldTask.isCompleted());
            return taskRepository.save(updatedTask);
        }

        return null;
    }


    public Task markAsCompleted(String id) {
        Task oldTask = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (oldTask != null) {
            Task updatedTask = new Task(id, oldTask.getTitle(), true);
            return taskRepository.save(updatedTask);
        }
        return null;
    }
}