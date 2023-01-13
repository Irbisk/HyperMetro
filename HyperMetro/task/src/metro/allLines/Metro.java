package metro.allLines;

import java.util.LinkedList;
import java.util.List;

public class Metro {

    private List<Station> stations;


    public Metro() {
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }


    public List<Station> getStations() {
        return stations;
    }
}
