package com.company.learn.RPC.server.entity;

import com.company.learn.RPC.protocal.Invocation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 启动一个线程监听socket是否有请求到达
 */
public class Listener extends Thread {
    private ServerSocket socket;
    private Server server;

    public Listener(Server server) {
        this.server = server;
    }

    @Override
    public void run(){
        System.out.println("启动服务器中，打开端口" + server.getPort());
        try {
            socket = new ServerSocket(server.getPort());
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        while (server.isRunning()) {
            try {
                System.out.println("等待请求");
                Socket client = socket.accept(); // 阻塞等待客户端发来一个需要处理的请求（终止代码执行等待请求到来）
                System.out.println("请求到来");
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                Invocation invo = (Invocation) ois.readObject();
                System.out.println("远程调用:" + invo);

                // 调用call反射调用方法
                server.call(invo);

                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(invo);
                oos.flush();
                oos.close();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            if (socket != null && !socket.isClosed()){
                System.out.println("关闭连接");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
