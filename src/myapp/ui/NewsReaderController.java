package myapp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import myapp.model.Article;
import myapp.model.NewsReader;

public class NewsReaderController {
    NewsReader newsReader;
    @FXML private ArticleListController articleListController;
    @FXML private ArticleFullViewController articleFullViewController;

    public void initModel(NewsReader newsReader) {
        // Allow to init model only once
        if (this.newsReader != null) {
            System.out.println(newsReader.toString());
        }

        this.newsReader = newsReader;

        // Populate view
        articleListController.getArticlesListView().getItems().setAll(newsReader.getBreakingNews());
        articleListController.getArticlesListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void initialize() {
        articleListController.injectMasterController(this);
        articleFullViewController.injectMasterController(this);

        // Event listeners
        articleListController.getArticlesListView().getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
            articleFullViewController.setArticle(newValue);
        }));
    }

    public Article getSelectedArticle() {
        return (Article) articleListController.getArticlesListView().getSelectionModel().getSelectedItems();
    }
}
