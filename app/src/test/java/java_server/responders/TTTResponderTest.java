package java_server.responders;

import java_server.httpserver.StatusCode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TTTResponderTest {
    private StatusCode statusCode;

    @Test
    public void returnsBoardHeader() throws Exception {
        String uri = "ttt";
        String httpMethod = "GET";
        String data = "1 2 3 4 5 6 7 8 9";

        TTTResponder tttResponder = new TTTResponder(httpMethod, uri, data);
        tttResponder.processResponse();
        byte[] fileBytes = tttResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("<h1>TTT Board</h1>"));
        Assert.assertEquals(statusCode.OK, tttResponder.getStatusCode());
    }

    @Test
    public void returnsBoard() throws Exception {
        String uri = "ttt";
        String httpMethod = "GET";
        String data = "1 2 3 4 5 6 7 8 9";

        TTTResponder tttResponder = new TTTResponder(httpMethod, uri, data);
        tttResponder.processResponse();
        byte[] fileBytes = tttResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("<a>| </a><a>1</a><a> | </a><a>2</a><a> | </a><a>3</a><a> |</a> <br>"));
        assertTrue(new String(fileBytes).contains("<a>| </a><a>4</a><a> | </a><a>5</a><a> | </a><a>6</a><a> |</a> <br>"));
        assertTrue(new String(fileBytes).contains("<a>| </a><a>7</a><a> | </a><a>8</a><a> | </a><a>9</a><a> |</a> <br>"));
        Assert.assertEquals(statusCode.OK, tttResponder.getStatusCode());
    }

    @Test
    public void returnsBoardWithHrefOnTokens() throws Exception {
        String uri = "ttt";
        String httpMethod = "GET";
        String data = "1 2 X 4 5 O 7 8 9";

        TTTResponder tttResponder = new TTTResponder(httpMethod, uri, data);
        tttResponder.processResponse();
        byte[] fileBytes = tttResponder.getMessageBody();

        assertTrue(new String(fileBytes).contains("<a>| </a><a>1</a><a> | </a><a>2</a><a> | </a><a href='/asdf'>X</a><a> |</a> <br>"));
        assertTrue(new String(fileBytes).contains("<a>| </a><a>4</a><a> | </a><a>5</a><a> | </a><a href='/asdf'>O</a><a> |</a> <br>"));
        assertTrue(new String(fileBytes).contains("<a>| </a><a>7</a><a> | </a><a>8</a><a> | </a><a>9</a><a> |</a> <br>"));
        Assert.assertEquals(statusCode.OK, tttResponder.getStatusCode());
    }
}

