package metro.allLines;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class MetroGsonSerializer implements JsonSerializer<Metro> {

    @Override
    public JsonElement serialize(Metro src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject metroJsonObject = new JsonObject();

        List<Station> stations = src.getStations();
        List<Line> lines = new LinkedList<>();

        for (Station station: stations) {
            metroJsonObject.addProperty(station.getLine().getLineName(), "1");
        }
        metroJsonObject.keySet().forEach(x -> lines.add(new Line(x)));

        for (Line line: lines) {
            JsonObject stationWithNumberJsonObject = new JsonObject();

            for (Station station: stations) {
                JsonObject stationJsonObject = new JsonObject();
                JsonObject transferJsonObject = new JsonObject();
                stationJsonObject.addProperty("name", station.getName());
                if (station.getTransfer() != null) {
                    transferJsonObject.addProperty("line", station.getTransfer().getLine());
                    transferJsonObject.addProperty("station", station.getTransfer().getStation());
                    stationJsonObject.add("transfer", transferJsonObject);

                } else {
                    stationJsonObject.add("transfer", null);
                }

                if (station.getLine().getLineName().equals(line.getLineName())) {
                    stationWithNumberJsonObject.add(String.valueOf(station.getNumber()), stationJsonObject);
                    System.out.println(stationWithNumberJsonObject);
                }
            }
            metroJsonObject.add(line.getLineName(), stationWithNumberJsonObject);
        }
        return metroJsonObject;
    }
}
