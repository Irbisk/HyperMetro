package metro.allLines;

import javax.xml.stream.events.EntityReference;
import java.util.*;
import java.util.stream.Collectors;

import static metro.Programm.stationsList;

public class Station implements Comparable<Station> {
    private String name;
    private Transfer transfer;
    private Line line;
    private int number;
    private int time;


    int distance = Integer.MAX_VALUE;
    boolean visited;
    private Station previous;

    public int getTime() {
        return time;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setPrevious(Station previous) {
        this.previous = previous;
    }

    public Station getPrevious() {
        return previous;
    }

    public Station(String name) {
        this.name = name;
    }

    public Station(String name, Transfer transfer, Line line, int number) {
        this.name = name;
        this.transfer = transfer;
        this.line = line;
        this.number = number;
    }

    public Station(String name, Transfer transfer, Line line, int number, int time) {
        this.name = name;
        this.transfer = transfer;
        this.line = line;
        this.number = number;
        this.time = time;
    }

    public Station(String name, Line line, int number) {
        this.name = name;
        this.line = line;
        this.number = number;
        this.transfer = null;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Line getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public Transfer getTransfer() {
        if (transfer.getStation() == null && transfer.getLine() == null) {
            return null;
        } else return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public Set<Station> getChildren() {
        Set<Station> children = new HashSet<>();

        String line1 = this.getLine().getLineName();


        List<Station> line1Stations = stationsList.stream()
                .filter(x -> x.getLine().getLineName().equals(line1))
                .sorted(Comparator.comparingInt(Station::getNumber))
                .collect(Collectors.toList());
        int n = line1Stations.indexOf(this);
        if (n == 0 && line1Stations.size() > 1) {
            children.add(line1Stations.get(n + 1));
        } else if (n == line1Stations.size() - 1) {
            children.add(line1Stations.get(n - 1));
        } else {
            children.add(line1Stations.get(n + 1));
            children.add(line1Stations.get(n - 1));
        }

        if (this.getTransfer() != null) {
            String line2 = this.transfer.getLine();
            List<Station> line2Stations = stationsList.stream()
                    .filter(x -> x.getLine().getLineName().equals(line2))
                    .sorted(Comparator.comparingInt(Station::getNumber))
                    .collect(Collectors.toList());

            Station transferStation = null;
            for (Station station: stationsList) {
                if (station.getName().equals(this.getTransfer().getStation()) && station.getLine().getLineName().equals(this.getTransfer().getLine())) {
                    transferStation = station;
                    break;
                }
            }

            n = line2Stations.indexOf(transferStation);
            if (n == 0 && line2Stations.size() > 1) {
                children.add(line2Stations.get(n + 1));
            } else if (n == line2Stations.size() - 1) {
                children.add(line2Stations.get(n - 1));
            } else {
                children.add(line2Stations.get(n + 1));
                children.add(line2Stations.get(n - 1));
            }
        }

        return children;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nLine: " + this.line.getLineName() +
         "\nTime: " + this.time + "\nTransfer: \tline: " + this.transfer.getLine() +
                "\n\tstation: " + this.transfer.getStation();
    }

    @Override
    public int compareTo(Station station) {
        return (this.distance - station.distance);
    }
}
