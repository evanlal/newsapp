/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import NewsApp.model.Article;

/**
 * Controller for the Full View
 */
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

    /**
     * Sets the Article to be displayed in Full View
     * @param article
     */
    public void setArticle(Article article) {
        title.setText(article.getTitle());
        publishedAt.setText(article.getPublishedAt().toString());
        source.setText(article.getSource().getName());
        author.setText(article.getAuthor());
        image.setImage(new Image(article.getUrlToImage(), 600, 600, true, true));
        description.setText(article.getDescription());
        url.setText(article.getUrl());
    }
}
