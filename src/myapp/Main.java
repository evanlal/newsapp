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
import myapp.ui.ArticleListController;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("ArticleList.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();


        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCountry(Country.US);
        ArticlesApiResponse articlesApiResponse = newsApi.sendTopHeadlinesRequest(apiRequest);

        List<Article> articles = new ArrayList<>();

        for(NewsApiWrapper.Article a : articlesApiResponse.getArticles()) {
            articles.add(new Article(a));
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/fxml/ArticleList.fxml"));
        Parent root = fxmlLoader.load();

        ArticleListController articleListController = (ArticleListController) fxmlLoader.getController();
        articleListController.populate(articles);


        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
        scene.getStylesheets().add(Main.class.getResource("/css/jfoenix-design.css").toExternalForm());



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
