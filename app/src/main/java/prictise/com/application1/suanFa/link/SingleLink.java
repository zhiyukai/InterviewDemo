package prictise.com.application1.suanFa.link;

/**
 * @Author zhisiyi
 * @Date 2019.09.23 19:05
 * @Comment
 */
public class SingleLink {

  public static Node reverse(Node node) {
    //单链表为空或只有一个节点，直接返回原单链表
    if (node == null || node.next == null) {
      return node;
    }

    //前一个节点指针
    Node preNode = null;
    //当前节点指针
    Node curNode = node;
    //下一个节点指针
    Node nextNode = node.next;
    while (curNode != null) {
      curNode.next = preNode;
      preNode = curNode;
      curNode = nextNode;

      if (nextNode != null) {
        nextNode = nextNode.next;
      }
    }
    return preNode;
  }

  public static Node reverse2(Node node) {
    Node prev = null;
    while (node != null) {
      Node tmp = node.next;
      node.next = prev;
      prev = node;
      node = tmp;
    }
    return prev;
  }

  public static Node reverse3(Node node) {
    if (node.next == null) {
      return node;
    }
    Node reverseNode = reverse3(node.next);
    node.next.next = node;
    node.next = null;
    return reverseNode;
  }

  public static void main(String[] args) {
    Node node1 = new Node();
    node1.data = 1;

    Node node2 = new Node();
    node2.data = 2;
    Node node3 = new Node();
    node3.data = 3;
    Node node4 = new Node();
    node4.data = 4;

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;

    Node n = reverse3(node1);

    while (n != null) {
      System.out.print(n.data + " ");
      n = n.next;
    }

  }

}
