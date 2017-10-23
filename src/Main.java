

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import tsp.TSP;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootLayoutController rootController;
    private MainViewController mainViewController;

    private File dataFile;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Traveling Salesperson");

        initRootLayout();

        showMainScreen();

        //showLogin();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
        primaryStage.setTitle("Traveling Salesperson");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            rootController = controller;

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();
            this.mainViewController = loader.getController();
            this.rootLayout.setCenter(mainView);

            rootController.toggleMenu(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDataFile(File file) {
        this.dataFile = file;
        this.mainViewController.setFile(file);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
