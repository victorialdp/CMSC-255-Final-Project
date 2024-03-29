/****************************************************************************
 * HoroscopesJavaFX.java
 ****************************************************************************
 * This is the GUI design for our horoscope program. It creates a window with
 * drop-down menus for the user's birth month and birth day along with a
 * button to run the program. Below the input menus, it displays an image
 * representing the user's star sign and the text of a horoscope.
 *
 * Please read ReadMe.txt before running code.
 *____________________________________________________________________________
 *
 * Jazmin Gering, Victoria Lopez Del Pino, Sean Youngstone
 * November 19, 2019
 * CMSC 255 002
 ****************************************************************************/

// Imports all the necessary JavaFX tools for the code below
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

// Imports AnimateFX animation tools
// Requires access to AnimateFX JAR file
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;

// Imports Java classes that allow us to read from a .txt file
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

        // Sets number of days to match the chosen month
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

            // If chosen day was out of range for the new month, adjusts value to last day of that month
            dayBox.setValue(Math.min(day, dayBox.getItems().size()));
        });

        // Creates a button for computing ther user's horoscope
        Button computeButton = new Button("Compute Horoscope");

        // Sets up a subpane with both comboboxes, corresponding Labels, and the compute button
        FlowPane inputPane = new FlowPane();
        inputPane.setHgap(5);
        inputPane.getChildren().add(new Label("Enter birth month:"));
        inputPane.getChildren().add(monthBox);
        inputPane.getChildren().add(new Label("Enter birth day:"));
        inputPane.getChildren().add(dayBox);
        inputPane.getChildren().add(computeButton);

        // Creates a a Text object with an introductory message and then formats it
        Text horoscope = new Text("Welcome to the Horoscope Wizard! Enter your birthday to learn your destiny.");
        horoscope.setFont(Font.font("Century Gothic", FontWeight.BOLD, 20));
        horoscope.setWrappingWidth(480);
        horoscope.setTextAlignment(TextAlignment.CENTER);

        // Creates a master pane, pads it, and assigns the above elements to occupy top and bottom
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 20, 10));
        pane.setTop(inputPane);
        pane.setBottom(horoscope);

        // Attempts to place a starting image in the center of the pane, and displays an error otherwise
        try {
            pane.setCenter(new ImageView(new Image("Signs/StarCluster.jpg")));
        }
        catch (IllegalArgumentException error) {
            pane.setCenter(new Text("[Image Not Found]"));
        }

        // Provides instructions for what to do when the Compute Horoscope button is pressed
        computeButton.setOnAction(e -> {

            // Replaces bottom text with a randomly generated horoscope and causes text to fade in
            horoscope.setText(generateHoroscope());
            new FadeIn(pane.getBottom()).play();

            // Changes font and color
            horoscope.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 30));
            horoscope.setFill(Color.rgb(142, 74, 253));

            // Calls generateSign on the selected month and day, then updates the image to match the resulting sign
            String sign = generateSign(monthBox.getValue(), dayBox.getValue());
            try {
                pane.setCenter(new ImageView(new Image("Signs/" + sign + ".gif")));

                // Causes image to fade in
                new FadeIn(pane.getCenter()).play();
            }

            // If image not found, displays text instead
            catch (IllegalArgumentException error) {
                pane.setCenter(new Text("[" + sign + "]"));
            }
        });

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

        // Prevents user from resizing the Stage and then generates the stage
        primaryStage.setResizable(false);
        primaryStage.show();

        // Cause the elements to pulse on startup
        new Pulse(pane.getTop()).play();
        new Pulse(pane.getCenter()).play();
        new Pulse(pane.getBottom()).play();

    }

    // Default main method for JavaFX programs
    public static void main(String[] args) {
        launch(args);
    }

    // Finds user's star sign using a chain of if/else statements
    private static String generateSign(String month, int day) {
        if (month.equals("January") && day >= 20 || month.equals("February") && day <= 18) {
            return "Aquarius";
        } else if (month.equals("February") || month.equals("March") && day <= 20) {
            return "Pisces";
        } else if (month.equals("March") || month.equals("April") && day <= 19) {
            return "Aries";
        } else if (month.equals("April") || month.equals("May") && day <= 20) {
            return "Taurus";
        } else if (month.equals("May") || month.equals("June") && day <= 20) {
            return "Gemini";
        } else if (month.equals("June") || month.equals("July") && day <= 22) {
            return "Cancer";
        } else if (month.equals("July") || month.equals("August") && day <= 22) {
            return "Leo";
        } else if (month.equals("August") || month.equals("September") && day <= 22) {
            return "Virgo";
        } else if (month.equals("September") || month.equals("October") && day <= 22) {
            return "Libra";
        } else if (month.equals("October") || month.equals("November") && day <= 21) {
            return "Scorpio";
        } else if (month.equals("November") || month.equals("December") && day <= 21) {
            return "Sagittarius";
        } else {
            return "Capricorn";
        }

    }

    // Returns a random String to use as a horoscope
    private static String generateHoroscope() {

        // The try block is here in case the file cannot be found
        try {
            // Creates a Scanner to read the .txt file "Horoscopes.txt" and an ArrayList to store the horoscopes
            Scanner readText = new Scanner(new File("Horoscopes.txt"));
            ArrayList<String> horoscopes = new ArrayList<>();

            // Fills the ArrayList with the various fortunes in the file
            while (readText.hasNextLine()) {
                horoscopes.add(readText.nextLine());
            }

            // Generates two random integers between 0 and horoscopes.size() - 1, checks they aren't equal,
            // then returns a two-sentence horoscope constructed from the fortunes found at those two indices
            int index1 = (int)(Math.random() * horoscopes.size());
            int index2;
            do {
                index2 = (int)(Math.random() * horoscopes.size());
            } while (index1 == index2);
            return horoscopes.get(index1) + " " + horoscopes.get(index2);

        }
        // Error message in the event the file is not found
        catch (FileNotFoundException error) {
            return "I'm sorry, we seem to have lost our star charts. Try again later.";
        }

    }

}
