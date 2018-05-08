package myapp.ui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import myapp.model.Article;


public class ArticleListController {
    NewsReaderController newsReaderController;

    @FXML
    JFXListView<Article> articlesListView;

    @FXML
    private void initialize() {
        System.out.println("list init");

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

    public void injectMasterController(NewsReaderController newsReaderController) {
        this.newsReaderController = newsReaderController;
    }

    public JFXListView<Article> getArticlesListView() {
        return articlesListView;
    }
}
