import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {

    private final Map<String, Set<String>> graph = new HashMap<>();

    public boolean addUser(String user) {
        if (user == null || user.trim().isEmpty()) {
            return false;
        }

        user = user.trim();

        if (graph.containsKey(user)) {
            return false;
        }

        graph.put(user, new HashSet<>());
        return true;
    }

    public boolean addFriend(String user1, String user2) {
        if (user1 == null || user2 == null) {
            return false;
        }

        user1 = user1.trim();
        user2 = user2.trim();

        if (!graph.containsKey(user1)
                || !graph.containsKey(user2)
                || user1.equals(user2)) {
            return false;
        }

        if (graph.get(user1).contains(user2)) {
            return false;
        }

        graph.get(user1).add(user2);
        graph.get(user2).add(user1);

        return true;
    }

    public boolean removeFriend(String user1, String user2) {
        if (user1 == null || user2 == null) {
            return false;
        }

        user1 = user1.trim();
        user2 = user2.trim();

        if (!graph.containsKey(user1)
                || !graph.containsKey(user2)) {
            return false;
        }

        if (!graph.get(user1).contains(user2)) {
            return false;
        }

        graph.get(user1).remove(user2);
        graph.get(user2).remove(user1);

        return true;
    }

    public Set<String> mutualFriends(String user1, String user2) {
        Set<String> result = new HashSet<>();

        if (user1 == null || user2 == null) {
            return result;
        }

        user1 = user1.trim();
        user2 = user2.trim();

        if (!graph.containsKey(user1)
                || !graph.containsKey(user2)) {
            return result;
        }

        result.addAll(graph.get(user1));
        result.retainAll(graph.get(user2));

        return result;
    }

    public List<String> isolatedUsers() {
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

    public Set<String> friendsOf(String user) {
        Set<String> result = new HashSet<>();

        if (user == null) {
            return result;
        }

        user = user.trim();

        if (graph.containsKey(user)) {
            result.addAll(graph.get(user));
        }

        return result;
    }

    public static void main(String[] args) {
        SocialNetworkGraph network =
                new SocialNetworkGraph();

        network.addUser("Amy");
        network.addUser("Ben");
        network.addUser("Cara");
        network.addUser("David");
        network.addUser("Eva");
        network.addUser("Frank");

        System.out.println(
                "Amy-Ben: "
                + network.addFriend("Amy", "Ben")
        );

        System.out.println(
                "Amy-Cara: "
                + network.addFriend("Amy", "Cara")
        );

        System.out.println(
                "Ben-Cara: "
                + network.addFriend("Ben", "Cara")
        );

        System.out.println(
                "Ben-David: "
                + network.addFriend("Ben", "David")
        );

        System.out.println(
                "duplicate Amy-Ben: "
                + network.addFriend("Amy", "Ben")
        );

        System.out.println();

        System.out.println(
                "Amy friends: "
                + network.friendsOf("Amy")
        );

        System.out.println(
                "Ben friends: "
                + network.friendsOf("Ben")
        );

        System.out.println(
                "Amy & Ben mutual: "
                + network.mutualFriends("Amy", "Ben")
        );

        System.out.println(
                "isolated users: "
                + network.isolatedUsers()
        );

        System.out.println();

        System.out.println(
                "remove Amy-Cara: "
                + network.removeFriend("Amy", "Cara")
        );

        System.out.println(
                "remove Amy-Cara again: "
                + network.removeFriend("Amy", "Cara")
        );

        System.out.println();

        System.out.println(
                "Amy friends: "
                + network.friendsOf("Amy")
        );

        System.out.println(
                "Amy & Ben mutual: "
                + network.mutualFriends("Amy", "Ben")
        );

        System.out.println(
                "isolated users: "
                + network.isolatedUsers()
        );
    }
}