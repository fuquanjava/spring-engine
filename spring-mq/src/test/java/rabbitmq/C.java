package rabbitmq;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * spring-engine 2015/12/26 16:53
 * fuquanemail@gmail.com
 */
public final class C {

    public static Map<Integer, String> usedMap = new HashMap<>();

    public static final String FANOUT_EXCHANGE = "fanout-logs";

    static Random random;

    static {
        random = new Random();
    }

    public static String getClient() {
        int i = random.nextInt(9) + 1;
        String clientName = "client-" + i;
        String value = usedMap.get(i);
        if (null == value) {
            usedMap.put(i, clientName);
            return clientName;
        }
        return getClient();
    }

    public static int getRoutType(String [] routType){
        System.err.println("请输入routType序号,最大值:"+(routType.length-1));
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        return type;
    }

    public static String getMessage(){
        System.err.println("请输入发送的message:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line;
    }

    public static void main(String[] args) {
        System.err.println(getRoutType(new String []{"aa"}));
        System.err.println(getMessage());
    }
}
