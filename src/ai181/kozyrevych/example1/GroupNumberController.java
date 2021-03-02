package ai181.kozyrevych.example1;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupNumberController {
    private static Set<Group> groups = new HashSet<>();

    public static void addGroup(Group group, String number) {
        boolean contains;

        contains = groups.stream().anyMatch(i -> i.getGroupNumber().equals(number));

        if (contains) {
            throw new IllegalArgumentException("Группа уже существует");
        }
        groups.add(group);
    }

    public static String getSortedGroups() {
        return groups.stream().sorted().map(Group::toString).collect(Collectors.joining());
    }

    public static String getGroups() {
        return groups.stream().map(Group::toString).collect(Collectors.joining());
    }

}
