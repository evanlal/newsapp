package myapp.model;

import NewsApiWrapper.ApiRequest;
import NewsApiWrapper.ArticlesApiResponse;
import NewsApiWrapper.Country;
import NewsApiWrapper.NewsApi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class NewsReader {
    private NewsApi newsApi;
    private final ObservableList<Article> articlesList = FXCollections.observableArrayList();


    public NewsReader(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public ObservableList<Article> getBreakingNews() {
        // Send request
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCountry(Country.US);
        ArticlesApiResponse articlesApiResponse = newsApi.sendTopHeadlinesRequest(apiRequest);

        // Map to Article objects
        List<NewsApiWrapper.Article> apiArticles = articlesApiResponse.getArticles();
        for(NewsApiWrapper.Article a : apiArticles) {
            articlesList.add(new Article(a));
        }

        return articlesList;
    }

    public ObservableList<Article> getArticlesList() {
        return articlesList;
    }
}
