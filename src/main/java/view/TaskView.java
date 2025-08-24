package view;

import controller.TaskController;
import exceptions.TaskException;
import exceptions.TaskValidationException;
import model.Task;

import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(){
        while (true) {
            System.out.println("\n Task Management");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Update task");
            System.out.println("4. Show task");
            System.out.println("5. Exit");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addTaskView();
                    break;
                case "2":
                    removeTaskView();
                    break;
                case "3":
                    updateTaskView();
                    break;
                case "4":
                    showTaskView();
                    break;
                case "5":
                    System.out.println("Finishing the program execution...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public void addTaskView() {
        try {
            var task = this.getTaskInput();

            taskController.addTask(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
            System.out.println("Task created");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }

    public void removeTaskView() {
        try {
            System.out.println("Insert the id to remove");
            String id = scanner.nextLine();
            this.taskController.removeTask(id);

            System.out.println("Task deleted successfully");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }

    public void showTaskView() {
        try {
            this.taskController.showTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }

    public void updateTaskView() {
        try {
            var task = this.getTaskInput();

            taskController.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
            System.out.println("Task updated");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }

    private Task getTaskInput() {
        String id, title, description;
        Boolean completed = null;

        do {
            System.out.println("Insert ID: ");
            id = scanner.nextLine();

            if(id.isEmpty()) {
                System.out.println("id cannot be empty");
            }
        } while (id.isEmpty());

        do {
            System.out.println("Insert the title");
            title = scanner.nextLine();

            if(title.isEmpty()) {
                System.out.println("title cannot be empty");
            }
        } while (title.isEmpty());

        do {
            System.out.println("Insert the description");
            description = scanner.nextLine();

            if(description.isEmpty()) {
                System.out.println("description cannot be empty");
            }
        } while (description.isEmpty());


        while (completed == null) {
            System.out.println("Is the task completed? true/false");
            String input = scanner.nextLine().trim().toLowerCase();

            if(input.equals("true")) {
                completed = true;
            }

            if(input.equals("false")) {
                completed = false;
            }

            System.out.println("Please enter a valid option: true or false");
        };

        return new Task(id, title, description, completed);
    }
}
