package br.com.nttdata.nttskillboost.courseservice.domain.entity;

import lombok.Getter;

@Getter
public enum CourseStatus {
    WIP("WIP"), // Work In Progress
    COMPLETED("COMPLETED"), // Completed
    CANCELED("CANCELED"),  // Canceled
    PENDING("PENDING"),   // Pending
    INACTIVE("INACTIVE"),  // Inactive
    ACTIVE("ACTIVE"),    // Active
    ARCHIVED("ARCHIVED");   // Archived
    // Add more statuses as needed

    private final String description;

    private CourseStatus(String description) {
        this.description = description;
    }

    public static CourseStatus getCourseStatus(String status) {
        for (CourseStatus courseStatus : CourseStatus.values()) {
            if (courseStatus.name().equalsIgnoreCase(status)) {
                return courseStatus;
            }
        }
        throw new IllegalArgumentException("No constant with text " + status + " found");
    }
}
