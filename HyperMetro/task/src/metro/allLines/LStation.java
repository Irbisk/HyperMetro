package metro.allLines;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static metro.Programm.londonStations;


public class LStation implements Comparable<LStation> {
    private String name;
    private Line line;
    private Transfer[] transfers;
    private LStation[] prev;
    private LStation[] next;
    private int time;

    private int distance = Integer.MAX_VALUE;
    boolean visited;
    private LStation previous;

    public LStation getPrevious() {
        return previous;
    }

    public void setPrevious(LStation previous) {
        this.previous = previous;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LStation(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public LStation(String name, Line line, int time) {
        this.name = name;
        this.line = line;
        this.time = time;
    }

    public Set<LStation> getChildren() {
        Set<LStation> children = new HashSet<>();
        Transfer[] transfers = this.getTransfers();

        if (transfers != null) {
            for (Transfer transfer: transfers) {
                String name = transfer.getStation();
                String lineName = transfer.getLine();

                for (LStation lStation: londonStations) {
                    if (lStation.getName().equals(name) && lStation.getLine().getLineName().equals(lineName)) {
                        children.add(lStation);
                    }
                }
            }
        }



        if (prev != null) {
            children.addAll(Arrays.asList(prev));
        }
        if (next != null) {
            children.addAll(Arrays.asList(next));
        }
        return children;
    }

    public String getName() {
        return name;
    }

    public Line getLine() {
        return line;
    }

    public void setTransfers(Transfer[] transfers) {
        this.transfers = transfers;
    }

    public LStation[] getPrev() {
        return prev;
    }

    public LStation[] getNext() {
        return next;
    }

    public Transfer[] getTransfers() {
        return transfers;
    }

    @Override
    public String toString() {
        String next = "";
        String prev = "";
        String transfer = "";
        int p = 1;
        int n = 1;
        int t = 1;
        if (this.getPrev() != null) {
            for (LStation station: this.getPrev()) {
                prev += "\n\tPrev №" + p + ": Name: " + station.getName() + " Line: " + station.getLine().getLineName();
                p++;
            }
        }
        if (this.getNext() != null) {
            for (LStation station: this.getNext()) {
                next += "\n\tNext №" + n + ": Name: " + station.getName() + " Line: " + station.getLine().getLineName();
                n++;
            }
        }
        if (this.getTransfers() != null) {
            for (Transfer station: this.getTransfers()) {
                transfer += "\n\tTransfer №" + t + ": Name: " + station.getStation() + " Line: " + station.getLine();
                t++;
            }
        }


        String result = "Name: " + this.getName() + "\nLine: " + this.getLine().getLineName() + prev + next + transfer + "\nTime: " + this.time;

        return result;
    }

    public void setNext(LStation[] next) {
        this.next = next;
    }

    public void setPrev(LStation[] prev) {
        this.prev = prev;
    }

    @Override
    public int compareTo(LStation lstation) {
        return this.distance - lstation.distance;
    }
}
