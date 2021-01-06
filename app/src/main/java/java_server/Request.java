package java_server;

public class Request {
    private String httpMethod;
    private String uri;
    private String fullRequest;
    private String headerField;
    private String byteRange;
    private String basicAuthCredentials;
    private boolean basic;
    private String etag;
    private String parameterValues;

    public String getHTTPMethod() {
        return httpMethod;
    }

    public String getURI() {
        return uri;
    }

    public String getFullRequest() {
        return fullRequest;
    }

    public String getHeaderField() {
        return headerField;
    }

    public String getByteRange() {
        return byteRange;
    }

    public String getBasicAuthCredentials() {
        return basicAuthCredentials;
    }

//    public String getEtag() {
//        return etag;
//    }

//    public String getParameterValues() {
//        return parameterValues;
//    }

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

    public void setHeaderField(String headerField) {
        this.headerField = headerField;
    }

    public void setByteRange(String byteRange) {
        this.byteRange = byteRange;
    }

    public void setBasicRequestStatus(boolean basic) {
        this.basic = basic;
    }

    public boolean isABasicAuthRequest() {
        return basic;
    }

    public void setBasicAuthCredentials(String basicAuthCredentials) {
        this.basicAuthCredentials = basicAuthCredentials;
    }

//    public void setEtag(String etag) {
//        this.etag = etag;
//    }

//    public void setParameterValues(String parameterValues) {
//        this.parameterValues = parameterValues;
//    }
}
