public class StackTest {

    private Stack<String> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPushAndPeek() {
        stack.push("apple");
        assertEquals("apple", stack.peek());
    }

    @Test
    public void testPop() {
        stack.push("apple");
        stack.push("banana");
        assertEquals("banana", stack.pop());
        assertEquals("apple", stack.peek());
    }

    @Test
    public void testEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("apple");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push("apple");
        stack.push("banana");
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }
}

class Stack<T> {

    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public T peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {

        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }
}