/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApp.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import NewsApp.model.Article;
import NewsApp.model.NewsReader;

import java.util.Date;

/**
 * The master controller for the fx application.
 * The UI is split into sub - controllers for each component.
 */
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

    @FXML
    private void initialize() {
        // Inject a reference to the master controller where needed.
        articleListController.injectMasterController(this);
        articleFullViewController.injectMasterController(this);
        searchBarController.injectMasterController(this);
        mainMenuController.injectMasterController(this);
    }

    /**
     * Initialize the underlying model.
     * @param newsReader
     */
    public void initializeModel(NewsReader newsReader) {
        // Allow to init model only once
        if (this.newsReader != null) {
            System.out.println(newsReader.toString());
        }

        this.newsReader = newsReader;

        fetchBreakingNews();
    }

    /**
     * Fills the list view with breaking news.
     * The fetching is happening in a new thread.
     */
    public void fetchBreakingNews() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                newsReader.getBreakingNews();
                ObservableList<Article> list = newsReader.getArticlesList();

                Platform.runLater(() -> articleListController.getArticlesListView().getItems().setAll(list));

                return null;
            }
        };

        // Progress bar
        task.setOnScheduled(e -> {
            articleListController.getProgressBar().setVisible(true);
        });

        task.setOnSucceeded(e -> {
            articleListController.getProgressBar().setVisible(false);
        });

        articleListController.getProgressBar().progressProperty().bind(task.progressProperty());

        new Thread(task).start();
    }

    /**
     * Fills the list view with the search results.
     * The fetching is happening in a new thread.
     * The view list is updated using Platform.runLater,
     * as it must be updated only by the UI thread.
     * @param term
     * @param fromDate
     * @param toDate
     */
    public void searchNews(String term, Date fromDate, Date toDate) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                newsReader.getEverything(term, fromDate, toDate, 1);
                ObservableList<Article> list = newsReader.getArticlesList();

                Platform.runLater(() -> articleListController.getArticlesListView().getItems().setAll(list));

                return null;
            }
        };

        // Progress bar
        task.setOnScheduled(e -> {
            articleListController.getProgressBar().setVisible(true);
        });

        task.setOnSucceeded(e -> {
            articleListController.getProgressBar().setVisible(false);
        });


        // Used run later for this instead to support lazy loading
        // articleListController.getArticlesListView().itemsProperty().bind(task.valueProperty());

        articleListController.getProgressBar().progressProperty().bind(task.progressProperty());

        new Thread(task).start();
    }

    /**
     * Fills the list view with the next page of the last request if it exists.
     * The fetching is happening in a new thread. The view list is updated using Platform.runLater,
     * as it must be updated only by the UI thread.
     */
    public void loadMoreSearchResults() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                newsReader.getEverythingNextPage();
                ObservableList<Article> list = newsReader.getArticlesList();

                // Add more articles to list
                Platform.runLater(() -> articleListController.getArticlesListView().getItems().addAll(list));
                return null;
            }
        };

        // Progress bar
        task.setOnScheduled(e -> {
            articleListController.getProgressBar().setVisible(true);
        });

        task.setOnSucceeded(e -> {
            articleListController.getProgressBar().setVisible(false);
        });


        // Used run later for this instead to support lazy loading
        // articleListController.getArticlesListView().itemsProperty().bind(task.valueProperty());

        articleListController.getProgressBar().progressProperty().bind(task.progressProperty());

        new Thread(task).start();
    }


    /**
     * Shows an article in the full view component.
     * @param article
     */
    public void updateFullView(Article article) {
        articleFullViewController.setArticle(article);
    }

    /**
     * Controls the visibility of the search bar, and load more button.
     * @param visibility
     */
    public void setSearchVisibility(boolean visibility) {
        searchBarController.setVisibility(visibility);
        articleListController.getLoadMoreButton().setVisible(visibility);
    }

    /**
     * Clears the main list view.
     */
    public void clearResults() {
        articleListController.getArticlesListView().getItems().clear();
    }
}
