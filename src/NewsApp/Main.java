/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApp;

import NewsApiWrapper.NewsApi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import NewsApp.model.NewsReader;
import NewsApp.ui.NewsReaderController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/fxml/NewsReader.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new api client
        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");
        NewsReader newsReader = new NewsReader(newsApi);

        NewsReaderController newsReaderController = (NewsReaderController) fxmlLoader.getController();
        newsReaderController.initializeModel(newsReader);

        Scene scene = new Scene(root, 1400, 600);
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-design.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("ui/css/custom.css").toExternalForm());

        primaryStage.setTitle("News App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
