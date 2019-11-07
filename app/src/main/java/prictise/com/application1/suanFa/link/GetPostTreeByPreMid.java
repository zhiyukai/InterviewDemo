package prictise.com.application1.suanFa.link;

/**
 * @Author zhisiyi
 * @Date 2019.09.28 22:44
 * @Comment
 */
public class GetPostTreeByPreMid {

  /**
   * 题目:根据二叉树的先序遍历和中序遍历,输出后序遍历 很经典的一道题目,要了解二叉树先序输出和后序输出的特点
   *
   * 易错点: 1.一定要分清指针指向的位置. 2.判断数据是否正确 a.在程序中间判断数据是否正确的可以处理大部分情况,但是到了叶子节点的时候就处理不到 所以在叶子节点还需要再单独判断
   */
  /**
   * 数据正确与错误的标志,true代表数据错误
   */
  private static boolean flag = false;

  //定义一个内部节点类
  public static class Node<E> {

    public Node left;
    public Node right;
    public E e;

    public Node(E e) {
      this.e = e;
    }
  }

  /**
   * 根据先序遍历和中序遍历构建二叉树,并返回跟节点
   *
   * @param preOrder 先序遍历数据存储的数组
   * @param preStart 先序遍历数组的起始位置
   * @param preEnd 先序遍历数组的结束位置
   * @param midOrder 后序遍历数据存储的数组
   * @param midStart 后序遍历数组的起始位置
   * @param midEnd 后序遍历数组的结束位置
   */
  public static Node<Integer> reBuild_BinaryTree(int[] preOrder, int preStart, int preEnd,
      int[] midOrder, int midStart, int midEnd) {
    //增加鲁棒性
    if (preOrder.length != midOrder.length || preOrder.length == 0 || midOrder.length == 0) {
      throw new RuntimeException("date not correct");
    }
    if (preOrder == null || midOrder == null) {
      throw new RuntimeException("Null point Exception");
    }
    //递归结束标志,如果大于则说明数组越界了,空节点,直接返回
    if (midStart > midEnd && preStart > preEnd) {
      return null;
    }
    //等于说明遍历到了最后一个节点,构建节点返回
    if (midStart == midEnd && preStart == preEnd) {
      //此时midStart和preStart理应是同一个数字,但是如果不是呢?那就说明数据不正确
      if (preOrder[preStart] != midOrder[midStart]) {
        System.out.println("该数据不是一个正确的二叉树的先序遍历和中序遍历");
        flag = true;
        return null;
      }
      return new Node(midOrder[midEnd]);
    }
    int mid;//指向中序遍历中每一次的跟节点.
    int pre;//指向先序遍历中左子树的结束位置
    //找出中序遍历中根节点的位置
    for (mid = midStart; mid < midEnd; mid++) {
      if (midOrder[mid] == preOrder[preStart]) {
        break;
      }
    }
    //如果输入的数据不正确,应该输出提示
    if (mid == midOrder.length) {
      System.out.println("该数据不是一个正确的二叉树的先序遍历和中序遍历");
      flag = true;
      return null;
    }
    Node node = new Node(preOrder[preStart]);
    //找出先序遍历中左子树的结束位置
    pre = preStart + (mid - midStart);
    //递归构建二叉树,这里很容易出错,要注意
    //preOrder的左子树:preStart+1,pre  右子树:pre+1,preEnd;
    //midOrder的左子树:midStart,mid-1     右子树:mid+1,midEnd
    node.left = reBuild_BinaryTree(preOrder, preStart + 1, pre, midOrder, midStart, mid - 1);
    node.right = reBuild_BinaryTree(preOrder, pre + 1, preEnd, midOrder, mid + 1, midEnd);
    return node;
  }


  public static Node<Integer> reBuildTree(int[] preOrder, int preStart, int preEnd, int[] midOrder,
      int midStart,
      int midEnd) {
    if (preOrder == null || midOrder == null) {
      return null;
    }

    if (preStart > preEnd || midStart > midEnd) {
      return null;
    }

    if (preOrder.length == 1 && midOrder.length == 1) {
      if (preOrder[preStart] != midOrder[midStart]) {
        System.out.print("数据不是一个正确的二叉树");
        return null;
      }
    }

    if (preStart == preEnd && midStart == midEnd) {
      if (preOrder[preStart] != midOrder[midStart]) {
        System.out.print("数据不是一个正确的二叉树");
        return null;
      }
      return new Node<>(midOrder[midStart]);
    }

    Node<Integer> root = new Node<>(preOrder[preStart]);
    int mid = 0;
    int pre = 0;
    for (mid = midStart; mid < midEnd; mid++) {
      if (preOrder[preStart] == midOrder[mid]) {
        break;
      }
    }
    pre = preStart + mid - midStart;
    root.left = reBuildTree(preOrder, preStart + 1, pre, midOrder, midStart, mid - 1);
    root.right = reBuildTree(preOrder, pre + 1, preEnd, midOrder, mid + 1,
        midEnd);

    return root;
  }

  /**
   * 后序遍历输出二叉树
   */
  public static void postOrder_print(Node head) {
    if (flag) {
      return;//数据错误,直接返回
    }
    if (head == null) {
      return;
    }
    postOrder_print(head.left);
    postOrder_print(head.right);
    System.out.println(head.e);
  }

  public static void main(String[] args) {
    int[] preOrder = {1, 3, 5, 7};
    int[] midOrder = {3, 1, 7, 5};
    Node<Integer> head = reBuildTree(preOrder, 0, preOrder.length - 1, midOrder, 0,
        midOrder.length - 1);
    postOrder_print(head);
  }

}
