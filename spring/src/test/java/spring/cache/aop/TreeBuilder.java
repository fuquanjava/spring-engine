package spring.cache.aop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: fuquanemail@gmail.com 2015/10/20 8:53
 * description:
 * @version: 1.0.0
 */
public class TreeBuilder {
    List<TreeBuilder.Node> nodes = new ArrayList<TreeBuilder.Node>();

    public TreeBuilder(List<Node> nodes) {
        super();
        this.nodes = nodes;
    }

    /**
     * 构建JSON树形结构
     *
     * @return
     */
    public String buildJSONTree() {
        List<Node> nodeTree = buildTree();
        //JSONArray jsonArray = JSONArray.fromObject(nodeTree);
        //return jsonArray.toString();
        return null;
    }

    /**
     * 构建树形结构
     *
     * @return
     */
    public List<Node> buildTree() {
        List<Node> treeNodes = new ArrayList<Node>();
        List<Node> rootNodes = getRootNodes();
        for (Node rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     *
     * @param node
     */
    public void buildChildNodes(Node node) {
        List<Node> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (Node child : children) {
                buildChildNodes(child);
            }
            node.setMenus(children);
        }
    }

    /**
     * 获取父节点下所有的子节点
     *
     * @param pnode
     * @return
     */
    public List<Node> getChildNodes(Node pnode) {
        List<Node> childNodes = new ArrayList<Node>();
        for (Node n : nodes) {
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    /**
     * 判断是否为根节点
     * @return
     */
    public boolean rootNode(Node node) {
        boolean isRootNode = true;
        for (Node n : nodes) {
            if (node.getPid().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    /**
     * 获取集合中所有的根节点
     *
     * @return
     */
    public List<Node> getRootNodes() {
        List<Node> rootNodes = new ArrayList<Node>();
        for (Node n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

    public static class Node {

        private String id;
        private String pid;
        private String text;
        private String url;
        private List<Node> menus;

        public Node() {
        }

        public Node(String id, String pid, String text, String url) {
            super();
            this.id = id;
            this.pid = pid;
            this.text = text;
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<Node> getMenus() {
            return menus;
        }

        public void setMenus(List<Node> menus) {
            this.menus = menus;
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        Node p1 = new Node("01", "", "01", "");
        Node p6 = new Node("02", "", "02", "");
        Node p7 = new Node("0201", "02", "0201", "");
        Node p2 = new Node("0101", "01", "0101", "");
        Node p3 = new Node("0102", "01", "0102", "");
        Node p4 = new Node("010101", "0101", "010101", "");
        Node p5 = new Node("010102", "0101", "010102", "");

        nodes.add(p1);
        nodes.add(p2);
        nodes.add(p3);
        nodes.add(p4);
        nodes.add(p5);
        nodes.add(p6);
        nodes.add(p7);

        TreeBuilder builder = new TreeBuilder(nodes);
        builder.buildJSONTree();
    }

}
