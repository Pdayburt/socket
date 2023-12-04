package com.anatkh.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) throws IOException {
        while (true) {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream os = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            os.write(msg.getBytes());

            byte[] bytes = new byte[1024];
            InputStream is = socket.getInputStream();
            int read = is.read(bytes);
            String s = new String(bytes, 0, read);
            System.out.println("老板说：" + s);
            socket.close();
        }
    }
}
