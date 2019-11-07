package prictise.com.application1.suanFa.link;

/**
 * @Author zhisiyi
 * @Date 2019.09.27 10:34
 * @Comment You are given two non-empty linked lists representing two non-negative integers. The
 * digits are stored in reverse order and each of their nodes contain a single digit. Add the two
 * numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8 Explanation: 342 + 465 = 807.
 */
public class AddTwoNum {

  public static ListNode reverse(ListNode listNode) {
    ListNode pre = null;
    ListNode cur = listNode;
    ListNode n = listNode.next;

    while (cur != null) {
      cur.next = pre;
      pre = cur;
      cur = n;
      if (n != null) {
        n = n.next;
      }
    }

    return pre;
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    ListNode l1Pre = null;
    ListNode l1Cur = l1;
    ListNode l1Next = l1.next;

    ListNode l2Pre = null;
    ListNode l2Cur = l2;
    ListNode l2Next = l2.next;

    ListNode resultNode = new AddTwoNum().new ListNode(0);
    ListNode resultCur = resultNode;
    ListNode resultPre = null;

    int carryBit = 0;

    while (l1Cur != null && l2Cur != null) {
      int result = l1Cur.val + l2Cur.val + carryBit;
      int temp = 0;
      if (result >= 10) {
        temp = result % 10;
      } else {
        temp = result;
      }
      resultCur.val = temp;
      resultPre = resultCur;

      ListNode next = new AddTwoNum().new ListNode(0);
      resultCur.next = next;

      resultCur = next;

      l1Cur = l1Next;
      if (l1Next != null) {
        l1Next = l1Next.next;
      }

      l2Cur = l2Next;
      if (l2Next != null) {
        l2Next = l2Next.next;
      }

      if (result >= 10) {
        carryBit = 1;
      } else {
        carryBit = 0;
      }
    }
    if (l1Cur == null && l2Cur != null) {
      resultPre.next = l2Cur;

      while (l2Cur != null) {
        int r = l2Cur.val + carryBit;
        if (r >= 10) {
          l2Cur.val = r % 10;
          carryBit = 1;
        } else {
          carryBit = 0;
          l2Cur.val = r;
        }
        l2Pre = l2Cur;
        l2Cur = l2Cur.next;
      }
      if (carryBit == 1) {
        l2Pre.next = new AddTwoNum().new ListNode(carryBit);
      }
    } else if (l1Cur != null && l2Cur == null) {
      resultPre.next = l1Cur;

      while (l1Cur != null) {
        int r = l1Cur.val + carryBit;
        if (r >= 10) {
          l1Cur.val = r % 10;
          carryBit = 1;
        } else {
          carryBit = 0;
          l1Cur.val = r;
        }
        l1Pre = l1Cur;
        l1Cur = l1Cur.next;
      }

      if (carryBit == 1) {
        l1Pre.next = new AddTwoNum().new ListNode(carryBit);
      }

    } else {
      if (carryBit == 1) {
        resultPre.next = new AddTwoNum().new ListNode(1);
      } else {
        resultPre.next = null;
      }
    }

    return resultNode;
  }

  public static ListNode addTowNumbers2(ListNode l1, ListNode l2) {
    ListNode result = new AddTwoNum().new ListNode(0);
    ListNode ret = result;
    int carry = 0;
    while (true) {
      result.val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
      if (result.val >= 10) {
        carry = 1;
        result.val -= 10;
      } else {
        carry = 0;
      }
      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;
      if (l1 == null && l2 == null) {
        if (carry == 1) {
          result.next = new AddTwoNum().new ListNode(0);
          result = result.next;
          result.val = 1;
        }
        break;
      } else {
        result.next = new AddTwoNum().new ListNode(0);
        result = result.next;
      }
    }
    return ret;
  }

  public static ListNode addTowNumbers3(ListNode l1, ListNode l2) {
    ListNode result = new AddTwoNum().new ListNode(0);
    ListNode ret = result;

    int carryBit = 0;

    while (true) {

      int l1Val = l1 == null ? 0 : l1.val;
      int l2Val = l2 == null ? 0 : l2.val;
      int resVal = l1Val + l2Val + carryBit;
      if (resVal >= 10) {
        resVal -= 10;
        carryBit = 1;
      } else {
        carryBit = 0;
      }
      result.val = resVal;

      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;

      if (l1 == null && l2 == null) {
        if (carryBit == 1) {
          ListNode listNode = new AddTwoNum().new ListNode(0);
          result.next = listNode;
          result = result.next;
          result.val = carryBit;
        }
        break;
      } else {
        result.next = new AddTwoNum().new ListNode(0);
        result = result.next;
      }
    }

    return ret;
  }

  public static void main(String[] args) {
    ListNode list1node1 = new AddTwoNum().new ListNode(3);
    ListNode list1node2 = new AddTwoNum().new ListNode(4);
    ListNode list1node3 = new AddTwoNum().new ListNode(2);

    list1node1.next = list1node2;
    list1node2.next = list1node3;

    ListNode list2node1 = new AddTwoNum().new ListNode(4);
    ListNode list2node2 = new AddTwoNum().new ListNode(6);
    ListNode list2node3 = new AddTwoNum().new ListNode(5);

    list2node1.next = list2node2;
    list2node2.next = list2node3;

    ListNode list3node1 = new AddTwoNum().new ListNode(8);
    ListNode list3node2 = new AddTwoNum().new ListNode(4);
    list3node1.next = list3node2;

    ListNode list4node1 = new AddTwoNum().new ListNode(7);
    ListNode list4node2 = new AddTwoNum().new ListNode(2);
    ListNode list4node3 = new AddTwoNum().new ListNode(8);
    ListNode list4node4 = new AddTwoNum().new ListNode(9);
    list4node1.next = list4node2;
    list4node2.next = list4node3;
    list4node3.next = list4node4;

    ListNode list5node1 = new AddTwoNum().new ListNode(1);
    ListNode list6node1 = new AddTwoNum().new ListNode(9);
    ListNode list6node2 = new AddTwoNum().new ListNode(9);
    list6node1.next = list6node2;


    ListNode list7node1 = new AddTwoNum().new ListNode(9);
    ListNode list7node2 = new AddTwoNum().new ListNode(1);
    ListNode list8node1 = new AddTwoNum().new ListNode(9);
    ListNode list8node2 = new AddTwoNum().new ListNode(9);

    list7node1.next = list7node2;
    list8node1.next = list8node2;

    ListNode l1 = reverse(list7node1);
    ListNode l2 = reverse(list8node1);

    ListNode result = reverse(addTowNumbers2(l1, l2));

    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }
  }


  class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}
