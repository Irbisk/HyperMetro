package metro;
import static metro.Programm.isON;

public class CommandHandler {


    public static void handleCommand(Command command) {
        String action = command.getCommand();

        switch (action) {
            case "add":
                JsonConverter.add(command.getValue());
                break;
            case "output":
                JsonConverter.output(command.getValue());
                break;
            case "append":
                JsonConverter.append(command.getValue());
                break;
            case "add-head":
                JsonConverter.addHead(command.getValue());
                break;
            case "remove":
                JsonConverter.remove(command.getValue());
                break;
            case "connect":
                JsonConverter.connect(command.getValue());
                break;
            case "route":
                JsonConverter.route(command.getValue());
                break;
            case "fastest-route":
                JsonConverter.fastestRoute(command.getValue());
                break;
            case "exit":
                isON = false;
                break;
            default:
                System.out.println("Invalid command");
        }


    }
}
