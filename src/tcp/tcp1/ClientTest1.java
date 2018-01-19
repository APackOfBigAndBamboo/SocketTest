package tcp.tcp1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTest1 {

    //需求::客户端发送数据给服务端，并读取服务端反馈的数据
    public static void main(String[] args) throws IOException {
        System.out.println("客户端启动");
        Socket s = new Socket("192.168.89.232", 10004);
        OutputStream out=s.getOutputStream();
        out.write("服务端，我来了。。。".getBytes());

        InputStream in=s.getInputStream();
        byte[] buf=new byte[1024];

        int len=in.read(buf);
        String str=new String(buf,0,len);

        System.out.println(str);
        s.close();
    }
}
