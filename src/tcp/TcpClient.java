package tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) throws IOException {
        /*
    *  思路：
    *  1.建立tcp客户端服务。
    *   1.1因为是面向连接，必须有连接才可以进行通信
    *   1.2在创建客户端是，就必须明确目的地址和端口
    *  2.一旦连接建立，就有了传输数据的通道。就可以在通道中进行数据传输。
    *    这个传输其实就是通过流实现的。就是socket io流
    *  3.获取socket io中的写动作，将数据写到socket流中发给分服务端
    *  4.关闭资源
    */

        System.out.println("客户端启动。。。");

        //1
        Socket s = new Socket("192.168.89.232", 10003);

        //2
        OutputStream out = s.getOutputStream();

        //3
        out.write("注意，tcp客户端来了。。".getBytes());

        //4
        s.close();
    }
}

