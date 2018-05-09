package myapp.ui;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import myapp.model.Article;
import myapp.model.NewsReader;

import java.util.Date;

public class NewsReaderController {
    NewsReader newsReader;
    @FXML
    private ArticleListController articleListController;
    @FXML
    private ArticleFullViewController articleFullViewController;
    @FXML
    private SearchBarController searchBarController;
    @FXML
    private MainMenuController mainMenuController;

    private int visitedPages;

    public void initModel(NewsReader newsReader) {
        // Allow to init model only once
        if (this.newsReader != null) {
            System.out.println(newsReader.toString());
        }

        this.newsReader = newsReader;

        fetchBreakingNews();
    }

    @FXML
    private void initialize() {
        // Inject controller where needed
        articleListController.injectMasterController(this);
        articleFullViewController.injectMasterController(this);
        searchBarController.injectMasterController(this);
        mainMenuController.injectMasterController(this);
    }

    public Article getSelectedArticle() {
        return (Article) articleListController.getArticlesListView().getSelectionModel().getSelectedItems();
    }

    public void setSearchBarVisibility(boolean visibility) {
        searchBarController.setVisibility(visibility);
    }

    public void searchNews(String term, Date fromDate, Date toDate) {
        // Reset visited pages
        visitedPages = 0;

        Task<ObservableList<Article>> task = new Task<ObservableList<Article>>() {
            @Override
            protected ObservableList<Article> call() throws Exception {
                return newsReader.getEverything(term, fromDate, toDate, 1);
            }
        };

        // Progress bar
        task.setOnScheduled(e -> {
            articleListController.getProgressBar().setVisible(true);
        });

        task.setOnSucceeded(e -> {
            articleListController.getProgressBar().setVisible(false);
        });


        // Bindings
        articleListController.getArticlesListView().itemsProperty().bind(task.valueProperty());
        articleListController.getProgressBar().progressProperty().bind(task.progressProperty());

        new Thread(task).start();
    }

    public void fetchBreakingNews() {
        Task<ObservableList<Article>> task = new Task<ObservableList<Article>>() {
            @Override
            protected ObservableList<Article> call() throws Exception {
                return newsReader.getBreakingNews();
            }
        };

        // Progress bar
        task.setOnScheduled(e -> {
            articleListController.getProgressBar().setVisible(true);
        });

        task.setOnSucceeded(e -> {
            articleListController.getProgressBar().setVisible(false);
        });

        // Bindings
        articleListController.getArticlesListView().itemsProperty().bind(task.valueProperty());
        articleListController.getProgressBar().progressProperty().bind(task.progressProperty());

        new Thread(task).start();
    }

    public void showMoreResults() {

    }

    public void updateFullView(Article article) {
        articleFullViewController.setArticle(article);
    }

    public void clearResults() {
        articleListController.getArticlesListView().getItems().clear();
    }
}
