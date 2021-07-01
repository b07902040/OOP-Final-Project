import hearthclone.server.Server;

public class ServerMain {

    public static void main(String[] args) {

        int port = (args.length == 0) ? 2021 : Integer.parseInt(args[0]);
        Server server = new Server();
        server.start(port);

    }
}
