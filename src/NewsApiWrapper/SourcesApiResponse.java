/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

import java.util.ArrayList;

/**
 * Extends ApiResponse to cater for Sources repsonses.
 * Each article is saved in a list of Source objects.
 */
public class SourcesApiResponse extends ApiResponse {
    private ArrayList<Source> sources;

    public SourcesApiResponse() {
        super();
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return "ArticlesApiResponse{" +
                "status='" + this.getStatus() + '\'' +
                ", errorCode='" + this.getCode() + '\'' +
                ", message='" +this.getMessage() + '\'' +
                '}';
    }
}
