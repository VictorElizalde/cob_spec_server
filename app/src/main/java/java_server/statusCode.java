package java_server;

import java.security.KeyException;
import java.util.HashMap;
import java.util.Set;

public class statusCode {
    protected static HashMap<Integer, String> statusCodeMap = new HashMap<Integer, String>();

    public static HashMap<Integer, String> getStatusCode() {
        statusCodeMap.put(200, "200 OK");
        statusCodeMap.put(404, "404 Not Found");
        statusCodeMap.put(405, "405 Method Not Allowed");
        statusCodeMap.put(501, "501 Not Implemented");

        return statusCodeMap;
    }

    public String getStatus(int status) {
        try {
            isAValidKey(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getStatusCode().get(status);
    }

    public boolean isAValidKey(Integer key) throws Exception {
        for (Integer code : getKeySet()) {
            if (key.equals(code))
                return true;
        }

        throw new KeyException("Key is Invalid");
    }

    private Set<Integer> getKeySet() {
        return getStatusCode().keySet();
    }
}
