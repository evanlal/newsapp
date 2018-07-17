/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Wrapper for NewsApi.org
 * Api Documentation: https://newsapi.org/docs
 * Developed for Evan's Newsapp
 */
public class NewsApi {
    private String apiKey;
    private Client client;
    private TopHeadlinesEndpoint topHeadlinesEndpoint;
    private EverythingEndpoint everythingEndpoint;
    private SourcesEndpoint sourcesEndpoint;

    public NewsApi(String apiKey) {
        this.apiKey = apiKey;
        client = ClientBuilder.newClient();
        topHeadlinesEndpoint = new TopHeadlinesEndpoint();
        everythingEndpoint = new EverythingEndpoint();
        sourcesEndpoint = new SourcesEndpoint();
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Sends a request to Top Headlines Endpoint
     * For valid request parameters, check documentation:
     * https://newsapi.org/docs/endpoints/top-headlines
     * @param apiRequest
     * @return An ArticlesApiResponse. TThe json objects are mapped
     * to a list of Article objects.
     */
    public ArticlesApiResponse sendTopHeadlinesRequest(ApiRequest apiRequest) {
        apiRequest.setApiKey(this.apiKey);
        return topHeadlinesEndpoint.sendRequest(apiRequest, client);
    }

    /**
     * Sends a request to Everything Endpoint
     * For valid request parameters, check documentation:
     * https://newsapi.org/docs/endpoints/everything
     * @param apiRequest
     * @return An ArticlesApiResponse. The json objects are mapped
     * to a list of Article objects.
     */
    public ArticlesApiResponse sendEverythingRequest(ApiRequest apiRequest) {
        apiRequest.setApiKey(this.apiKey);
        return everythingEndpoint.sendRequest(apiRequest, client);
    }

    /**
     * Sends a request to Sources Endpoint
     * For valid request parameters, check documentation:
     * https://newsapi.org/docs/endpoints/sources
     * @param apiRequest
     * @return An SourcesApiResponse. The json objects are mapped to
     * a list of Source objects.
     */
    public SourcesApiResponse sendSourcesRequest(ApiRequest apiRequest) {
        apiRequest.setApiKey(this.apiKey);
        return  sourcesEndpoint.sendRequest(apiRequest, client);
    }

    /**
     * NewsApi Unit Testing
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\n Top Headlines \n");
        top_headlines();

        System.out.println("\n Everything \n");
        everything();

        System.out.println("\n Sources \n");
        sources();
    }

    public static void top_headlines() {
        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");

        ApiRequest apiRequest1 = new ApiRequest();
        apiRequest1.setCountry(Country.GB);
        apiRequest1.setCategory(Category.TECHNOLOGY);

        ArticlesApiResponse articlesApiResponse = newsApi.sendTopHeadlinesRequest(apiRequest1);
        System.out.println(articlesApiResponse.toString());

        for (Article a : articlesApiResponse.getArticles()) {
            System.out.println(a.toString());
        }
    }

    public static void everything() {
        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setQ("Cambridge Analytica");
        apiRequest.setPageSize(100);
        apiRequest.setPage(1);

        ArticlesApiResponse articlesApiResponse = newsApi.sendEverythingRequest(apiRequest);
        System.out.println(articlesApiResponse.toString());

        for (Article a : articlesApiResponse.getArticles()) {
            System.out.println(a.toString());
        }
    }

    public static void sources() {
        NewsApi newsApi = new NewsApi("5af3074b0cdd4892892c29df2c9ea910");

        ApiRequest apiRequest1 = new ApiRequest();
        apiRequest1.setCountry(Country.GB);

        SourcesApiResponse sourcesApiResponse = newsApi.sendSourcesRequest(apiRequest1);
        System.out.println(sourcesApiResponse.toString());

        for (Source s : sourcesApiResponse.getSources()) {
            System.out.println(s.toString());
        }
    }
}
