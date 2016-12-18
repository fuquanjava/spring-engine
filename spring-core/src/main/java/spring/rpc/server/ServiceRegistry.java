package spring.rpc.server;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * fuquanemail@gmail.com 2016/5/12 10:19
 * description:
 * 1.0.0
 */
public class ServiceRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRegistry.class);


    public final static int ZK_SESSION_TIMEOUT = 5000;

    public final static String ZK_REGISTRY_PATH = "/myregistry";
    public final static String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/mydata";


    private CountDownLatch latch = new CountDownLatch(1);

    private String registryAddress;


    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                createNode(zk, data);
            }
        }
    }

    /**
     * 首先需要使用 ZooKeeper 客户端命令行创建/registry永久节点，用于存放所有的服务临时节点。
     *
     * @return ZooKeeper
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            LOG.error("连接zookeeper服务器异常", e);
        }
        return zk;
    }

    /**
     * 临时节点
     *
     * @param zk
     * @param data
     */
    private void createNode(ZooKeeper zk, String data) {
        try {
            byte[] bytes = data.getBytes();

            String path = zk.create(ZK_REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            LOG.info("path={}", path);

            path = zk.create(ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            LOG.debug("create zookeeper node ({} => {})", path, data);
        } catch (KeeperException | InterruptedException e) {
            LOG.error("创建zookeeper节点异常", e);
        }
    }

    public static void main(String[] args) {

        ServiceRegistry registry = new ServiceRegistry("127.0.0.1:2181");
        registry.register("test");
    }

}

