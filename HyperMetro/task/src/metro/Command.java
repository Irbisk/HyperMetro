package metro;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Command {
    private String command;
    private String value;


    public String getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }

    public Command(String command, String value) {
        this.command = command;
        this.value = value;
    }

    public static Command defineCommand(String input) {
        String command = "";
        String value = "";

        Pattern p = Pattern.compile("/[\\w-]*");
        Matcher m = p.matcher(input);

        if (m.find()) {
            command = m.group().substring(1);
        }

        Logger logger = Logger.getLogger(Command.class.getName());
        logger.log(Level.INFO, command);
        int space = input.indexOf(" ") + 1;
        value = input.substring(space);
        return new Command(command, value);
    }

}
