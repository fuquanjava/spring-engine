package rabbitmq.client.rpc;

/**
 * spring-engine 2015/12/27 22:10
 * fuquanemail@gmail.com
 */
public class Test {
    public static void main(String[] args) {
        RpcClient fibonacciRpc = new RpcClient();
        System.out.println(" [x] Requesting fib(3)");
        String response = null;
        try {
            long s1 = System.currentTimeMillis();
            response = fibonacciRpc.call("3");
            long s2 = System.currentTimeMillis();
            System.err.println("s2 - s1:" +(s2 - s1) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" [.] Got '" + response + "'");
        //fibonacciRpc.close();

    }
}
