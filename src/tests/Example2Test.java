package tests;

import ai181.kozyrevych.example2.Loader;
import ai181.kozyrevych.example2.Mirror;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Example2Test {
    Loader loader;

    @Test
    @DisplayName("Loader setName method test")
    public void setMirror() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        Mirror mirror = new Mirror("UserX", stack);
        loader = new Loader(mirror);

        loader.setMirror();
        assertEquals("[1, 2, 3, 4, 4, 3, 2, 1]", mirror.getStack().toString());
    }

    @Test
    @DisplayName("Loader getAllMirrors method test")
    public void getMirrors() {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(5);
        stack.push(6);

        Mirror mirror = new Mirror("UserY", stack);
        loader = new Loader(mirror);

        loader.setMirror();

        assertEquals("UserJ=[4, 2, 6, 6, 2, 4]\n" +
                "UserX=[1, 2, 3, 4, 4, 3, 2, 1]\n" +
                "UserY=[4, 5, 6, 6, 5, 4]", loader.getAllMirrors());
    }

    @Test
    @DisplayName("Loader sort method test")
    public void sort() {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(2);
        stack.push(6);

        Mirror mirror = new Mirror("UserJ", stack);
        loader = new Loader(mirror);

        loader.setMirror();

        Deque<String> deque = loader.getSortedUsers();
        Deque<String> dequeCopy = new ArrayDeque<>();
        int size = deque.size();
        String s = "";

        for (int i = 0; i < size; i++) {
            s += deque.peekFirst() + "\n";
            dequeCopy.push(deque.pollFirst());
        }

        assertEquals("UserJ\n" +
                "UserX\n" +
                "UserY\n", s, "sort doesn't work");

        s = "";

        for (int i = 0; i < size; i++) {
            s += dequeCopy.pop() + "\n";
        }

        assertEquals("UserY\n" +
                        "UserX\n" +
                        "UserJ\n",
                s, "sort doesn't work");

        TreeSet<String> set = new TreeSet<>(loader.getUsersUnordered());

        assertEquals("UserJ\n" +
                "UserX\n" +
                "UserY", String.join("\n", set));
    }
}
