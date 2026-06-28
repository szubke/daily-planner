package xihu.app.dailyplanner.tasks;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    private String id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;

    public Task() {
    }

    public Task(String id, String title, LocalDate dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
    public boolean setCompleted(boolean isCompleted){
       return this.completed = isCompleted;

    }
    public LocalDate getDueDate(){
        return dueDate;
    }
}