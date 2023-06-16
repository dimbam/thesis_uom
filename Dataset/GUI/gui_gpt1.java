public class GUIExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello, world!");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }
}
