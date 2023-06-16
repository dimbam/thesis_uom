public class GUIExample {
    public static void main(String[] args) {
        Frame frame = new Frame("My GUI");

        Label label = new Label("Enter your name:");
        TextField textField = new TextField(20);
        Button button = new Button("Click me!");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                System.out.println("Hello, " + name + "!");
            }
        });

        Panel panel = new Panel(new FlowLayout());
        panel.add(label);
        panel.add(textField);
        panel.add(button);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
