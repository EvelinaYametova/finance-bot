import java.util.Scanner;

public class Console {
    private DialogLogic logic = new DialogLogic();
    private Scanner scanner = new Scanner(System.in);

    public void start(){
        System.out.println(this.logic.executeCommand("/start"));
        while (true) {
            String input = scanner.next();
            System.out.println(this.logic.executeCommand(input));


            if (input.equals("/exit")) {
                break;
            }
        }
    }
}
