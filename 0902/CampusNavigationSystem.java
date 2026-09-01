import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CampusNavigationSystem {

    static class Place {
        private final String id;
        private final String name;

        public Place(String id, String name) {
            if (id == null || id.trim().isEmpty()
                    || name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id + "(" + name + ")";
        }
    }

    private final Map<String, Place> places = new HashMap<>();
    private final Map<String, List<String>> roads = new HashMap<>();

    public boolean addPlace(String id, String name) {
        if (id == null || name == null) {
            return false;
        }

        id = id.trim();
        name = name.trim();

        if (id.isEmpty() || name.isEmpty()
                || places.containsKey(id)) {
            return false;
        }

        places.put(id, new Place(id, name));
        roads.put(id, new ArrayList<>());

        return true;
    }

    public boolean addRoad(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        a = a.trim();
        b = b.trim();

        if (!places.containsKey(a)
                || !places.containsKey(b)
                || a.equals(b)) {
            return false;
        }

        if (roads.get(a).contains(b)) {
            return false;
        }

        roads.get(a).add(b);
        roads.get(b).add(a);

        return true;
    }

    public Place findPlace(String id) {
        if (id == null) {
            return null;
        }

        return places.get(id.trim());
    }

    public List<String> shortestPath(
            String start, String end) {

        List<String> empty = new ArrayList<>();

        if (start == null || end == null) {
            return empty;
        }

        start = start.trim();
        end = end.trim();

        if (!places.containsKey(start)
                || !places.containsKey(end)) {
            return empty;
        }

        Queue<String> queue = new ArrayDeque<>();
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        for (String id : places.keySet()) {
            visited.put(id, false);
        }

        queue.offer(start);
        visited.put(start, true);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            for (String next : roads.get(current)) {
                if (!visited.get(next)) {
                    visited.put(next, true);
                    parent.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.get(end)) {
            return empty;
        }

        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    public void printPath(String start, String end) {
        List<String> path = shortestPath(start, end);

        System.out.println(
                "Navigation: " + start + " -> " + end
        );

        if (path.isEmpty()) {
            System.out.println("No path");
            return;
        }

        List<String> names = new ArrayList<>();

        for (String id : path) {
            names.add(places.get(id).getName());
        }

        System.out.println(
                "Path: " + String.join(" -> ", names)
        );

        System.out.println(
                "Edge count: " + (path.size() - 1)
        );
    }

    public void printRoads() {
        List<String> ids =
                new ArrayList<>(places.keySet());

        Collections.sort(ids);

        for (String id : ids) {
            List<String> neighbors =
                    new ArrayList<>(roads.get(id));

            Collections.sort(neighbors);

            System.out.println(
                    places.get(id) + " -> " + neighbors
            );
        }
    }

    public static void main(String[] args) {

        CampusNavigationSystem campus =
                new CampusNavigationSystem();

        campus.addPlace("A", "Main Gate");
        campus.addPlace("B", "Library");
        campus.addPlace("C", "Cafeteria");
        campus.addPlace("D", "Computer Center");
        campus.addPlace("E", "Gym");
        campus.addPlace("F", "Dormitory");
        campus.addPlace("G", "Parking");

        campus.addRoad("A", "B");
        campus.addRoad("A", "C");
        campus.addRoad("B", "D");
        campus.addRoad("C", "D");
        campus.addRoad("C", "E");
        campus.addRoad("D", "F");
        campus.addRoad("E", "F");

        campus.printRoads();

        System.out.println();

        campus.printPath("A", "F");

        System.out.println();

        campus.printPath("B", "E");

        System.out.println();

        campus.printPath("A", "G");
    }
}