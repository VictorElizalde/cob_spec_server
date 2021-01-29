package java_server.httpserver;

public class StatusCode {
    public static final String OK = "200 OK";
    public static final String CREATED = "201 Created";
    public static final String PARTIAL_CONTENT = "206 Partial Content";
    public static final String UNAUTHORIZED = "401 Unauthorized";
    public static final String NOT_FOUND = "404 Not Found";
    public static final String METHOD_NOT_ALLOWED = "405 Method Not Allowed";
    public static final String REQUEST_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
    public static final String NOT_IMPLEMENTED = "501 Not Implemented";

    private String activeStatus;

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
}
