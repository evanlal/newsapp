package myapp.ui;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import myapp.model.Article;


public class ArticleListController {
    NewsReaderController newsReaderController;

    @FXML
    AnchorPane listWrapper;
    @FXML
    JFXListView<Article> articlesListView;
    @FXML
    JFXProgressBar progressBar;

    @FXML
    private void initialize() {
        articlesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        progressBar.setVisible(false);

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

    @FXML
    private void mouseClickHandler() {
        newsReaderController.updateFullView(
                articlesListView.getSelectionModel().getSelectedItem()
        );
    }

    @FXML
    private void scrollFinishedHandler() {
        newsReaderController.showMoreResults();
    }

    public void injectMasterController(NewsReaderController newsReaderController) {
        this.newsReaderController = newsReaderController;
    }

    public JFXListView<Article> getArticlesListView() {
        return articlesListView;
    }

    public JFXProgressBar getProgressBar() {
        return progressBar;
    }
}
