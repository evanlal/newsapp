/*
 * Written by Evan Lalopoulos <evan.lalopoulos.2017@my.bristol.ac.uk>
 * Copyright (C) 2018 - All rights reserved.
 * Unauthorized copying of this file is strictly prohibited.
 */

package NewsApiWrapper;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Sources Endpoint.
 * The response body is mapped to Source objects using GSON.
 */
public class SourcesEndpoint extends Endpoint {

    public SourcesEndpoint() {
        super();
        this.setRootUrl("https://NewsApi.org/v2/sources?");
    }

    @Override
    public SourcesApiResponse sendRequest(ApiRequest apiRequest, Client client) {
        WebTarget webTarget = buildWebTarget(apiRequest, client);
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.header("X-Api-Key", apiRequest.getApiKey()).get();

        String body = response.readEntity(String.class);

        // json
        Gson gson = new Gson();
        SourcesApiResponse sourcesApiResponse = gson.fromJson(body, SourcesApiResponse.class);
        sourcesApiResponse.setJson(body);

        return sourcesApiResponse;
    }

    @Override
    WebTarget buildWebTarget(ApiRequest apiRequest, Client client) {
        WebTarget webTarget = client.target(this.getRootUrl());

        if (apiRequest.getLang() != null) {
            webTarget = webTarget.queryParam("language", apiRequest.getLang());
        }

        if (apiRequest.getCountry() != null) {
            webTarget = webTarget.queryParam("country", apiRequest.getCountry());
        }

        if (apiRequest.getCategory() != null) {
            webTarget = webTarget.queryParam("category", apiRequest.getCategory());
        }

        return webTarget;
    }
}
