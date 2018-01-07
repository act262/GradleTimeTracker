package io.micro.gradle

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TimeTracker implements TaskExecutionListener, BuildListener {

    private Timer timer = new Timer()
    private Map<Task, Long> costTimeMap = [:]

    private TimeTrackerExtension extension;

    TimeTracker(TimeTrackerExtension extension) {
        this.extension = extension
    }

    @Override
    void beforeExecute(Task task) {
        timer.reset()
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        def ms = timer.getElapsedMillis()
        costTimeMap[task] = ms
    }

    @Override
    void buildStarted(Gradle gradle) {
    }

    @Override
    void settingsEvaluated(Settings settings) {
    }

    @Override
    void projectsLoaded(Gradle gradle) {
    }

    @Override
    void projectsEvaluated(Gradle gradle) {
    }

    @Override
    void buildFinished(BuildResult result) {
        if (costTimeMap.isEmpty()) {
            return
        }
        // extension filter
        if (extension.threshold > 0) {
            println "Enabled time filter(threshold >= ${extension.threshold}ms)"
            costTimeMap = costTimeMap.findAll {
                it.value >= extension.threshold
            }
        }

        // filter include task
        if (extension.includeTask?.size() > 0) {
            println "Enabled task filter"
            costTimeMap = costTimeMap.findAll {
                extension.includeTask.contains(it.key.name)
            }
        }

        // maybe a empty result
        if (costTimeMap.isEmpty()) {
            return
        }

        println "Task spend time:"
        printf "%7sms All Task\n", costTimeMap.values().sum()

        costTimeMap.each { key, value ->
            printf "%7sms  %s\n", value, key
        }
    }
}
