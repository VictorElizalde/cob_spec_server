package java_server;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartialContentParser {
    private static final Pattern bytePattern = Pattern.compile("^([0-9])?-([0-9])?$");
    private String byteRange;

    public PartialContentParser(String byteRange) {
        this.byteRange = byteRange;
    }

    public byte[] getPartialContent(byte[] data) throws Exception {
        Matcher byteMatcher = bytePattern.matcher(byteRange);
        byteMatcher.matches();

        int minRange = getMinRange(byteMatcher.group(1), byteMatcher.group(2), data.length);
        int maxRange = getMaxRange(byteMatcher.group(1), byteMatcher.group(2), data.length);

        return Arrays.copyOfRange(data, minRange, maxRange);
    }

    public int getMinRange(String minMatch, String maxMatch, int dataContentLength) {
        if (minMatch != null) {
            return Integer.parseInt(minMatch);
        } else {
            return dataContentLength - (Integer.parseInt(maxMatch));
        }
    }

    public int getMaxRange(String minMatch, String maxMatch, int dataContentLength) {
        if (maxMatch != null) {
            if (minMatch == null) {
                return dataContentLength;
            } else {
                return Integer.parseInt(maxMatch) + 1;
            }
        } else {
            return dataContentLength;
        }
    }
}
