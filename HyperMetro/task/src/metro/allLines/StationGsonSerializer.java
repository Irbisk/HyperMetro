package metro.allLines;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import metro.allLines.Station;

import java.lang.reflect.Type;

public class StationGsonSerializer implements JsonSerializer<Station> {
    @Override
    public JsonElement serialize(Station src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject stationJsonObj = new JsonObject();
        JsonObject transferJsonObj = new JsonObject();

        String name = src.getName();
        String transferLine = src.getTransfer().getLine();
        String transferStation = src.getTransfer().getStation();

        transferJsonObj.addProperty("line", transferLine);
        transferJsonObj.addProperty("station", transferStation);




        stationJsonObj.addProperty("name", name);
        stationJsonObj.add("transfer", transferJsonObj);

        return stationJsonObj;
    }
}
