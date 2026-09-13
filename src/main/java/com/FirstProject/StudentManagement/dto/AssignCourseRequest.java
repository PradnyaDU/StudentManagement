package com.FirstProject.StudentManagement.dto;

import jakarta.validation.constraints.NotNull;

public record AssignCourseRequest(
        @NotNull(message = "Course id is required") Integer courseId
) {
}
