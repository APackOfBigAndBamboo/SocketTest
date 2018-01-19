package tcp.tcp1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest1 {
    //需求：服务端收到客户端发送送的数据并反馈数据给客户端，应答
    public static void main(String[] args) throws IOException {
        System.out.println("服务器端启动。。。");
        //1
        ServerSocket ss=new ServerSocket(10004);
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

//反馈
        OutputStream out=s.getOutputStream();
        out.write("Ok,我知道你来了".getBytes());
        //5
        s.close();
        ss.close();
    }
}
