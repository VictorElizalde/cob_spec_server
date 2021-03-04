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
        String coordinates = "";
        String responseHtml = "<h1>TTT Board</h1> <br><br>";

        for (int i = 0; i < boardValues.length; i++) {
            if (boardValues[i].equals("X") || boardValues[i].equals("O")){
                boardValues[i] = "<a>" + boardValues[i] + "</a>";
            } else {
                coordinates = getPositionCoordinates(i);
                boardValues[i] = "<a href='http://localhost:4000/move?coor=" + coordinates + "' target=\"_blank\">" + boardValues[i] + "</a>";
            }
        }

        responseHtml += "<a>| </a>" + boardValues[0] + "<a> | </a>" + boardValues[1] + "<a> | </a>" + boardValues[2] + "<a> |</a> <br>";
        responseHtml += "<a>| </a>" + boardValues[3] + "<a> | </a>" + boardValues[4] + "<a> | </a>" + boardValues[5] + "<a> |</a> <br>";
        responseHtml += "<a>| </a>" + boardValues[6] + "<a> | </a>" + boardValues[7] + "<a> | </a>" + boardValues[8] + "<a> |</a> <br>";

        body = responseHtml.getBytes();
    }

    private String getPositionCoordinates(int position){
        String coordinates = "";
        switch (position) {
            case 0:
                coordinates = "0,0";
                break;
            case 1:
                coordinates = "0,1";
                break;
            case 2:
                coordinates = "0,2";
                break;
            case 3:
                coordinates = "1,0";
                break;
            case 4:
                coordinates = "1,1";
                break;
            case 5:
                coordinates = "1,2";
                break;
            case 6:
                coordinates = "2,0";
                break;
            case 7:
                coordinates = "2,1";
                break;
            case 8:
                coordinates = "2,2";
                break;
        }

        return coordinates;
    }
}
