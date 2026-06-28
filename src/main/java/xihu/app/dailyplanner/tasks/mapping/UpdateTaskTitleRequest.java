package xihu.app.dailyplanner.tasks.mapping;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskTitleRequest(@NotBlank String title) {
}
