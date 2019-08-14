package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.08.14 16:11
 * @Comment https://blog.csdn.net/isea533/article/details/80345507
 */

/**
 * Java 语言: 二叉查找树
 *
 * @author skywang
 * @date 2013/11/07
 */

public class BSTree<T extends Comparable<T>> {

  BSTNode<T> mRoot;    // 根结点

  public class BSTNode<T extends Comparable<T>> {

    T key;                // 关键字(键值)
    BSTNode<T> left;    // 左孩子
    BSTNode<T> right;    // 右孩子
    BSTNode<T> parent;    // 父结点

    public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
      this.key = key;
      this.parent = parent;
      this.left = left;
      this.right = right;
    }

    public T getKey() {
      return key;
    }

    @Override
    public String toString() {
      return "key:" + key;
    }
  }

  public BSTree() {
    mRoot = null;
  }

  /*
   * 前序遍历"二叉树"
   */
  private void preOrder(BSTNode<T> tree) {
    if (tree != null) {
      System.out.print(tree.key + " ");
      preOrder(tree.left);
      preOrder(tree.right);
    }
  }

  public void preOrder() {
    preOrder(mRoot);
  }

  /*
   * 中序遍历"二叉树"
   */
  private void inOrder(BSTNode<T> tree) {
    if (tree != null) {
      inOrder(tree.left);
      System.out.print(tree.key + " ");
      inOrder(tree.right);
    }
  }

  public void inOrder() {
    inOrder(mRoot);
  }


  /*
   * 后序遍历"二叉树"
   */
  private void postOrder(BSTNode<T> tree) {
    if (tree != null) {
      postOrder(tree.left);
      postOrder(tree.right);
      System.out.print(tree.key + " ");
    }
  }

  public void postOrder() {
    postOrder(mRoot);
  }


  /*
   * (递归实现)查找"二叉树x"中键值为key的节点
   */
  private BSTNode<T> search(BSTNode<T> x, T key) {
    if (x == null) {
      return x;
    }

    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      return search(x.left, key);
    } else if (cmp > 0) {
      return search(x.right, key);
    } else {
      return x;
    }
  }

  public BSTNode<T> search(T key) {
    return search(mRoot, key);
  }

  /*
   * (非递归实现)查找"二叉树x"中键值为key的节点
   */
  private BSTNode<T> iterativeSearch(BSTNode<T> x, T key) {
    while (x != null) {
      int cmp = key.compareTo(x.key);

      if (cmp < 0) {
        x = x.left;
      } else if (cmp > 0) {
        x = x.right;
      } else {
        return x;
      }
    }

    return x;
  }

  public BSTNode<T> iterativeSearch(T key) {
    return iterativeSearch(mRoot, key);
  }

  /*
   * 查找最小结点：返回tree为根结点的二叉树的最小结点。
   */
  private BSTNode<T> minimum(BSTNode<T> tree) {
    if (tree == null) {
      return null;
    }

    while (tree.left != null) {
      tree = tree.left;
    }
    return tree;
  }

  public T minimum() {
    BSTNode<T> p = minimum(mRoot);
    if (p != null) {
      return p.key;
    }

    return null;
  }

  /*
   * 查找最大结点：返回tree为根结点的二叉树的最大结点。
   */
  private BSTNode<T> maximum(BSTNode<T> tree) {
    if (tree == null) {
      return null;
    }

    while (tree.right != null) {
      tree = tree.right;
    }
    return tree;
  }

  public T maximum() {
    BSTNode<T> p = maximum(mRoot);
    if (p != null) {
      return p.key;
    }

    return null;
  }

  /*
   * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
   */
  public BSTNode<T> successor(BSTNode<T> x) {
    // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
    if (x.right != null) {
      return minimum(x.right);
    }

    // 如果x没有右孩子。则x有以下两种可能：
    // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
    // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
    BSTNode<T> y = x.parent;
    while ((y != null) && (x == y.right)) {
      x = y;
      y = y.parent;
    }

    return y;
  }

  /*
   * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
   */
  public BSTNode<T> predecessor(BSTNode<T> x) {
    // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
    if (x.left != null) {
      return maximum(x.left);
    }

    // 如果x没有左孩子。则x有以下两种可能：
    // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
    // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
    BSTNode<T> y = x.parent;
    while ((y != null) && (x == y.left)) {
      x = y;
      y = y.parent;
    }

    return y;
  }

  /*
   * 将结点插入到二叉树中
   *
   * 参数说明：
   *     tree 二叉树的
   *     z 插入的结点
   */
  private void insert(BSTree<T> bst, BSTNode<T> z) {
    int cmp;
    BSTNode<T> y = null;
    BSTNode<T> x = bst.mRoot;

    // 查找z的插入位置
    while (x != null) {
      y = x;
      cmp = z.key.compareTo(x.key);
      if (cmp < 0) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    z.parent = y;
    if (y == null) {
      bst.mRoot = z;
    } else {
      cmp = z.key.compareTo(y.key);
      if (cmp < 0) {
        y.left = z;
      } else {
        y.right = z;
      }
    }
  }

  /*
   * 新建结点(key)，并将其插入到二叉树中
   *
   * 参数说明：
   *     tree 二叉树的根结点
   *     key 插入结点的键值
   */
  public void insert(T key) {
    BSTNode<T> z = new BSTNode<T>(key, null, null, null);

    // 如果新建结点失败，则返回。
    if (z != null) {
      insert(this, z);
    }
  }

  private BSTNode<T> delete(BSTNode<T> node) {
    //第 3 种情况，如果同时存在左右子节点
    if (node.left != null && node.right != null) {
      //获取后继结点
      BSTNode<T> successor = successor(node);
      //转移后继结点值到当前节点
      node.key = successor.key;
      //把要删除的当前节点设置为后继结点
      node = successor;
    }
    //经过前一步处理，下面只有前两种情况，只能是一个节点或者没有节点
    //不管是否有子节点，都获取子节点
    BSTNode<T> child;
    if (node.left != null) {
      child = node.left;
    } else {
      child = node.right;
    }
    //如果 child != null，就说明是有一个节点的情况
    if (child != null)
    //将子节点和父节点关联上
    {
      child.parent = node.parent;
    }
    //如果当前节点没有父节点（后继情况到这儿时一定有父节点）
    //说明要删除的就是根节点
    if (node.parent == null)
    //根节点设置为子节点
    //按照前面逻辑，根只有一个或者没有节点，所以直接赋值 child 即可
    {
      mRoot = child;
    } else if (node == node.parent.left)//存在父节点，并且当前节点是左节点时
    //将父节点的左节点设置为 child
    {
      node.parent.left = child;
    } else//右节点时
    //将父节点的右节点设置为 child
    {
      node.parent.right = child;
    }
    //返回被删除的节点
    return node;
  }

  //删除指定的值
  public void delete(T key) {
    //获取要删除的节点
    BSTNode<T> node = search(mRoot, key);
    //如果存在就删除
    if (node != null) {
      delete(node);
    }
  }

  /*
   * 删除结点(z)，并返回被删除的结点
   *
   * 参数说明：
   *     bst 二叉树
   *     z 删除的结点
   */
  private BSTNode<T> remove(BSTree<T> bst, BSTNode<T> z) {
    //这里没起个好名字，让人看着默默奇妙，实际上 x 就是子节点 child
    BSTNode<T> x = null;
    //这里的 y 节点就是要删除的节点 delete
    BSTNode<T> y = null;
    //这个写法理解比较绕，不如反转后容易理解
    //只有一个节点或者没有节点时
    if ((z.left == null) || (z.right == null))
    //z 就是要删除的节点
    {
      y = z;
    } else
    //当有两个子节点时，删除后继结点
    {
      y = successor(z);
    }
    //获取子节点，不管是左是右
    if (y.left != null) {
      x = y.left;
    } else {
      x = y.right;
    }
    //如果存在子节点，就关联被删节点的父节点
    if (x != null) {
      x.parent = y.parent;
    }
    //如果父节点是空，说明要删的是根节点
    if (y.parent == null)
    //设置根为 child（此时根只有一个或没有节点）
    {
      bst.mRoot = x;
    } else if (y == y.parent.left)//要删的是左节点
    {
      y.parent.left = x;//左节点关联子节点
    } else//要删的是右节点
    {
      y.parent.right = x;//右节点关联子节点
    }
    //如果要删的节点和一开始传入的不一样，就是后继的情况
    if (y != z) {
      z.key = y.key;//后继的值传给本来要删除的节点
    }
    //返回被删除的节点
    return y;
  }

  /*
   * 删除结点(z)，并返回被删除的结点
   *
   * 参数说明：
   *     tree 二叉树的根结点
   *     z 删除的结点
   */
  public void remove(T key) {
    BSTNode<T> z, node;

    if ((z = search(mRoot, key)) != null) {
      if ((node = remove(this, z)) != null) {
        node = null;
      }
    }
  }

  /*
   * 销毁二叉树
   */
  private void destroy(BSTNode<T> tree) {
    if (tree == null) {
      return;
    }

    if (tree.left != null) {
      destroy(tree.left);
    }
    if (tree.right != null) {
      destroy(tree.right);
    }

    tree = null;
  }

  public void clear() {
    destroy(mRoot);
    mRoot = null;
  }

  /*
   * 打印"二叉查找树"
   *
   * key        -- 节点的键值
   * direction  --  0，表示该节点是根节点;
   *               -1，表示该节点是它的父结点的左孩子;
   *                1，表示该节点是它的父结点的右孩子。
   */
  private void print(BSTNode<T> tree, T key, int direction) {

    if (tree != null) {

      if (direction == 0)    // tree是根节点
      {
        System.out.printf("%2d is root\n", tree.key);
      } else                // tree是分支节点
      {
        System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");
      }

      print(tree.left, tree.key, -1);
      print(tree.right, tree.key, 1);
    }
  }

  public void print() {
    if (mRoot != null) {
      print(mRoot, mRoot.key, 0);
    }
  }

  public static BSTree<Integer> newTree() {
    int[] array = new int[]{50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100};
    BSTree<Integer> tree = new BSTree<>();
    for (int i = 0; i < array.length; i++) {
      tree.insert(array[i]);
    }
    return tree;
  }

  public static void main(String[] args) {
    BSTree<Integer> tree = newTree();
    System.out.println("----------初始--------");
//    print(tree);
//    tree.delete(20);
//    System.out.println("----------删除 20--------");
//    print(tree);
//    tree = newTree();
//    tree.delete(70);
//    System.out.println("----------删除 70--------");
    print(tree);
//    tree = newTree();
//    tree.delete(50);
    System.out.println("----------插入 85--------");
    tree.insert(85);
//    System.out.println("----------删除 30--------");
    print(tree);
  }

  //------------下面代码是用于输出树的工具代码------------------------

  public static final String PREFIX_BRANCH = "├";//树枝
  public static final String PREFIX_TRUNK = "│ ";//树干
  public static final String PREFIX_LEAF = "└";//叶子
  public static final String PREFIX_EMP = "  ";//空
  public static final String PREFIX_LEFT = "─L─";//左
  public static final String PREFIX_RIGTH = "─R─";//右

  private static boolean hasChild(BSTree.BSTNode node) {
    return node.left != null || node.right != null;
  }

  public static void print(BSTree tree) {
    if (tree != null && tree.mRoot != null) {
      System.out.println(tree.mRoot.key);
      print(tree.mRoot, "");
    }
  }

  public static void print(BSTree.BSTNode node, String prefix) {
    if (prefix == null) {
      prefix = "";
    } else {
      prefix = prefix.replace(PREFIX_BRANCH, PREFIX_TRUNK);
      prefix = prefix.replace(PREFIX_LEAF, PREFIX_EMP);
    }
    if (hasChild(node)) {
      if (node.right != null) {
        System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGTH + node.right.key);
        if (hasChild(node.right)) {
          print(node.right, prefix + PREFIX_BRANCH);
        }
      } else {
        System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGTH);
      }

      if (node.left != null) {
        System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT + node.left.key);
        if (hasChild(node.left)) {
          print(node.left, prefix + PREFIX_LEAF);
        }
      } else {
        System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT);
      }
    }
  }
}
