import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class HoroscopeJavaFX extends Application {

    private String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    private Integer[] days = {1 , 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    private ComboBox<String> monthBox = new ComboBox<>();
    private ComboBox<Integer> dayBox = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) {
        monthBox.getItems().addAll(FXCollections.observableArrayList(months));
        dayBox.getItems().addAll(FXCollections.observableArrayList(days));

        Image starSign = new Image("http://www.astrology-insight.com/astrology/arisbigbutton.gif");

        FlowPane inputPane = new FlowPane();
        inputPane.setHgap(5);
        inputPane.getChildren().add(new Label("Enter birth month:"));
        inputPane.getChildren().add(monthBox);
        inputPane.getChildren().add(new Label("Enter birth day:"));
        inputPane.getChildren().add(dayBox);
        inputPane.getChildren().add(new Button("Compute Horoscope"));

        Label horoscope = new Label("You will pick up a tall, handsome hitchhiker off the side of I-95 on your way" +
                " to see the Ravens play in Baltimore. The two of you will fall in love while eating lunch at" +
                " a Denny's in Fredericksburg and miss the game, which the Ravens will lose 12-97.");
        horoscope.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        horoscope.setWrapText(true);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        pane.setTop(inputPane);
        pane.setCenter(new ImageView(starSign));
        pane.setBottom(horoscope);

        Scene scene = new Scene(pane, 500, 350);
        primaryStage.setTitle("Horoscope Wizard");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
