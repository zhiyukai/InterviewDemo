package prictise.com.application1.suanFa.link;

/**
 * @Author zhisiyi
 * @Date 2019.12.13 22:12
 * @Comment
 */
public class Reverse {

    static class Node {

        String name;
        Node next;

        public Node(String name, Node next) {
            this.name = name;
            this.next = next;
        }
    }

    private static Node work(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root.next;
        while (temp != null) {
            root.next = root;
            temp = temp.next;
            root.next = temp;
        }

        return root;
    }

    public void t() {
        Node origin = null;
        Node newNode = null;

        origin.next.next = null;

        Node newNode1 = null;
        if (newNode != null) {
            newNode1 = newNode.next;
            newNode.next = origin;
        }

        Node newNode2 = null;
        if (newNode1 != null) {
            newNode2 = newNode1.next;
            newNode1.next = newNode;
        }
        Node newNode3 = null;
        if (newNode2 != null) {
            newNode3 = newNode2.next;
            newNode2.next = newNode1;
        }

        Node newNode4 = null;
        if (newNode3 != null) {
            newNode4 = newNode3.next;
            newNode3.next = newNode2;
        }
    }

    public static void main(String[] args) {
        Node n4 = new Node("D", null);
        Node n3 = new Node("C", n4);
        Node n2 = new Node("B", n3);
        Node n1 = new Node("A", n2);

        test3(n1);
    }

    public static void test1(Node root) {
        Node pre = null;
        Node cur = root;
        Node next = null;

        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        print(pre);
    }

    public static void test2(Node root) {
        Node head = new Node("off", null);
        Node p = root;
        Node pNext = p.next;
        head.next = root;
        while(pNext != null) {
            p.next = pNext.next;
            pNext.next = head.next;
            head.next = pNext;
            pNext = p.next;
        }
        print(head.next);
    }

    public static void test3(Node root) {
        //单链表为空或只有头结点或只有一个元素，不用进行逆置操作
        if (root == null || root.next == null || root.next.next == null) {
            return;
        }
        Node p = root.next.next;//令p指向线性表中第2个元素a2
        root.next.next = null;//令线性表中第1个元素a1的next为空
        while (p != null) {
            Node q = p.next;
            //将p插入头结点之后
            p.next = root.next;
            root.next = p;
            p = q;//继续访问下一个元素
        }
    }


    private static void print(Node root) {
        while (root != null) {
            System.out.print(root.name + " ");
            root = root.next;
        }
    }


}
