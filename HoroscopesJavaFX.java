/****************************************************************************
 * HoroscopesJavaFX.java
 ****************************************************************************
 * This is the GUI design for our horoscope program. It creates a window with
 * drop-down menus for the user's birth month and birth day along with a
 * button to run the program. Below the input menus, it displays an image
 * representing the user's star sign and the text of a horoscope.
 * 
 * All images used in this project were taken from http://www.astrology-insight.com/
 *____________________________________________________________________________
 *
 * Jazmin Gering, Victoria Lopez Del Pino, Sean Youngstone
 * November 19, 2019
 * CMSC 255 002
 ****************************************************************************/

// This long list imports all the necessary tools for the code below
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

// Import animation tools (separate for clarity for future manipulation)
import animatefx.animation.BounceInDown;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;

public class HoroscopesJavaFX extends Application {

    // Creates String and Integer arrays of all possible month and day options
    private String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private Integer[] days31 = {1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private Integer[] days30 = {1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    private Integer[] days29 = {1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};

    // Main body of JavaFX program
    @Override
    public void start(Stage primaryStage) {

        // Creates a ComboBox for months and for days (dayBox must use Integer and not int for this to work)
        ComboBox<String> monthBox = new ComboBox<>(FXCollections.observableArrayList(months));
        ComboBox<Integer> dayBox = new ComboBox<>(FXCollections.observableArrayList(days31));

        // Sets default starting values for monthBox and dayBox
        monthBox.setValue("January");
        dayBox.setValue(1);

        // Sets number of days to match the month, and changes day value if out of range
        monthBox.setOnAction(e -> {
            int day = dayBox.getValue();
            if ("February".equals(monthBox.getValue())) {
                dayBox.setItems(FXCollections.observableArrayList(days29));
            }
            else if ("SeptemberAprilJuneNovember".contains(monthBox.getValue())) {
                dayBox.setItems(FXCollections.observableArrayList(days30));
            }
            else {
                dayBox.setItems(FXCollections.observableArrayList(days31));
            }
            dayBox.setValue(Math.min(day, dayBox.getItems().size()));
        });

        // Creates a starting message and button to be integrated into the interface.
        Text horoscope = new Text("Welcome to the Horoscope Wizard! Enter your birthday to learn your destiny.");
        Button computeButton = new Button("Compute Horoscope");

        // Sets up a subpane with both comboboxes, corresponding Labels, and the compute button
        FlowPane inputPane = new FlowPane();
        inputPane.setHgap(5);
        inputPane.getChildren().add(new Label("Enter birth month:"));
        inputPane.getChildren().add(monthBox);
        inputPane.getChildren().add(new Label("Enter birth day:"));
        inputPane.getChildren().add(dayBox);
        inputPane.getChildren().add(computeButton);
        horoscope.setFont(Font.font("Century Gothic", FontWeight.BOLD, 20));
        horoscope.setWrappingWidth(480);
        horoscope.setTextAlignment(TextAlignment.CENTER);

        // Creates a master pane, pads it, and assigns the above elements to occupy top and bottom
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 20, 10));
        pane.setTop(inputPane);
        pane.setBottom(horoscope);

        // Sets colors for the display
        monthBox.setStyle("-fx-color: #da99ff");
        dayBox.setStyle("-fx-color: #da99ff");
        computeButton.setStyle("-fx-color: #da99ff");
        horoscope.setFill(Color.rgb(109, 52, 253));
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(236, 204, 255), null, null)));

        // Incorporates the master pane into a Scene with a set size and title
        Scene scene = new Scene(pane, 500, 400);
        primaryStage.setTitle("Horoscope Wizard");
        primaryStage.setScene(scene);

        // Attempts to place a starting image in the center of the pane, and displays an error otherwise
        try {
            pane.setCenter(new ImageView(new Image("Signs/StarCluster.jpg")));
        }
        catch (IllegalArgumentException error) {
            pane.setCenter(new Text("[Image Not Found]"));
        }

        // Provides instructions for what to do when the Computer Horoscope button is pressed
        computeButton.setOnAction(e -> {

            // Replaces bottom text with a randomly generated horoscope
            horoscope.setText(Horoscopes.generateHoroscope());

            // Changes font and color
            horoscope.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 30));
            horoscope.setFill(Color.rgb(142, 74, 253));

            // Calls generateSign on the selected month and day, then updates the image to match the resulting sign
            String sign = Horoscopes.generateSign(monthBox.getValue(), dayBox.getValue());
            try {
                pane.setCenter(new ImageView(new Image("Signs/" + sign + ".gif")));
            }

            // If image not found, displays text instead
            catch (IllegalArgumentException error) {
                pane.setCenter(new Text("[" + sign + "]"));
            }
        });

        // Prevents user from resizing the Stage and then generates the stage
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Animate the pane, scene, and button
        new FadeIn(pane).play();
        new Pulse(scene).play();
        new BounceInDown(computeButton).play();
        
        
   

    }

    // Default main method for JavaFX programs
    public static void main(String[] args) {
        launch(args);
    }

}
