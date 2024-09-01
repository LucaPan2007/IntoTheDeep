package org.firstinspires.ftc.teamcode.tasks;


import org.firstinspires.ftc.teamcode.common.Log;

import java.util.function.Supplier;


public class TaskBuilder {
    public static SerialTask serial(Task... task) {
        return new SerialTask(task);
    }

    public static ParallelTask parallel(Task... task) {
        return new ParallelTask(task);
    }
    public static ConditionalTask conditional(Supplier<Boolean> condition, Task task) {
        return new ConditionalTask(condition, task);
    }

    public static SleepTask sleepms(long ms) {
        return new SleepTask(ms);
    }

    public static ExecuteTask execute(Runnable runnable) {
        return new ExecuteTask(runnable);
    }

    public static ExecuteTask log(String message) {
        return new ExecuteTask(() -> {
            Log.getInstance().add(message);
        });
    }

    public static ExecuteTask log(String name, Object line) {
        return new ExecuteTask(() -> {
            Log.getInstance().add(name, line);
        });
    }
}
