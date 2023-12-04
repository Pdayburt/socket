package com.anatkh.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务已启动");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        handleSocket(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    private static void handleSocket(Socket socket) throws IOException {
        System.out.println("线程ID："+Thread.currentThread().getId()+
                "  线程名称："+Thread.currentThread().getName());
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int read = is.read(bytes);
        System.out.println("客户端："+new String(bytes,0,read));
        OutputStream os = socket.getOutputStream();
        os.write("没钱".getBytes());
        socket.close();

    }
}
