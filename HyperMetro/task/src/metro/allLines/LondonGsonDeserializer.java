package metro.allLines;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;


public class LondonGsonDeserializer implements JsonDeserializer<LondonMetro> {
    List<LStation> stations = new LinkedList<>();

    @Override
    public LondonMetro deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<Line> lines = new LinkedList<>();

        JsonObject metroJsonObject;
        JsonArray stationsJsonArray;
        JsonArray prevStationsJsonArray;
        JsonArray nextStationsJsonArray;
        JsonArray transferStationsJsonArray;
        JsonObject stationJsonObject;


        metroJsonObject = json.getAsJsonObject();

        for (var entry: metroJsonObject.entrySet()) {
            lines.add(new Line(entry.getKey()));
        }

        for (Line line: lines) {
            stationsJsonArray = metroJsonObject.get(line.getLineName()).getAsJsonArray();
            for (JsonElement stationsJsonElement: stationsJsonArray) {
                stationJsonObject = stationsJsonElement.getAsJsonObject();
                String name = stationJsonObject.get("name").getAsString();
                int time = 0;
                if (stationJsonObject.has("time")) {
                    time = stationJsonObject.get("time").getAsInt();
                }
                stations.add(new LStation(name, line, time));
            }
        }

        for (Line line: lines) {
            stationsJsonArray = metroJsonObject.get(line.getLineName()).getAsJsonArray();
            for (JsonElement stationsJsonElement: stationsJsonArray) {
                stationJsonObject = stationsJsonElement.getAsJsonObject();
                String name = stationJsonObject.get("name").getAsString();

                if (!stationJsonObject.get("prev").getAsJsonArray().isEmpty()) {
                    List<LStation> prevs = new ArrayList<>();
                    prevStationsJsonArray = stationJsonObject.getAsJsonArray("prev");
                    for (JsonElement station: prevStationsJsonArray) {
                        String prev = station.getAsString();
                        prevs.add(getStationByName(prev, line.getLineName()));
                    }
                    setPrevs(name, line.getLineName(), prevs);
                }

                if (!stationJsonObject.get("next").getAsJsonArray().isEmpty()) {
                    List<LStation> nexts = new ArrayList<>();
                    nextStationsJsonArray = stationJsonObject.getAsJsonArray("next");
                    for (JsonElement station: nextStationsJsonArray) {
                        String next = station.getAsString();
                        nexts.add(getStationByName(next, line.getLineName()));
                    }
                    setNexts(name, line.getLineName(), nexts);
                }
                if (stationJsonObject.get("transfer").getAsJsonArray().isEmpty()) {

                } else {
                    List<Transfer> transfers = new ArrayList<>();
                    transferStationsJsonArray = stationJsonObject.get("transfer").getAsJsonArray();
                    for (JsonElement transferJsonElement: transferStationsJsonArray) {
                        JsonObject transferJsonObject = transferJsonElement.getAsJsonObject();
                        String transferLine = transferJsonObject.get("line").getAsString();
                        String transferStation = transferJsonObject.get("station").getAsString();
                        Transfer transfer = new Transfer(transferLine, transferStation);
                        transfers.add(transfer);
                    }
                    setTransfers(name, line.getLineName(), transfers);
                }
            }
        }

        LondonMetro londonMetro = new LondonMetro();
        londonMetro.setLondonStations(stations);

        return londonMetro;
    }

    private void setTransfers(String name, String lineName, List<Transfer> transfers) {
        for (LStation lStation : stations) {
            if (name.equals(lStation.getName()) && lineName.equals(lStation.getLine().getLineName())) {
                if (transfers == null) {
                    Transfer[] array = {new Transfer()};
                    lStation.setTransfers(array);
                } else {
                    lStation.setTransfers(transfers.toArray(new Transfer[0]));
                }
                break;
            }
        }




    }

    private LStation getStationByName(String name, String lineName) {
        LStation station = null;
        for (LStation lStation: stations) {
            if (name.equals(lStation.getName()) && lineName.equals(lStation.getLine().getLineName())) {
                station = lStation;
                break;
            }
        }
        return station;
    }

    private void setPrevs (String name, String lineName, List<LStation> prevsList) {
        for (LStation lStation: stations) {
            if (name.equals(lStation.getName()) && lineName.equals(lStation.getLine().getLineName())) {
                lStation.setPrev(prevsList.toArray(prevsList.toArray(new LStation[0])));
                break;
            }
        }
    }

    private void setNexts (String name, String lineName, List<LStation> nextList) {
        for (LStation lStation: stations) {
            if (name.equals(lStation.getName()) && lineName.equals(lStation.getLine().getLineName())) {
                lStation.setNext(nextList.toArray(nextList.toArray(new LStation[0])));
                break;
            }
        }
    }
}
