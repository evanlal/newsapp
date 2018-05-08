package myapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import myapp.model.Article;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ArticleCell extends Parent {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label source;
    @FXML
    private ImageView image;
    @FXML
    private Label title;
    @FXML
    private Label publishedAt;

    public ArticleCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ArticleCell.fxml"));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setContent(Article article) {
        this.source.setText(article.getSource().getName());
        this.title.setText(article.getTitle());

        SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM YYYY, HH:mm");
        this.publishedAt.setText(sdf.format(article.getPublishedAt()));

        if (article.getUrlToImage() != null) {
            //Image img = new Image(article.getUrlToImage(), 150, 150, true, true);
            this.image.setImage(article.getThumbnail());
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
