import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class DequeDemo {
    public static void main(String[] args) {
        Queue<String> upNext = new ArrayDeque<>();
        upNext.offer("Static");
        upNext.offer("Echo Park");
        upNext.offer("Slow Burn");
        System.out.println("plays " + upNext.poll() + ", then " + upNext.peek());

        Deque<String> history = new ArrayDeque<>();
        history.push("Static");
        history.push("Echo Park");
        history.push("Slow Burn");
        System.out.println("back to " + history.pop() + ", then " + history.pop());
    }
}
