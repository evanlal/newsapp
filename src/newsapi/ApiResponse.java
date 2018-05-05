package newsapi;

/**
 * A response from the API
 */
public class ApiResponse {
    private String status;
    private String code;
    private String message;
    private String json;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status='" + status + '\'' +
                ", errorCode='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
