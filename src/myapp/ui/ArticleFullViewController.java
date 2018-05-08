package myapp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import myapp.model.Article;

public class ArticleFullViewController {
    NewsReaderController newsReaderController;

    @FXML
    private Label author;
    @FXML
    private Label source;
    @FXML
    private ImageView image;
    @FXML
    private Label title;
    @FXML
    private Label publishedAt;
    @FXML
    private Text url;
    @FXML
    private Text description;

    public void injectMasterController(NewsReaderController newsReaderController) {
        this.newsReaderController = newsReaderController;
    }

    @FXML
    private void initialise() {

    }

    public void setArticle(Article article) {
        title.setText(article.getTitle());
        publishedAt.setText(article.getPublishedAt().toString());
        source.setText(article.getSource().getName());
        author.setText(article.getAuthor());
        image.setImage(new Image(article.getUrlToImage(), 500, 500, true, true));
        description.setText(article.getDescription());
        url.setText(article.getUrl());
    }
}
