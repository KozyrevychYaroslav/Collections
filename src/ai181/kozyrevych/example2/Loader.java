package ai181.kozyrevych.example2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Loader {
    private Properties properties = new Properties();
    private Mirror mirror;

    public Loader(Mirror mirror) {
        this.mirror = mirror;
    }

    public void setMirror() {
        Stack<Integer> stack = mirror.getStack();
        Queue<Integer> q = new LinkedList<>(stack);

        try (FileInputStream fin = new FileInputStream("example2File.txt")) {
            properties.load(fin);

            while (!stack.isEmpty()) {
                q.offer(stack.pop());
            }
            while (!q.isEmpty()) {
                stack.push(q.poll());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileOutputStream fout = new FileOutputStream("example2File.txt", false)) {
            properties.put(mirror.getUserName(), stack.toString());
            properties.store(fout, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getAllMirrors() {
        try (FileInputStream fin = new FileInputStream("example2File.txt");) {
            properties.load(fin);
            return properties.keySet().stream().sorted().
                    map(o -> o.toString() + '=' + properties.getProperty((String) o)).
                    collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public Deque<String> getSortedUsers() {
        Deque<String> users = properties.keySet().stream().
                map(Object::toString).
                sorted().
                collect(
                        Collectors.toCollection(ArrayDeque::new)
                );
        return users;
    }

    public Deque<String> getUsersUnordered() {
        Deque<String> users = properties.keySet().stream().unordered().
                map(Object::toString).
                collect(
                        Collectors.toCollection(ArrayDeque::new)
                );
        return users;
    }


}
