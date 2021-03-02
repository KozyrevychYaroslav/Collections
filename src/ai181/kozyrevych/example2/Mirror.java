package ai181.kozyrevych.example2;

import java.util.Stack;

public class Mirror {
    private String userName = "";
    private Stack<Integer> stack = new Stack<>();

    public Mirror(String userName, Stack<Integer> stack) {
        this.userName = userName;
        this.stack = stack;
    }

    public String getUserName() {
        return userName;
    }

    public Stack<Integer> getStack() {
        return stack;
    }
}
