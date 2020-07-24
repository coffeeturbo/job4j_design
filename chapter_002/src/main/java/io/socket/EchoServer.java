package io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean execute = true;
            while (execute) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {
                    String str;

                    String msg = "";
                    do {
                        str = in.readLine();
                        if (str.contains("?msg=Exit")) {
                            execute = false;
                        } else if (str.contains("?msg=Hello")) {
                            msg = "Hello";
                        } else if (str.contains("?msg=What")) {
                            msg = "What";
                        } else if (str.contains("?msg=")) {
                            msg = cutTheMessage(str);
                        }

                    } while (!str.isEmpty());

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(msg.getBytes());
                }
            }
        }
    }

    private static String cutTheMessage(String line) {
        String[] values = line.split("msg=");
        return values[1].split(" ")[0];
    }
}
