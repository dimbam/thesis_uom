public class GUIExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton button = new JButton("Click me!");
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Button clicked!");
        });

        panel.add(button);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
