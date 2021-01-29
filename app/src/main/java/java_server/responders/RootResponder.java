package java_server.responders;

import java.io.File;

public class RootResponder implements Responder {
    private String serverViewsDirectory;
    private String directoryLinks;
    public byte[] body;

    public RootResponder(String serverViewsDirectory) {
        this.serverViewsDirectory = serverViewsDirectory;
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
        File directory = new File(serverViewsDirectory);

        if (directory != null) {
            directoryLinks = "";
            for (String file : directory.list()) {
                directoryLinks += addLinkHTML(file);
            }
            body = directoryLinks.getBytes();
        } else {
            body = "File Could Not Be Read".getBytes();
        }
    }

    private String addLinkHTML(String fileName){
        return "<li> <a href='/" + fileName + "'>" + fileName + "</a> </li>";
    }
}
