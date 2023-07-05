package main;

import response.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static ConcurrentHashMap<AtomicInteger, Task> tasks = new ConcurrentHashMap<>();

    public static List<Task> getAllTasks() {
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.addAll(tasks.values());
        return tasksList;
    }

    public static AtomicInteger addTask(Task task) {
        AtomicInteger id = new AtomicInteger(currentId.getAndIncrement());
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    public static void updateTask(AtomicInteger taskId, Task newTask) {
        tasks.put(taskId, newTask);
    }

    public static void deleteAll() {
        tasks.clear();
    }

    public static Task getTask(int taskId) {
        if(tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }
}
