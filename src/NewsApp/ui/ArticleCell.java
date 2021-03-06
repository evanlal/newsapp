/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import NewsApp.model.Article;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Controller for an Article Cell inside the main list view
 */
public class ArticleCell {
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

    /**
     * Sets the content of an Article Cell
     * @param article
     */
    public void setContent(Article article) {
        this.source.setText(article.getSource().getName());
        this.title.setText(article.getTitle());

        if (article.getPublishedAt() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM YYYY, HH:mm");
            this.publishedAt.setText(sdf.format(article.getPublishedAt()));
        }

        if (article.getUrlToImage() != null) {
            //Image img = new Image(article.getUrlToImage(), 150, 150, true, true);
            this.image.setImage(article.getThumbnail());
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
