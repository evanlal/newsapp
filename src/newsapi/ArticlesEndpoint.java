package newsapi;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * An abstract class for Endpoints that respond with an ArticlesApiResponse,
 * as each endpoint has a different URL (e.g. top headlines, everything).
 */
public abstract class ArticlesEndpoint extends Endpoint {

    public ArticlesApiResponse sendRequest(ApiRequest apiRequest, Client client) {
        WebTarget webTarget = buildWebTarget(apiRequest, client);
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.header("X-Api-Key", apiRequest.getApiKey()).get();

        String body = response.readEntity(String.class);

        // json
        Gson gson = new Gson();
        ArticlesApiResponse articlesApiResponse = gson.fromJson(body, ArticlesApiResponse.class);
        articlesApiResponse.setJson(body);

        return articlesApiResponse;
    }
}
