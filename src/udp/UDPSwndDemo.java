package udp;

import java.io.IOException;
import java.net.*;

public class UDPSwndDemo {
    public static void main(String[] args) throws IOException {
        /*
        *需求：建立 udp的发送端
        *
        * 1、建立可以实现udp传输的socket服务。
        * 2、明确发送的数据
        * 3、通过socket服务将具体的数据发送出去
        * 4、关闭服务
        * */

        System.out.println("UDP发送端启动。。。");
         //1、
        DatagramSocket datagramSocket=new DatagramSocket(8888);
        //2
        String str="注意。UdP commig";
        //3 发送数据，将数据封装到数据包中
          //3.1 将数据封装到数据包对象中，数据包会明确目的地址和端口
        byte[] buf=str.getBytes();
        DatagramPacket dp=new DatagramPacket(buf,buf.length,
                InetAddress.getByName("192.168.89.232"),10000);
          //发送
        datagramSocket.send(dp);
        datagramSocket.close();
    }
}