package kmavn.giaynhap.udptrackingclient;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketBuilder {
    public static byte[] createLogBuffer(String event)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(stream);
        // Write commad;
        try {
            dataStream.writeByte(1);
            dataStream.writeUTF(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }
}
