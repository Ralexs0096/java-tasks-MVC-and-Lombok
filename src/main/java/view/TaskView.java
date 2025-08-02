package view;

import controller.TaskController;
import exceptions.TaskException;
import exceptions.TaskValidationException;

import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController, Scanner scanner) {
        this.taskController = taskController;
        this.scanner = scanner;
    }

    public void showMenu(){
        while (true) {
            System.out.println("\n Task Management");
            System.out.println("1. Add task");
            System.out.println("2. Delete task");
            System.out.println("3. Update task");
            System.out.println("4. Remove task");
            System.out.println("5. Exit");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addTaskView();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
            }
        }
    }

    public void addTaskView() {
        try {
            System.out.println("Insert ID: ");
            String id = scanner.nextLine();

            System.out.println("Insert the title");
            String title = scanner.nextLine();

            System.out.println("Insert the description");
            String description = scanner.nextLine();

            System.out.println("Is the task completed? true/false");
            Boolean completed = Boolean.parseBoolean(scanner.nextLine());

            taskController.addTask(id, title, description, completed);
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
    }
}
