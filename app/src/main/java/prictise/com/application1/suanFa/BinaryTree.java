package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.08.14 09:33
 * @Comment
 */
public class BinaryTree implements AbsBinaryTree {

  private TreeNode root;

  public TreeNode getRoot() {
    return this.root;
  }

  public void setRoot(TreeNode root) {
    this.root = root;
  }

  // 二叉排序树查找节点
  // 找到和key相等则返回相应节点，否则返回 null。
  @Override
  public TreeNode find(int key) {
    // TODO Auto-generated method stub
    TreeNode currentNode = this.root;
    // currentNode.key和 key不等才需要循环
    while ((currentNode != null) && (currentNode.key != key)) {
      if (key < currentNode.key) {
        currentNode = currentNode.leftChild;
      } else if (key > currentNode.key) {
        currentNode = currentNode.rightChild;
      }
    }
    return currentNode;

  }

  @Override
  public void insert(int key, int value) {
    // TODO Auto-generated method stub
    if (this.root == null) {
      this.root = new TreeNode(key, value);
      return;
    }
    TreeNode currentNode = this.root;
    TreeNode parentNode = this.root;// 指向currentNode节点的父节点
    boolean isLeftChild = true;
    // 寻找插入位置
    while (currentNode != null) {
      parentNode = currentNode;
      if (key < currentNode.key) {
        currentNode = currentNode.leftChild;
        isLeftChild = true;
      } else if (key > currentNode.key) {
        currentNode = currentNode.rightChild;
        isLeftChild = false;
      } else {
        // 插入的节点key和二叉树中节点key相等无需插入
        // parentNode 和 currentNode两个引用指向相同Node对象，引用变量相等，只需要更改value
        break;
      }
    }
    // 插入节点
    if (parentNode != currentNode) {
      TreeNode newNode = new TreeNode(key, value);
      if (isLeftChild) {
        parentNode.leftChild = newNode;
      } else {
        parentNode.rightChild = newNode;
      }

    } else {
      // 如果待插入节点和二叉树中节点一样；则只要更改值
      currentNode.setValue(value);
    }
  }

  @Override
  public boolean delete(int key) {
    // TODO Auto-generated method stub
    TreeNode currentNode = this.root;// 用来保存待删除节点
    TreeNode parentNode = this.root;// 用来保存待删除节点的父亲节点
    boolean isLeftChild = true;// 用来保存待删除节点是父亲节点的左孩子还是右孩子
    // 寻找删除节点并记录删除节点的父节点以及他是父节点的左孩子还是右孩子
    while ((currentNode != null) && (currentNode.key != key)) {
      parentNode = currentNode;
      if (key < currentNode.key) {
        currentNode = currentNode.leftChild;
        isLeftChild = true;
      } else {
        currentNode = currentNode.rightChild;
        isLeftChild = false;
      }
    }
    if (currentNode == null) {
      return false;// 空树
    }
    // 要删除的节点为叶子节点，删除的第一种情况
    if ((currentNode.leftChild == null) && (currentNode.rightChild == null)) {
      if (currentNode == this.root) {
        this.root = null;
      } else if (isLeftChild) {
        parentNode.leftChild = null;
      } else {
        parentNode.rightChild = null;
      }
      // 要删除的节点只有左孩子 第二种情况
    } else if ((currentNode.rightChild == null) && (currentNode.leftChild != null)) {
      if (currentNode == this.root) {
        this.root = currentNode.leftChild;
      } else if (isLeftChild) {
        parentNode.leftChild = currentNode.leftChild;
      } else {
        parentNode.rightChild = currentNode.leftChild;
      }
      // 要删除的节点只有右孩子 第三种情况
    } else if ((currentNode.leftChild == null) && (currentNode.rightChild != null)) {
      if (currentNode == this.root) {
        this.root = currentNode.rightChild;
      } else if (isLeftChild) {
        parentNode.leftChild = currentNode.rightChild;
      } else {
        parentNode.rightChild = currentNode.rightChild;
      }
    } // 最后一种情况，待删除节点既有左子树又有右子树
    else {
      // 将待删除节点的右子树最小节点赋值给删除节点的key,value，那么删除后新的二叉树也是二叉排序树
      // 思路：删除右子树中key值最小的节点，并返回，然后用这个节点的值赋值删除节点的key和value
      // 右子树中key最小的节点一定不含左子树,所以删除这个key最小的节点一定是属于叶子节点或者只有右子树的节点
      TreeNode directPostNode = this.getDirectPostNode(currentNode);
      currentNode.key = directPostNode.key;
      currentNode.value = directPostNode.value;
    }

    return true;
  }

  // 获取到待删除节点的中序直接后继节点。将该后继节点从二叉树中删除并返回
  @Override
  public TreeNode getDirectPostNode(TreeNode delNode) {
    // TODO Auto-generated method stub
    // 方法作用为得到待删除节点的直接后继节点

    TreeNode parentNode = delNode;// 用来保存待删除节点的直接后继节点的父亲节点
    TreeNode direcrPostNode = delNode;// 用来保存待删除节点的直接后继节点
    TreeNode currentNode = delNode.rightChild;// 待删除节点右子树
    while (currentNode != null) {
      parentNode = direcrPostNode;
      direcrPostNode = currentNode;
      currentNode = currentNode.leftChild;
    }
    if (direcrPostNode != delNode.rightChild) {// 从树中删除此直接后继节点
      parentNode.leftChild = direcrPostNode.rightChild;// 后继节点的父节点指向后继节点的右孩子
      direcrPostNode.rightChild = null;// 直接后继节点右孩子为空
    }
    return direcrPostNode;// 返回此直接后继节点
  }

  @Override
  public void preOrder(TreeNode rootNode) {
    // TODO Auto-generated method stub
    if (rootNode != null) {
      System.out.println(rootNode.key + " " + rootNode.value);
      this.preOrder(rootNode.leftChild);
      this.preOrder(rootNode.rightChild);
    }
  }

  @Override
  public void inOrder(TreeNode rootNode) {
    // TODO Auto-generated method stub
    if (rootNode != null) {
      this.inOrder(rootNode.leftChild);
      System.out.println(rootNode.key + " " + rootNode.value);
      this.inOrder(rootNode.rightChild);
    }
  }

  @Override
  public void postOrder(TreeNode rootNode) {
    // TODO Auto-generated method stub
    if (rootNode != null) {
      this.postOrder(rootNode.leftChild);
      this.postOrder(rootNode.rightChild);
      System.out.println(rootNode.key + " " + rootNode.value);
    }
  }

  /**
   * // 基于二叉排序树查找find查找节点，然后通过Node的setValue将新值赋值过去。
   */
  @Override
  public boolean update(int key, int value) {
    // TODO Auto-generated method stub
    TreeNode node = this.find(key);
    node.setValue(value);
    return true;
  }


  public static void main(String[] args) {
    // TODO Auto-generated method stub
    BinaryTree tree = new BinaryTree();
    tree.insert(6, 6);// 插入操作,构造图一所示的二叉树
    tree.insert(3, 3);
    tree.insert(14, 14);
    tree.insert(16, 16);
    tree.insert(10, 10);
    tree.insert(9, 9);
    tree.insert(13, 13);
    tree.insert(11, 11);
    tree.insert(12, 12);
    System.out.println("删除前中序遍历结果，结果是一个递增有序序列");
    tree.inOrder(tree.getRoot());// 中序遍历操作
//    tree.update(12, 200);
//    System.out.println("更新节点值中序遍历结果  key=12的值");
//    tree.inOrder(tree.getRoot());
//    System.out.println("删除节点10之后遍历结果");
//
//    tree.delete(13);// 删除操作
//    tree.inOrder(tree.getRoot());
  }
}
