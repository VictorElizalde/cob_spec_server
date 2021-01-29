package java_server.responders;

import java.io.File;

public class RootResponder implements Responder {
    private String serverViewsDirectory;
    private String directoryLinks;

    public RootResponder(String serverViewsDirectory) {
        this.serverViewsDirectory = serverViewsDirectory;
    }

    @Override
    public byte[] getMessageBody() {
        File directory = new File(serverViewsDirectory);

        if (directory != null) {
            directoryLinks = "";
            for (String file : directory.list()) {
                directoryLinks += addLinkHTML(file);
            }

            return directoryLinks.getBytes();
        }

        return "File Could Not Be Read".getBytes();
    }

    @Override
    public String getStatusCode() {
        return statusMessageCode.OK;
    }

    private String addLinkHTML(String fileName){
        return "<li> <a href='/" + fileName + "'>" + fileName + "</a> </li>";
    }
}
