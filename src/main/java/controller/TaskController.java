package controller;

import exceptions.TaskException;
import exceptions.TaskValidationException;
import model.Task;
import model.TaskRepository;

import java.util.List;

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);

        Task task = new Task(id, title, description, completed);
        this.taskRepository.save(task);
        System.out.println("Task added successfully");
    }

    public void removeTask(String id) throws TaskValidationException, TaskException {
        if(id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("Id could not be null");
        }

        this.taskRepository.remove(id);
    }

    public void showTasks() throws TaskValidationException, TaskException {
        List<Task> tasks = this.taskRepository.findAll();
        if(tasks.isEmpty()) {
            throw new TaskValidationException("Tasks array is empty");
        }

        for (Task task: tasks) {
            System.out.println(task);
        }
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);

        Task updatedTask = new Task(id, title, description, completed);

        this.taskRepository.updateTask(updatedTask);
    }

    private void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        if(id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("Id could not be null");
        }

        if(title == null || title.trim().isEmpty()) {
            throw new TaskValidationException("Title could not be empty");
        }

        if(description == null || description.trim().isEmpty()) {
            throw new TaskValidationException("Description could not be empty");
        }

        if(completed == null ) {
            throw new TaskValidationException("Title could not be null");
        }
    }
}
