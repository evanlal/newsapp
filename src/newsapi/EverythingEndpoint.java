package newsapi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Everything Endpoint
 */
public class EverythingEndpoint extends ArticlesEndpoint {

    public EverythingEndpoint() {
        super();
        this.setRootUrl("https://newsapi.org/v2/everything?");
    }

    @Override
    WebTarget buildWebTarget(ApiRequest apiRequest, Client client) {
        WebTarget webTarget = client.target(this.getRootUrl());

        if (apiRequest.getQ() != null) {
            webTarget = webTarget.queryParam("q", apiRequest.getQ());
        }

        if (apiRequest.getSources() != null) {
            webTarget = webTarget.queryParam("sources", apiRequest.getSources());
        }

        if (apiRequest.getDomains() != null) {
            webTarget = webTarget.queryParam("domains", apiRequest.getDomains());
        }

        if (apiRequest.getFrom() != null) {
            webTarget = webTarget.queryParam("from", apiRequest.getFrom());
        }

        if (apiRequest.getTo() != null) {
            webTarget = webTarget.queryParam("to", apiRequest.getTo());
        }

        if (apiRequest.getLang() != null) {
            webTarget = webTarget.queryParam("language", apiRequest.getLang());
        }

        if (apiRequest.getSortBy() != null) {
            webTarget = webTarget.queryParam("sortBy", apiRequest.getSortBy());
        }

        if (apiRequest.getPageSize() > 0) {
            webTarget = webTarget.queryParam("pageSize", apiRequest.getPageSize());
        }

        if (apiRequest.getPage() > 0) {
            webTarget = webTarget.queryParam("page", apiRequest.getPage());
        }


        return webTarget;
    }
}
