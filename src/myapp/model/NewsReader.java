package myapp.model;

import NewsApiWrapper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

public class NewsReader {
    private NewsApi newsApi;
    private final ObservableList<Article> articlesList = FXCollections.observableArrayList();
    private final int PAGE_SIZE = 20;
    private ApiRequest lastApiRequest;
    private ArticlesApiResponse lastArticlesApiResponse;


    public NewsReader(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public ObservableList<Article> getBreakingNews() {
        // Send request
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCountry(Country.US);
        ArticlesApiResponse articlesApiResponse = newsApi.sendTopHeadlinesRequest(apiRequest);

        mapResponseToArticles(articlesApiResponse);

        return articlesList;
    }

    public ObservableList<Article> getEverything(String q, Date fromDate, Date toDate, int page) {
        if (page < 1) {
            throw new IllegalArgumentException("Page must be greater than 1");
        }

        // Don't exceed page limits
        if (page > 1 && page > getTotalPages(lastArticlesApiResponse)) {
            return null;
        }

        ApiRequest apiRequest = constructRequest(q, fromDate, toDate, page);
        ArticlesApiResponse articlesApiResponse = newsApi.sendEverythingRequest(apiRequest);
        mapResponseToArticles(articlesApiResponse);

        // Cache last request, and response
        // Useful for pagination
        lastApiRequest = apiRequest;
        lastArticlesApiResponse = articlesApiResponse;

        return articlesList;
    }


    // Helper functions

    private ApiRequest constructRequest(String q, Date fromDate, Date toDate, int page) {
        if (q == null || q.isEmpty()) {
            throw new IllegalArgumentException("A question has to be defined");
        }

        // Construct request
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setQ(q);
        apiRequest.setPage(page);

        if (fromDate != null) {
            apiRequest.setFrom(fromDate);
        }

        if (toDate != null) {
            apiRequest.setTo(toDate);
        }

        // Lang has to be set
        // Remove if multilingual
        apiRequest.setLang(Lang.EN);

        return apiRequest;
    }

    private void mapResponseToArticles(ArticlesApiResponse articlesApiResponse) {
        // Clear past articles
        articlesList.clear();

        // Map articles
        List<NewsApiWrapper.Article> apiArticles = articlesApiResponse.getArticles();
        for(NewsApiWrapper.Article a : apiArticles) {
            articlesList.add(new Article(a));
        }
    }

    public int getTotalPages(ArticlesApiResponse articlesApiResponse) {
        int totalResults = articlesApiResponse.getTotalResults();

        if (totalResults > PAGE_SIZE) {
            int totalPages =  (int) Math.ceil((double) totalResults/PAGE_SIZE);
            return totalPages;
        } else {
            return 1;
        }
    }
}
