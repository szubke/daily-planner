package xihu.app.dailyplanner.tasks;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task addSingleTask(String title) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Task newTask = new Task(id, title, false);
        tasks.add(newTask);
        return newTask;
    }
}
