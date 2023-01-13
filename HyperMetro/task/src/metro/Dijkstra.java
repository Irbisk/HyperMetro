package metro;

import metro.allLines.Line;
import metro.allLines.Station;
import metro.allLines.Transfer;
import static metro.Programm.stationsList;
import java.util.*;

public class Dijkstra {
    private Station startStation;
    Queue<Station> queue = new PriorityQueue<>();



    public Dijkstra(Station startStation) {
        this.startStation = startStation;
    }

    public void compute() {
        clearPreviousStations();
        startStation.setDistance(0);
        queue.add(startStation);

        while (!queue.isEmpty()) {
            Station actualStation = queue.peek();

            Set<Station> set = actualStation.getChildren();

            for (Station childStation : set) {
                if (!childStation.isVisited()) {
                    if (actualStation.getLine().getLineName().equals(childStation.getLine().getLineName())) {
                        if (actualStation.getNumber() < childStation.getNumber()) {
                            if (actualStation.getDistance() + actualStation.getTime() < childStation.getDistance()) {
                                childStation.setDistance(actualStation.getDistance() + actualStation.getTime());
                                childStation.setPrevious(actualStation);
                                queue.add(childStation);
                            }
                        } else {
                            int num = actualStation.getNumber() - 1;
                            for (Station station : stationsList) {
                                if (station.getNumber() == num && station.getLine().getLineName().equals(actualStation.getLine().getLineName())) {
                                    if (actualStation.getDistance() + station.getTime() < childStation.getDistance()) {
                                        childStation.setDistance(actualStation.getDistance() + station.getTime());
                                        childStation.setPrevious(actualStation);
                                        queue.add(childStation);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        String name = actualStation.getName();


                        for (Station station: stationsList) {
                            if (station.getName().equals(name) && station.getLine().getLineName().equals(childStation.getLine().getLineName())) {


                                if (station.getNumber() < childStation.getNumber()) {
                                    if (actualStation.getDistance() + station.getTime() + 5 < childStation.getDistance()) {
                                        childStation.setDistance(actualStation.getDistance() + station.getTime() + 5);
                                        childStation.setPrevious(actualStation);
                                        queue.add(childStation);
                                    }
                                } else {
                                    int num = station.getNumber() - 1;
                                    for (Station station1 : stationsList) {
                                        if (station1.getNumber() == num && station1.getLine().getLineName().equals(station.getLine().getLineName())) {
                                            if (actualStation.getDistance() + station1.getTime() + 5 < childStation.getDistance()) {
                                                childStation.setDistance(actualStation.getDistance() + station1.getTime() + 5);
                                                childStation.setPrevious(actualStation);
                                                queue.add(childStation);

                                            }
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            queue.poll();
            actualStation.setVisited(true);

        }
    }


    public void shortestPAth(Station endStation) {
        List<Station> route = new ArrayList<>();
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
                System.out.println(route.get(i - 1).getTransfer().getStation());
            }
            System.out.println(route.get(i).getName());
        }
        System.out.println("Total: " + totalTime + " minutes in the way");


    }

    private void clearPreviousStations() {
        stationsList.forEach(x -> x.setPrevious(null));
        stationsList.forEach(x -> x.setVisited(false));
        stationsList.forEach(x -> x.setDistance(Integer.MAX_VALUE));
    }


}
