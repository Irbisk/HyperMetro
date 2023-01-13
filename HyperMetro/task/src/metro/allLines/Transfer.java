package metro.allLines;

public class Transfer {
    private String line;
    private String station;

    public Transfer(String line, String station) {
        this.line = line;
        this.station = station;
    }

    public Transfer() {
        this.line = null;
        this.station = null;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLine() {
        return line;
    }

    public String getStation() {
        return station;
    }
}
