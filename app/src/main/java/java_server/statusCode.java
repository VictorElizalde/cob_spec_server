package java_server;

import java.security.KeyException;
import java.util.HashMap;
import java.util.Set;

public class statusCode {
    protected static HashMap<Integer, String> statusCodeMap = new HashMap<Integer, String>();
    public static final String OK = "200 OK";
    public static final String CREATED = "201 Created";
    public static final String PARTIAL_CONTENT = "206 Partial Content";
    public static final String NOT_FOUND = "404 Not Found";
    public static final String METHOD_NOT_ALLOWED = "405 Method Not Allowed";
    public static final String REQUEST_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
    public static final String NOT_IMPLEMENTED = "501 Not Implemented";

//    public static HashMap<Integer, String> getStatusCode() {
//        statusCodeMap.put(200, "200 OK");
//        statusCodeMap.put(201, "201 Created");
//        statusCodeMap.put(206, "206 Partial Content");
//        statusCodeMap.put(404, "404 Not Found");
//        statusCodeMap.put(405, "405 Method Not Allowed");
//        statusCodeMap.put(416, "416 Requested Range Not Satisfiable");
//        statusCodeMap.put(501, "501 Not Implemented");
//
//        return statusCodeMap;
//    }
//
//    public String getStatus(int status) {
//        try {
//            isAValidKey(status);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return getStatusCode().get(status);
//    }

//    public boolean isAValidKey(Integer key) throws Exception {
//        for (Integer code : getKeySet()) {
//            if (key.equals(code))
//                return true;
//        }
//
//        throw new KeyException("Key is Invalid");
//    }

//    private Set<Integer> getKeySet() {
//        return getStatusCode().keySet();
//    }
}
