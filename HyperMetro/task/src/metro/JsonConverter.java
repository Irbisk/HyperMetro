package metro;

import metro.allLines.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static metro.Programm.londonStations;
import static metro.Programm.stationsList;

public class JsonConverter {
    public static void routeOld (String value) {
        List<String> info = new LinkedList<>();

        Pattern p = Pattern.compile( "\"([^\"]*)\"" );
        Matcher m = p.matcher(value);
        while (m.find()) {
            info.add(m.group(1));
        }

        if (info.size() == 4){
            String line1 = info.get(0);
            String stationName1 = info.get(1);
            String line2 = info.get(2);
            String stationName2 = info.get(3);

            Station station1 = null;
            Station station2 = null;
            boolean found1 = false;
            boolean found2 = false;

            for (Station station: stationsList) {
                if (station.getName().equals(stationName1) && station.getLine().getLineName().equals(line1)) {
                    station1 = station;
                    found1 = true;
                }
                if (station.getName().equals(stationName2) && station.getLine().getLineName().equals(line2)) {
                    station2 = station;
                    found2 = true;
                }
            }
            if (found1 && found2) {
                BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(station1, station2);
                breadthFirstSearch.compute();
                breadthFirstSearch.trace_route();
            }
        }
    }

    public static void fastestRouteOld (String value) {
        List<String> info = new LinkedList<>();

        Pattern p = Pattern.compile( "\"([^\"]*)\"" );
        Matcher m = p.matcher(value);
        while (m.find()) {
            info.add(m.group(1));
        }

        if (info.size() == 4){
            String line1 = info.get(0);
            String stationName1 = info.get(1);
            String line2 = info.get(2);
            String stationName2 = info.get(3);

            Station station1 = null;
            Station station2 = null;
            boolean found1 = false;
            boolean found2 = false;

            for (Station station: stationsList) {
                if (station.getName().equals(stationName1) && station.getLine().getLineName().equals(line1)) {
                    station1 = station;
                    found1 = true;
                }
                if (station.getName().equals(stationName2) && station.getLine().getLineName().equals(line2)) {
                    station2 = station;
                    found2 = true;
                }
            }
            if (found1 && found2) {
                Dijkstra dijkstra = new Dijkstra(station1);
                dijkstra.compute();
                dijkstra.shortestPAth(station2);
            }
        }
    }

    public static void fastestRoute (String value) {
        List<String> info = new LinkedList<>();

        Pattern p = Pattern.compile( "\"([^\"]*)\"" );
        Matcher m = p.matcher(value);
        while (m.find()) {
            info.add(m.group(1));
        }

        if (info.size() == 4){
            String line1 = info.get(0);
            String stationName1 = info.get(1);
            String line2 = info.get(2);
            String stationName2 = info.get(3);

            LStation station1 = null;
            LStation station2 = null;
            boolean found1 = false;
            boolean found2 = false;

            for (LStation lStation: londonStations) {
                if (lStation.getName().equals(stationName1) && lStation.getLine().getLineName().equals(line1)) {
                    station1 = lStation;
                    found1 = true;
                }
                if (lStation.getName().equals(stationName2) && lStation.getLine().getLineName().equals(line2)) {
                    station2 = lStation;
                    found2 = true;
                }
            }
            if (found1 && found2) {
                DijkstraL dijkstraL = new DijkstraL(station1);
                dijkstraL.computePAth();
                dijkstraL.shortestPAth(station2);
            }
        }
    }

    public static void route (String value) {
        List<String> info = new LinkedList<>();

        Pattern p = Pattern.compile( "\"([^\"]*)\"" );
        Matcher m = p.matcher(value);
        while (m.find()) {
            info.add(m.group(1));
        }

        if (info.size() == 4){
            String line1 = info.get(0);
            String stationName1 = info.get(1);
            String line2 = info.get(2);
            String stationName2 = info.get(3);

            LStation station1 = null;
            LStation station2 = null;
            boolean found1 = false;
            boolean found2 = false;

            for (LStation lStation: londonStations) {
                if (lStation.getName().equals(stationName1) && lStation.getLine().getLineName().equals(line1)) {
                    station1 = lStation;
                    found1 = true;
                }
                if (lStation.getName().equals(stationName2) && lStation.getLine().getLineName().equals(line2)) {
                    station2 = lStation;
                    found2 = true;
                }
            }
            if (found1 && found2) {
                BreadthL breadthFirstSearch = new BreadthL(station1, station2);
                breadthFirstSearch.compute();
                breadthFirstSearch.trace_route();
            }
        }
    }



    public static void outputOLD (String value) {
        List<Line> lines = new LinkedList<>();
        List<Station> lineStations = new LinkedList<>();
        List<String> lineNames = new LinkedList<>();

        String lineName = "";
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(value);
        while (m.find()) {
            lineName = m.group(1);
        }

        for (Station station : stationsList) {
            Line line = station.getLine();
            if (!lines.contains(line)) {
                lines.add(line);
                lineNames.add(line.getLineName());
            }
        }

        String finalLineName = lineName;

        if (lineNames.contains(lineName)) {
            stationsList.stream()
                    .filter(x -> x.getLine().getLineName().equals(finalLineName))
                    .forEach(lineStations::add);

            Collections.sort(lineStations, Comparator.comparingInt(Station::getNumber));

            System.out.println("depot");
            for (Station station: lineStations) {
                if (station.getTransfer() == null) {
                    System.out.println(station.getName());
                } else {
                    String mainStation = station.getName();
                    String transferStation = station.getTransfer().getStation();
                    String transferLine = station.getTransfer().getLine();
                    System.out.println(mainStation + " - " + transferStation + " (" + transferLine + " line)");
                }
            }
            System.out.println("depot");
        } else {
            System.out.println("No such line");
        }
    }

    public static void output(String value) {
        List<Line> lines = new LinkedList<>();
        List<LStation> lineStations = new LinkedList<>();
        List<String> lineNames = new LinkedList<>();

        String lineName = "";
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(value);
        while (m.find()) {
            lineName = m.group(1);
        }

        for (LStation station: londonStations) {
            Line line = station.getLine();
            if (!lines.contains(line)) {
                lines.add(line);
                lineNames.add(line.getLineName());
            }
        }
        String finalLineName = lineName;
        if (lineNames.contains(lineName)) {
            londonStations.stream()
                    .filter(x -> x.getLine().getLineName().equals(finalLineName))
                    .forEach(lineStations::add);


            System.out.println("depot");
            LStation lStation = null;
            for (LStation station: lineStations) {
                if (station.getPrev() == null) {
                    System.out.println("NEW");
                    System.out.println(station.getName());
                    recursiveNext(station);
                }
            }
            System.out.println("depot");
        } else {
            System.out.println("No such line");
        }
    }

    private static void recursiveNext(LStation station) {
        if (station.getNext() != null) {
            LStation[] array = station.getNext();
            for (LStation station1: array) {
                System.out.println(station1.getName());
                recursiveNext(station1);
            }
        } else {
            System.out.println("depot");
        }
    }



    public static void connect(String value) {
        List<String> info = new LinkedList<>();

        Pattern p = Pattern.compile( "\"([^\"]*)\"" );
        Matcher m = p.matcher(value);
        while (m.find()) {
            info.add(m.group(1));
        }
        info.stream().forEach(System.out::println);

        if (info.size() == 4){
            String line1 = info.get(0);
            String stationName1 = info.get(1);
            String line2 = info.get(2);
            String stationName2 = info.get(3);

            stationsList.stream()
                    .filter(x -> x.getName().equals(stationName1))
                    .filter(x -> x.getLine().getLineName().equals(line1))
                    .forEach(x -> x.setTransfer(new Transfer(stationName2, line2)));
            stationsList.stream()
                    .filter(x -> x.getName().equals(stationName2))
                    .filter(x -> x.getLine().getLineName().equals(line2))
                    .forEach(x -> x.setTransfer(new Transfer(stationName1, line1)));


        }

    }


    public static void append(String value) {
        List<String> lineAndStation = new LinkedList<>();

        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(value);
        while (m.find()) {
            lineAndStation.add(m.group(1));
        }

        if (lineAndStation.size() == 2) {
            String line = lineAndStation.get(0);
            String stationName = lineAndStation.get(1);

            int count = (int) stationsList.stream()
                    .filter(x -> x.getLine().getLineName().equals(line))
                    .count();
            stationsList.add(new Station(stationName, new Transfer(), new Line(line), ++count));

        } else {
            System.out.println("Wrong names parsing");
        }

    }

    public static void add(String value) {
        List<String> lineAndStation = new LinkedList<>();

        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(value);
        while (m.find()) {
            lineAndStation.add(m.group(1));
        }
        if (lineAndStation.size() == 3) {
            String line = lineAndStation.get(0);
            String stationName = lineAndStation.get(1);
            int time =  Integer.parseInt(lineAndStation.get(2));
            int count = (int) stationsList.stream()
                    .filter(x -> x.getLine().getLineName().equals(line))
                    .count();
            stationsList.add(new Station(stationName, new Transfer(), new Line(line), ++count, time));

        } else {
            System.out.println("Wrong names parsing");
        }

    }



    public static void addHead(String value) {
        List<String> lineAndStation = new LinkedList<>();

        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(value);
        while (m.find()) {
            lineAndStation.add(m.group(1));
        }

        if (lineAndStation.size() == 2) {
            String line = lineAndStation.get(0);
            String stationName = lineAndStation.get(1);

            stationsList.add(new Station(stationName, new Transfer(), new Line(line), 0));
            stationsList.stream()
                    .filter(x -> x.getLine().getLineName().equals(line))
                    .forEach(x -> x.setNumber(x.getNumber() + 1));

        } else {
            System.out.println("Wrong names parsing");
        }

    }

    public static void remove(String value){
        List<String> lineAndStation = new LinkedList<>();

        Pattern p = Pattern.compile("\"([^\",.-]*)\"");
        Matcher m = p.matcher(value);
        while (m.find()) {
            lineAndStation.add(m.group(1));
        }

        if (lineAndStation.size() == 2) {
            String line = lineAndStation.get(0);
            String stationName = lineAndStation.get(1);
            List<Station> stations = stationsList.stream()
                    .filter(x -> x.getLine().getLineName().equals(line))
                    .filter(x -> x.getName().equals(stationName))
                    .collect(Collectors.toList());

            if (stations.size() == 1) {
                Station station = stations.get(0);
                int number = station.getNumber();
                stationsList.remove(station);
                stationsList.stream()
                        .filter(x -> x.getLine().getLineName().equals(line))
                        .filter(x -> x.getNumber() > number)
                        .forEach(x -> x.setNumber(x.getNumber() - 1));
                stationsList.stream()
                        .filter(x -> x.getLine().getLineName().equals(line))
                        .forEach(System.out::println);
            } else {
                System.out.println("Didn find station");
            }

        } else {
            System.out.println("Wrong names parsing");
        }

    }
}
