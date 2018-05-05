package myapp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import NewsApiWrapper.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Text title;
    @FXML
    private Text description;
    @FXML
    private Text publishedAt;
    @FXML
    private ImageView image;
    private String url;
    private String urlToImage;

    public Controller() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCountry(Country.US);
        ArticlesApiResponse articlesApiResponse = newsApi.sendTopHeadlinesRequest(apiRequest);

        NewsApiWrapper.Article newsApiArticle = articlesApiResponse.getArticles().get(1);

        this.title.setText(newsApiArticle.getTitle());
        this.description.setText(newsApiArticle.getDescription());
        this.publishedAt.setText(newsApiArticle.getPublishedAt());

        if (urlToImage != null) {
            Image newsImage = new Image(urlToImage);
            this.image.setImage(newsImage);
        }
    }
}
