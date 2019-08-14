package prictise.com.application1.suanFa;

/**
 * @Author zhisiyi
 * @Date 2019.08.14 09:31
 * @Comment
 */
public class TreeNode {

  int key;
  int value;
  TreeNode leftChild;
  TreeNode rightChild;

  public TreeNode(int key, int value) {
    this.key = key;
    this.value = value;
    this.leftChild = null;
    this.rightChild = null;
  }

  public TreeNode(int key, int value, TreeNode leftChild, TreeNode rightChild) {
    super();
    this.key = key;
    this.value = value;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  public TreeNode() {

  }

  @Override
  public String toString() {
    return "TreeNode [key=" + this.key + ", value=" + this.value + ", leftChild=" + this.leftChild + ", rightChild="
        + this.rightChild + "]";
  }

  public int getKey() {
    return this.key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public TreeNode getLeftChild() {
    return this.leftChild;
  }

  public void setLeftChild(TreeNode leftChild) {
    this.leftChild = leftChild;
  }

  public TreeNode getRightChild() {
    return this.rightChild;
  }

  public void setRightChild(TreeNode rightChild) {
    this.rightChild = rightChild;
  }
}
