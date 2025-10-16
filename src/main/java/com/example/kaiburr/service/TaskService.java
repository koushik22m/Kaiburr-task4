package com.example.kaiburr.service;

import com.example.kaiburr.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    // Get all tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Get task by ID
    public Task getTaskById(Long id) {
        Optional<Task> task = tasks.stream()
                                   .filter(t -> t.getId().equals(id))
                                   .findFirst();
        return task.orElse(null);
    }

    // Create a new task
    public Task createTask(Task task) {
        tasks.add(task);
        return task;
    }

    // Update an existing task
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setName(taskDetails.getName());
        }
        return task;
    }

    // Delete a task
    public boolean deleteTask(Long id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }
}
