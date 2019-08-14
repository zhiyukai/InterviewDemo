package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.08.14 09:31
 * @Comment
 */
public interface AbsBinaryTree {

  public abstract TreeNode find(int key);// 查找指定节点

  public abstract boolean update(int key, int value);

  public abstract void insert(int key, int value); // 插入节点

  public abstract boolean delete(int key); // 删除指定节点

  public abstract TreeNode getDirectPostNode(TreeNode delNode); // 得到待删除节点的直接后继节点

  public abstract void preOrder(TreeNode rootNode); // 先序遍历树

  public abstract void inOrder(TreeNode rootNode); // 中序遍历树

  public abstract void postOrder(TreeNode rootNode); // 后序遍历树
}
