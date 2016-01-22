package a;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * fuquanemail@gmail.com 2016/1/18 9:56
 * description:
 * 1.0.0
 */
public class WriteObject {
    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\objectFile.obj"));
            User user = new User();
            user.setId(1);
            user.setName("name");

            out.writeObject(user);

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
