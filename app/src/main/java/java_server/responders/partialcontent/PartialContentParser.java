package java_server.responders.partialcontent;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartialContentParser {
    private static final Pattern bytePattern = Pattern.compile("^([0-9]*)-([0-9]*)$");
    private String byteRange;

    public PartialContentParser(String byteRange) {
        this.byteRange = byteRange;
    }

    public byte[] getPartialContent(byte[] data) throws Exception {
        String[] minMaxRange;
        minMaxRange = getRanges(data);

        return Arrays.copyOfRange(data, Integer.parseInt(minMaxRange[0]), Integer.parseInt(minMaxRange[1]));
    }

    public String[] getRanges(byte[] data) throws Exception {
        String[] minMaxRange = new String[2];
        Matcher byteMatcher = bytePattern.matcher(byteRange);
        byteMatcher.matches();

        if (!byteMatcher.group(1).equals("") && !byteMatcher.group(2).equals("")) {
            if (Integer.parseInt(byteMatcher.group(1)) > Integer.parseInt(byteMatcher.group(2)))
                throw new Exception("Wrong byte format");
        } else if (!byteMatcher.group(1).equals("")) {
            if (data.length == Integer.parseInt(byteMatcher.group(1)))
                throw new Exception("Wrong byte format");
        }

        minMaxRange[0] = String.valueOf(getMinRange(byteMatcher.group(1), byteMatcher.group(2), data.length));
        minMaxRange[1] = String.valueOf(getMaxRange(byteMatcher.group(1), byteMatcher.group(2), data.length));

        return minMaxRange;
    }

    public int getMinRange(String minMatch, String maxMatch, int dataContentLength) {
        if (!minMatch.equals("")) {
            return Integer.parseInt(minMatch);
        } else if (Integer.parseInt(maxMatch) <= dataContentLength) {
            return dataContentLength - (Integer.parseInt(maxMatch));
        } else {
            return 0;
        }
    }

    public int getMaxRange(String minMatch, String maxMatch, int dataContentLength) {
        if (!maxMatch.equals("")) {
            if (Integer.parseInt(maxMatch) <= dataContentLength) {
                if (minMatch.equals("")) {
                    return dataContentLength;
                } else {
                    return Integer.parseInt(maxMatch) + 1;
                }
            }
        }

        return dataContentLength;
    }
}
