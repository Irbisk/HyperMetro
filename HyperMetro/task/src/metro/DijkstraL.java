package metro;

import metro.allLines.LStation;
import metro.allLines.Line;
import metro.allLines.Station;
import metro.allLines.Transfer;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static metro.Programm.stationsList;

public class DijkstraL {
    private LStation startStation;
    Queue<LStation> queue = new PriorityQueue<>();

    public DijkstraL (LStation startStation) {
        this.startStation = startStation;
    }

    public void computePAth() {
        clearPreviousStations();
        startStation.setDistance(0);
        queue.add(startStation);

        while (!queue.isEmpty()) {
            LStation actualStation = queue.peek();

            Set<LStation> set = actualStation.getChildren();

            for (LStation childStation: set) {
                if (!childStation.isVisited()) {
                    if (actualStation.getLine().getLineName().equals(childStation.getLine().getLineName())) {
                        boolean previous = false;
                        int actualTime;
                        LStation[] stations = actualStation.getPrev();
                        if (stations != null) {
                            for (LStation station: stations) {
                                if (station.equals(childStation)) {
                                    previous = true;
                                    break;
                                }
                            }
                        }
                        if (previous) {
                            actualTime = childStation.getTime();
                        } else {
                            actualTime = actualStation.getTime();
                        }

                        if (actualStation.getDistance() + actualTime < childStation.getDistance()) {
                            childStation.setDistance(actualStation.getDistance() + actualTime);
                            childStation.setPrevious(actualStation);
                            queue.add(childStation);
                        }
                    } else {
                        if (actualStation.getDistance() + 5  < childStation.getDistance()) {
                            childStation.setDistance(actualStation.getDistance() + 5);
                            childStation.setPrevious(actualStation);
                            queue.add(childStation);
                        }
                    }
                }
            }
            queue.poll();
            actualStation.setVisited(true);
        }
    }

    public void shortestPAth(LStation endStation) {
        Logger logger = Logger.getLogger(Main.class.getName());
        List<LStation> route = new ArrayList<>();
        int totalTime = endStation.getDistance();
        route.add(endStation);
        while (endStation.getPrevious() != null) {
            route.add(endStation.getPrevious());
            endStation = endStation.getPrevious();
        }

        Collections.reverse(route);
        System.out.println(route.get(0).getName());
        for (int i = 1; i < route.size(); i++) {
            Line line = route.get(i).getLine();
            if (!line.equals(route.get(i - 1).getLine())) {
                System.out.println("Transition to line " + line.getLineName());
            }
            System.out.println(route.get(i).getName());
            logger.log(Level.INFO, String.valueOf(route.get(i).getDistance()));

        }
        System.out.println("Total: " + totalTime + " minutes in the way");

        logger.log(Level.INFO, String.valueOf(totalTime));


    }

    private void clearPreviousStations() {
        stationsList.forEach(x -> x.setPrevious(null));
        stationsList.forEach(x -> x.setVisited(false));
        stationsList.forEach(x -> x.setDistance(Integer.MAX_VALUE));
    }

}
