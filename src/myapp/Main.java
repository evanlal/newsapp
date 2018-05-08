package myapp;

import NewsApiWrapper.ApiRequest;
import NewsApiWrapper.ArticlesApiResponse;
import NewsApiWrapper.Country;
import NewsApiWrapper.NewsApi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import myapp.model.Article;
import myapp.model.NewsReader;
import myapp.ui.ArticleListController;
import myapp.ui.NewsReaderController;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("ArticleList.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();


        // Create new api client
        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");

        // Load articlelist
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/fxml/NewsReader.fxml"));
        Parent root = fxmlLoader.load();
        NewsReaderController newsReaderController = (NewsReaderController) fxmlLoader.getController();

        NewsReader newsReader = new NewsReader(newsApi);
        newsReaderController.initModel(newsReader);


        Scene scene = new Scene(root, 1200, 600);
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-design.css").toExternalForm());



        primaryStage.setTitle("NewsApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
