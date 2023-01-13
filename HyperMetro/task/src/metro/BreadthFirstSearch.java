package metro;

import metro.allLines.Line;
import metro.allLines.Station;

import java.util.*;
import java.util.stream.Collectors;

import static metro.Programm.stationsList;


public class BreadthFirstSearch {
    private Station startStation;
    private Station endStation;


    public BreadthFirstSearch(Station startStation, Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }


    public Set<Station> compute() {
        clearPreviousStations();

        if (this.startStation.equals(endStation)) {
            System.out.println("Completed");
        }

        Queue<Station> queue = new LinkedList<>();
        Set<Station> explored = new LinkedHashSet<>();
        queue.add(startStation);


        while (!queue.isEmpty()) {
            Station current = queue.remove();
            if (current.equals(endStation)) {
                break;
            } else {
                Set<Station> set = current.getChildren();
                set.removeAll(explored);
                if (!set.isEmpty()) {
                    queue.addAll(set);
                    set.forEach(x -> x.setPrevious(current));
                }

            }
            explored.add(current);
        }
        return explored;
    }

    public void trace_route() {
        Station station = endStation;
        List<Station> route = new ArrayList<>();
        while (station != null) {
            route.add(station);
            station = station.getPrevious();
        }
        Collections.reverse(route);

        System.out.println(route.get(0).getName());
        for (int i = 1; i < route.size(); i++) {
            Line line = route.get(i).getLine();
            if (!line.equals(route.get(i - 1).getLine())) {
                System.out.println("Transition to line " + line.getLineName());
                System.out.println(route.get(i - 1).getTransfer().getStation());
            }
            System.out.println(route.get(i).getName());
        }
    }

    private void clearPreviousStations() {
        stationsList.forEach(x -> x.setPrevious(null));
        stationsList.forEach(x -> x.setVisited(false));
        stationsList.forEach(x -> x.setDistance(Integer.MAX_VALUE));
    }
}
