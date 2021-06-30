import heart.client.Client;

public class ClientMain {

    public static void main(String[] args) {

        String IP = (args.length == 0) ? "127.0.0.1" : args[0];
        int port = (args.length <= 1) ? 2021 : Integer.parseInt(args[1]);
        Client client = new Client();
        client.start(IP, port);
        
    }
}