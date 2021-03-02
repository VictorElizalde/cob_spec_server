package java_server.responders;

public class TTTResponder implements Responder {
    private String uri;
    private String httpMethod;
    private String data;
    public byte[] body;

    public TTTResponder(String httpMethod, String uri, String data) {
        this.httpMethod = httpMethod;
        this.uri = uri;
        this.data = data;
    }

    @Override
    public byte[] getMessageBody() {
        return body;
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }

    @Override
    public void processResponse() {
        if (data.equals("")) {
            data = "1 2 3 4 5 6 7 8 9";
        }

        String[] boardValues = data.split(" ");
        String responseHtml = "<h1>TTT Board</h1> <br><br>";

        for (int i = 0; i < boardValues.length; i++) {
            if (boardValues[i].equals("X") || boardValues[i].equals("O")){
                boardValues[i] = "<a>" + boardValues[i] + "</a>";
            } else {
                boardValues[i] = "<a href='/asdf'>" + boardValues[i] + "</a>";
            }
        }

        responseHtml += "<a>| </a>" + boardValues[0] + "<a> | </a>" + boardValues[1] + "<a> | </a>" + boardValues[2] + "<a> |</a> <br>";
        responseHtml += "<a>| </a>" + boardValues[3] + "<a> | </a>" + boardValues[4] + "<a> | </a>" + boardValues[5] + "<a> |</a> <br>";
        responseHtml += "<a>| </a>" + boardValues[6] + "<a> | </a>" + boardValues[7] + "<a> | </a>" + boardValues[8] + "<a> |</a> <br>";

        body = responseHtml.getBytes();
    }
}
