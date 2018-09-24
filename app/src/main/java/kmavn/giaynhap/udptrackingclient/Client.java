package kmavn.giaynhap.udptrackingclient;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    private static Client instance;
    private   InetAddress IPAddress;
    public static  Client getInstance(){
        if (instance==null) instance = new Client();
        return instance;
    }
    DatagramSocket clientSocket = null;
    int port;
    private  Client()  {

        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    public void connect(String host,int port) throws UDPTrackingException, UnknownHostException {
        if (clientSocket==null) throw  new UDPTrackingException("client UDP init error.");
        this.port = port;
        IPAddress = InetAddress.getByName(host);
    }
    public void send(byte[] sendData) throws IOException, UDPTrackingException {
        if (clientSocket==null) throw  new UDPTrackingException("client UDP init error.");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);
    }
    public void sendAsync(final byte[] sendData)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                try {
                    clientSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void logEvent(String event)
    {
       final byte[] data= PacketBuilder.createLogBuffer(event);
       this.sendAsync(data);
    }

}
