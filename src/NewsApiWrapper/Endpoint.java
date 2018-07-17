/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * An abstract class for NewsApi.org endpoints
 * Each endpoint has different URL and Response type.
 * The body of the responses is mapped to Article or Source
 * objects according to implementation, using GSON.
 */
public abstract class Endpoint {
    private String rootUrl;

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public abstract ApiResponse sendRequest(ApiRequest apiRequest, Client client);
    abstract WebTarget buildWebTarget(ApiRequest apiRequest, Client client);
}
