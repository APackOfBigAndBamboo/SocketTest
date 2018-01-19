package udp.udpliaotian;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpChat {
    public static void main(String[] args) throws SocketException {

        //创建socket服务
        //发送端
        DatagramSocket send = new DatagramSocket(8888);
        DatagramSocket rece = new DatagramSocket(10001);

        new Thread(new Send(send)).start();
        new Thread(new Rece(rece)).start();
    }


}

//负责发送的任务
class Send implements Runnable {
    //任务对象建立，需要socket对象
    private DatagramSocket ds;

    public Send(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        //1,要发送的数据来源 键盘录入
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        //1.1获取录入
        String line = null;
        try {
            while ((line = bufr.readLine()) != null) {


                //1.2将数据变成字节数组，封装到数据包中
                byte[] buf = line.getBytes();

                //2.数据包装到数据包中 实现群聊时发送数据包到广播ip  xxxx。xxxx。xxxx。255
                DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.89.232"), 10001);
                //3.发送数据包

                ds.send(dp);
                if ("over".equals(line)) {
                    break;
                }
            }
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

//负责接受的任务
class Rece implements Runnable {
    private DatagramSocket ds;

    public Rece(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {

        while (true) {
            //1因为接受的数据最终都会存储到数据包中，数据包中必有字节数组，
            byte[] but = new byte[1024];

            //创建数据包
            DatagramPacket dp = new DatagramPacket(but, but.length);

            //将数据存数到数据包中
            try {
                ds.receive(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取数据
            String ip = dp.getAddress().getHostAddress();
            String data = new String(dp.getData(), 0, dp.getLength());


            System.out.println(ip + ";" + data);
            if ("over".equals(data)) {
                System.out.println(ip + "....离开聊天室");
            }
        }
    }
}