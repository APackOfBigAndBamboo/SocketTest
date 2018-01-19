package udp;

import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceDemo {
    public static void main(String[] args) throws IOException {
        /*
        * 创建udp接收端
        * 创建socket服务，明确端口
        * 收数据
        * 将其中所需要的数据取出来  ip，date，端口
        * 关闭资源
        */
        System.out.println("接收端启动了。。。");
        //1
        DatagramSocket ds =new DatagramSocket(10000);

        //2使用socket接收数据,需要将收到的数据存到数据包中，
        //可以通过数据包对象的方法对收到的数据进行解析
          //2.1 创建一个数据包
        byte[] buf= new  byte[1024];
        DatagramPacket dp=new DatagramPacket(buf,buf.length);

        ds.receive(dp);

        //3 通过数据包对象解析收到的数据，使用数据包的方法

        String ip=dp.getAddress().getHostAddress();
        int port=dp.getPort();
        String str=new String(dp.getData(),0,dp.getLength());
        System.out.println(ip+"..."+port+"..."+str);
    }
}
