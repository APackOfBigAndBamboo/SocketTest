package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        /*
          创建tcp服务端
          思路：
          1.创建tcp服务器端服务，服务器端为了让客户端可以连接上，必须提供端口，监听一个端口
          2.获取客户端对象，通过客户端的socket流和对i赢得客户端进行通信
          3.获取客户端的socket流的读取流
          4.读取数据并显示在服务端
          5.关闭资源
         */

        System.out.println("服务器端启动。。。");
        //1
        ServerSocket ss=new ServerSocket(10003);
        //2
        Socket s=ss.accept();
        String ip=ss.getInetAddress().getHostAddress();
        System.out.println(ip+"...connected");
        //3
        InputStream in=s.getInputStream();

        //4
        byte[] buf=new byte[1024];
        int len=in.read(buf);
        String str=new String(buf,0,len);

        System.out.println(str);

        //5
        s.close();
        ss.close();
    }
}
