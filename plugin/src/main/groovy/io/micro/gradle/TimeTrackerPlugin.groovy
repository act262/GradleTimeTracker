package io.micro.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle task execution time tracking.
 */
class TimeTrackerPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        TimeTrackerExtension extension = target.extensions
                .create("timeTracker", TimeTrackerExtension)
        target.gradle.addListener(new TimeTracker(extension))
    }

}
