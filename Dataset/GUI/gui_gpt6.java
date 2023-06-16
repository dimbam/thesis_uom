public class GUIExample extends Application {
    public void start(Stage stage) {
        Label label = new Label("Enter your name:");
        TextField textField = new TextField();
        Button button = new Button("Click me!");
        button.setOnAction(e -> {
            String name = textField.getText();
            System.out.println("Hello, " + name + "!");
        });

        HBox hbox = new HBox(10, label, textField);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(20, hbox, button);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);

        stage.setScene(scene);
        stage.setTitle("My GUI");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
