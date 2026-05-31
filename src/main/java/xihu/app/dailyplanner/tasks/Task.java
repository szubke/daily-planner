package xihu.app.dailyplanner.tasks;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    private String id;
    private String title;
    private boolean completed;

    public Task() {
    }

    public Task(String id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // Klasyczne gettery JavaBean (Jackson teraz bez problemu zamieni je na JSON)
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}