/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

import java.util.ArrayList;

/**
 * Extends ApiResponse to cater for Article repsonses.
 * Each article is saved in a list of Article objects.
 */
public class ArticlesApiResponse extends ApiResponse {
    private int totalResults;
    private ArrayList<Article> articles;

    public ArticlesApiResponse() {
        super();
        totalResults = 0;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return "ArticlesApiResponse{" +
                "status='" + this.getStatus() + '\'' +
                ", errorCode='" + this.getCode() + '\'' +
                ", message='" +this.getMessage() + '\'' +
                ", totalResults='" + totalResults + '\'' +
                '}';
    }
}
