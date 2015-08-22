package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * spring-demo 2015/7/25 16:39
 * fuquanemail@gmail.com
 */
public class SerializeUtil {
    public SerializeUtil() {
    }

    public static byte[] serialize(Object object) {
        if(object == null) {
            return null;
        } else {
            ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;

            try {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                byte[] e = baos.toByteArray();
                return e;
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static Object unserialize(byte[] bytes) {
        if(bytes == null) {
            return null;
        } else {
            ByteArrayInputStream bais = null;

            try {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream e = new ObjectInputStream(bais);
                return e.readObject();
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }
}
