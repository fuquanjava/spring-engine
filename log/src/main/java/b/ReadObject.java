package b;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * fuquanemail@gmail.com 2016/1/18 9:58
 * description:
 * 1.0.0
 */
public class ReadObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //反序列化对象
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\objectFile.obj"));

        a.User user  = (a.User) in.readObject();    //读取User对象
        System.out.println("user:" + user);
        in.close();

    }
}
