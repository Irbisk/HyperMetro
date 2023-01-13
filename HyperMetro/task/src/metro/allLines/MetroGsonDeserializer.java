package metro.allLines;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class MetroGsonDeserializer implements JsonDeserializer<Metro> {

    @Override
    public Metro deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Station> stations = new LinkedList<>();
        List<Line> lines = new LinkedList<>();
        JsonObject metroJsonObject;
        JsonObject stationsWithNumberJsonObject;
        JsonObject stationJsonObject;
        JsonArray transferJsonObject;

        metroJsonObject = json.getAsJsonObject();

        for (var entry: metroJsonObject.entrySet()) {
            lines.add(new Line(entry.getKey()));
        }

        for (Line line: lines) {
            stationsWithNumberJsonObject = metroJsonObject.get(line.getLineName()).getAsJsonObject();
            for (var entry: stationsWithNumberJsonObject.entrySet()) {
                stationJsonObject = entry.getValue().getAsJsonObject();
                String name = stationJsonObject.get("name").getAsString();
                int time = 0;

                if (stationJsonObject.has("time")) {
                    if (!stationJsonObject.get("time").isJsonNull()) {
                        time = stationJsonObject.get("time").getAsInt();
                    }
                }
                if (stationJsonObject.get("transfer").getAsJsonArray().isEmpty()) {
                    stations.add(new Station(name, new Transfer(), line, Integer.parseInt(entry.getKey()), time));
                } else {
                    transferJsonObject = stationJsonObject.get("transfer").getAsJsonArray();
                    String transferLine = transferJsonObject.get(0).getAsJsonObject().get("line").getAsString();
                    String transferStation = transferJsonObject.get(0).getAsJsonObject().get("station").getAsString();
                    stations.add(new Station(name, new Transfer(transferLine, transferStation), line, Integer.parseInt(entry.getKey()), time));
                }
            }
        }

        Metro metro = new Metro();
        metro.setStations(stations);

        return metro;
    }
}
