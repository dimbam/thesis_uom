public class GUIExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Enter your name:");
        panel.add(label);

        JTextField textField = new JTextField(20);
        panel.add(textField);

        JButton button = new JButton("Click me!");
        button.addActionListener(e -> {
            String name = textField.getText();
            JOptionPane.showMessageDialog(frame, "Hello, " + name + "!");
        });

        panel.add(button);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
