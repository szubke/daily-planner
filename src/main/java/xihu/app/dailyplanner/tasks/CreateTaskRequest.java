package xihu.app.dailyplanner.tasks;


import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(@NotBlank String title) {
}
