package java_server;

public class Request {
    private String httpMethod;
    private String uri;
    private String fullRequest;

    public String getHTTPMethod() {
        return httpMethod;
    }

    public String getURI() {
        return uri;
    }

    public String getFullRequest() {
        return fullRequest;
    }

    public void setHTTPMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setURI(String uri) {
        this.uri = uri;
    }

    public void setFullRequest(String fullRequest) {
        this.fullRequest = fullRequest;
    }

    public boolean isARootRequest() {
        return getURI().equals("/");
    }
}
