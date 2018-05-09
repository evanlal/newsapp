package NewsApp.model;

import NewsApiWrapper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

/**
 * The model responsible for communicating with the NewsApi wrapper
 */
public class NewsReader {
    private NewsApi newsApi;
    private final ObservableList<Article> articlesList = FXCollections.observableArrayList();
    private final int PAGE_SIZE = 20;
    private ApiRequest lastApiRequest;
    private ArticlesApiResponse lastArticlesApiResponse;


    public NewsReader(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    /**
     * Get the top 20 breaking news for GB.
     */
    public void getBreakingNews() {
        // Send request
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCountry(Country.GB);
        ArticlesApiResponse articlesApiResponse = newsApi.sendTopHeadlinesRequest(apiRequest);

        mapResponseToArticles(articlesApiResponse);
    }

    /**
     * Get articles according to a term and dates.
     * @param q
     * @param fromDate
     * @param toDate
     * @param page
     */
    public void getEverything(String q, Date fromDate, Date toDate, int page) {
        ApiRequest apiRequest = constructRequest(q, fromDate, toDate, page);
        ArticlesApiResponse articlesApiResponse = newsApi.sendEverythingRequest(apiRequest);

        mapResponseToArticles(articlesApiResponse);

        // Cache last request, and response
        // Useful for pagination
        lastApiRequest = apiRequest;
        lastArticlesApiResponse = articlesApiResponse;
    }

    /**
     * Get the next page of the last search results.
     * TODO: Loading more articles need improvemnts and testing
     */
    public void getEverythingNextPage() {
        if (lastApiRequest == null || lastArticlesApiResponse == null) {
            return;
        }

        int nextPage = lastApiRequest.getPage() + 1;

        if (nextPage > getTotalPages(lastArticlesApiResponse)) {
            return;
        }

        lastApiRequest.setPage(nextPage);
        ArticlesApiResponse articlesApiResponse = newsApi.sendEverythingRequest(lastApiRequest);
        mapResponseToArticles(articlesApiResponse);

        // Cache last response
        lastArticlesApiResponse = articlesApiResponse;
    }

    public ObservableList<Article> getArticlesList() {
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

        // Map new articles
        List<NewsApiWrapper.Article> apiArticles = articlesApiResponse.getArticles();
        for(NewsApiWrapper.Article a : apiArticles) {
            articlesList.add(new Article(a));
        }
    }

    private int getTotalPages(ArticlesApiResponse articlesApiResponse) {
        int totalResults = articlesApiResponse.getTotalResults();

        if (totalResults > PAGE_SIZE) {
            int totalPages =  (int) Math.ceil((double) totalResults/PAGE_SIZE);
            return totalPages;
        } else {
            return 1;
        }
    }
}
