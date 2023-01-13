package metro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import metro.allLines.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Programm {
    private Scanner scanner = new Scanner(System.in);
    public static List<Station> stationsList = new LinkedList<>();
    public static List<LStation> londonStations = new LinkedList<>();
    public static boolean isON = true;

    public void start(String fileName) {

        File file = new File(fileName);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LondonMetro.class, new LondonGsonDeserializer())
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
        LondonMetro londonMetro;
        if (file.exists()) {
            if (fileName.equals("./test/london.json")) {
                fileName = "C:\\Users\\irbis\\Desktop\\Java\\london.json";
            }
            String json = FileHandler.handleFile(fileName);
            londonMetro = gson.fromJson(json, LondonMetro.class);
            londonStations = londonMetro.getLondonStations();
        } else {
            System.out.println("Error! Such a file doesn't exist!");
        }


        while (isON) {
            String line = scanner.nextLine();
            CommandHandler.handleCommand(Command.defineCommand(line));
        }

    }

    public void start() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LondonMetro.class, new LondonGsonDeserializer())
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                .create();


        String json = FileHandler.handleFile("C:\\Users\\irbis\\Desktop\\Java\\london.json");
        LondonMetro londonMetro = gson.fromJson(json, LondonMetro.class);
        londonStations = londonMetro.getLondonStations();

        while (isON) {
            String line = scanner.nextLine();
            CommandHandler.handleCommand(Command.defineCommand(line));
        }

    }


}
