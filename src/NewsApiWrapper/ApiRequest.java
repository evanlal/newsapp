/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A request to the API
 * For available and valid parameters
 * refer to: https://newsapi.org/docs/
 */
public class ApiRequest {
    private String apiKey;
    private String q;
    private String sources;
    private String domains;
    private String from;
    private String to;
    private String lang;
    private String country;
    private String category;
    private String sortBy;
    private int pageSize;
    private int page;


    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public void setFrom(Date from) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.from = sdf.format(from);
    }

    public void setTo(Date to) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.to = sdf.format(to);
    }

    public void setLang(Lang lang) {
        this.lang = lang.toString();
    }

    public void setCountry(Country country) {
        this.country = country.toString();
    }

    public void setCategory(Category category) {
        this.category = category.toString();
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy.toString();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getQ() {
        return q;
    }

    public String getSources() {
        return sources;
    }

    public String getDomains() {
        return domains;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLang() {
        return lang;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public String getSortBy() {
        return sortBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }
}
