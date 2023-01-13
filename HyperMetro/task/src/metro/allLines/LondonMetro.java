package metro.allLines;

import java.util.List;

public class LondonMetro {
    private List<LStation> londonStations;

    public LondonMetro() {
    }

    public void setLondonStations(List<LStation> londonStations) {
        this.londonStations = londonStations;
    }

    public List<LStation> getLondonStations() {
        return londonStations;
    }
}
