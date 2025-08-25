package model;

import exceptions.TaskException;
import persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> tasks;

    public TaskRepository() {
        this.tasks = TaskPersistence.loadTasks();
    }

    public void save(Task task) throws TaskException {
        if(task == null) {
            throw new TaskException("Task could not be null");
        }
        tasks.add(task);
        TaskPersistence.saveTasks(tasks);
    }

    public Task findById(String id){
        for(Task task: tasks) {
            if(task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if(task == null) {
            throw new TaskException("Task could not be null");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }
    public void remove(Task task) throws TaskException {
        if(task == null) {
            throw new TaskException("Task could not be null");
        }
        if(!tasks.contains(task)) {
            throw new TaskException("Task does not exist in the list");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    public List<Task> findAll() throws TaskException {
        if(tasks.isEmpty()) {
            throw new TaskException("List is empty");
        }
        return tasks;
    }

    public int findIndexById(String id){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task updatedTask) throws TaskException {
        if(updatedTask == null) {
            throw new TaskException("Task could not be null");
        }
        int index = findIndexById(updatedTask.getId());
        if(index == -1) {
            throw new TaskException("Invalid index");
        }
        tasks.set(index, updatedTask);
        TaskPersistence.saveTasks(tasks);
    }
}
