package com.FirstProject.StudentManagement.DTO;

import jakarta.validation.constraints.NotNull;

public record AssignCourseRequest(
        @NotNull(message = "Course id is required") Integer courseId
) {
}
