import controller.TaskController;
import model.TaskRepository;
import view.TaskView;

public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskController controller = new TaskController(repository);

        TaskView view = new TaskView(controller);

        view.showMenu();
    }
}
