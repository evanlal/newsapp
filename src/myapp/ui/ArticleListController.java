package myapp.ui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import myapp.model.Article;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ArticleListController implements Initializable{
    @FXML
    JFXListView<Article> articlesListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        articlesListView.setCellFactory(articlesListView -> {
            ArticleCell articleCell = new ArticleCell();
            JFXListCell<Article> cell = new JFXListCell<Article>() {

                @Override
                protected void updateItem(Article article, boolean empty) {
                    super.updateItem(article, empty);
                    this.setGraphic(null);
                    if(empty) {
                        setGraphic(null);
                    } else {
                        articleCell.setContent(article);
                        setText(null);
                        setGraphic(articleCell.getGridPane());
                    }
                }
            };
            return cell;
        });
    }

    public void populate(List<Article> articles) {
        this.articlesListView.getItems().setAll(articles);
        System.out.println("populating");
    }
}
