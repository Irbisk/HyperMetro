package metro.allLines;

import java.util.*;
import java.util.logging.Level;

import static metro.Programm.londonStations;

public class BreadthL {

    private LStation startStation;
    private LStation endStation;


    public BreadthL(LStation startStation, LStation endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }


    public void compute() {
        clearPreviousStations();

        Queue<LStation> queue = new LinkedList<>();
        startStation.setVisited(true);
        queue.add(startStation);

        while (!queue.isEmpty()) {
            LStation currentStation = queue.poll();
            Set<LStation> stationSet = currentStation.getChildren();
            for (LStation lStation: stationSet) {
                if (!lStation.isVisited()) {
                    lStation.setVisited(true);
                    queue.add(lStation);
                    lStation.setPrevious(currentStation);
                    if (lStation.equals(endStation)) {
                        queue.clear();
                        break;
                    }
                }

            }
        }
    }

    public void trace_route() {
        LStation station = endStation;
        List<LStation> route = new ArrayList<>();
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
            }
            System.out.println(route.get(i).getName());
        }
    }

    private void clearPreviousStations() {
        londonStations.forEach(x -> x.setPrevious(null));
    }

}
