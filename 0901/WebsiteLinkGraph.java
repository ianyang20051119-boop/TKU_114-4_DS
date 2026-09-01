import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebsiteLinkGraph {

    private final Map<String, Set<String>> graph =
            new HashMap<>();

    public boolean addPage(String page) {
        if (page == null || page.trim().isEmpty()) {
            return false;
        }

        page = page.trim();

        if (graph.containsKey(page)) {
            return false;
        }

        graph.put(page, new HashSet<>());
        return true;
    }

    public boolean addLink(String from, String to) {
        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)
                || from.equals(to)) {
            return false;
        }

        return graph.get(from).add(to);
    }

    public List<String> outgoingLinks(String page) {
        List<String> result = new ArrayList<>();

        if (page == null) {
            return result;
        }

        page = page.trim();

        if (!graph.containsKey(page)) {
            return result;
        }

        result.addAll(graph.get(page));
        result.sort(String::compareTo);

        return result;
    }

    public int incomingCount(String page) {
        if (page == null) {
            return -1;
        }

        page = page.trim();

        if (!graph.containsKey(page)) {
            return -1;
        }

        int count = 0;

        for (Set<String> links : graph.values()) {
            if (links.contains(page)) {
                count++;
            }
        }

        return count;
    }

    public List<String> noIncomingPages() {
        List<String> result = new ArrayList<>();

        for (String page : graph.keySet()) {
            if (incomingCount(page) == 0) {
                result.add(page);
            }
        }

        result.sort(String::compareTo);
        return result;
    }

    public List<String> noOutgoingPages() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry
                : graph.entrySet()) {

            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }

        result.sort(String::compareTo);
        return result;
    }

    public void printReport() {
        List<String> pages =
                new ArrayList<>(graph.keySet());

        pages.sort(String::compareTo);

        for (String page : pages) {
            System.out.println(
                    page
                    + " outgoing=" + outgoingLinks(page)
                    + " incoming=" + incomingCount(page)
            );
        }

        System.out.println(
                "No incoming: " + noIncomingPages()
        );

        System.out.println(
                "No outgoing: " + noOutgoingPages()
        );
    }

    public static void main(String[] args) {

        WebsiteLinkGraph website =
                new WebsiteLinkGraph();

        website.addPage("Home");
        website.addPage("About");
        website.addPage("Products");
        website.addPage("Contact");
        website.addPage("Blog");
        website.addPage("Help");

        website.addLink("Home", "About");
        website.addLink("Home", "Products");
        website.addLink("Home", "Blog");

        website.addLink("About", "Contact");

        website.addLink("Products", "Contact");
        website.addLink("Products", "Help");

        website.addLink("Blog", "Products");

        System.out.println(
                "duplicate link: "
                + website.addLink("Home", "About")
        );

        System.out.println();

        website.printReport();
    }
}