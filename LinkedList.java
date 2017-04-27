import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class LinkedList {
	/**
	 * @Author：ZhangRui
	 * @Purpose：For learning single LinkedList
	 * @Date：2017/04/23
	 * @Email：1138517609@qq.com
	 * @Blog：http://blog.csdn.net/u011489043
	 * @GitHub：https://github.com/selfcon
	 */
	public static class Node {
		int value;
		Node next;
		public Node(int n) {
			this.value = n;
			this.next = null;
		}
	}
//	输出函数
	public static void display(Node head) {
		while (head != null) {
			if (head.next == null) {
				System.out.println(head.value);
			} else {
				System.out.print(head.value + " -> ");
			}
			head = head.next;
		}
	}
//	利用栈的特性实现逆序输出
	public static void reverseByStack(Node head) {

		Stack<Node> stack = new Stack<Node>();
		while (head != null) {
			stack.add(head);
			head = head.next;
		}
		while (!stack.empty()) {
			System.out.print(stack.pop().value + " -> ");
		}
		System.out.println();
	}
//	利用动态数组实现逆序输出
	public static ArrayList<Integer> printListFromTailToHead(Node listNode) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while (listNode != null) {
			arr.add(0, listNode.value);
			listNode = listNode.next;
		}
		return arr;
	}

//  通过递归实现逆序输出
	public static void reverseByRecursive(Node head) {
		if (head == null)
			return;
		else
			reverseByRecursive(head.next);
		System.out.print(head.value + " -> ");
	}

//  利用递归和动态数组
	ArrayList<Integer> arrayList = new ArrayList<Integer>();
	public ArrayList<Integer> printListFromTailToHead1(Node listNode) {
		if (listNode != null) {
			this.printListFromTailToHead1(listNode.next);
			arrayList.add(listNode.value);
		}
		return arrayList;
	}
	
//	获取链表的长度
	public static int getListLength(Node head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}
//	链表反转
	public static Node reverseList(Node head) {
		if (head == null || head.next == null)
			return head;
		Node pre = null;
		Node nex = null;
		while (head != null) {
			nex = head.next;
			head.next = pre;
			pre = head;
			head = nex;
		}
		return pre;
	}
//	通过递归实现链表反转
	public static Node reverseListRecursive(Node head) {
		if (head == null || head.next == null)
			return head;
		Node nex = reverseListRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return nex;
	}
//	删除链表中某个特定节点
	public static Node deleteOneNode(Node head, Node delNode) {
		if (head == null || delNode == null)
			return head;
		if (delNode.next == null) {
//			尾节点
			Node temp = head;
			while (temp.next != null) {
				if (temp.next == delNode)
					temp.next = delNode.next;
				temp = temp.next;
			}
		} else {
//			非尾节点
			Node p = delNode.next;
			delNode.value = p.value;
			delNode.next = p.next;
		}
		return head;
	}
//	反转链表中第m到第n个节点
	public static Node reverseListBetween(Node head, int m, int n) {
		if (head == null || head.next == null)
			return head;
		Node helper = new Node(0);
		helper.next = head;
		Node nex = head;
		Node pre = helper;
		// 首先定位到第m个节点
		for (int i = 1; i < m; i++) {
			pre = nex;
			nex = nex.next;
		}
		// 然后将第m到第n个节点进行反转
		for (int i = 1; i <= n - m; i++) {
			Node temp = nex.next;
			nex.next = temp.next;
			temp.next = pre.next;
			pre.next = temp;
		}
		return helper.next;
	}
//	查找链表中倒数第k个节点 k>=0
	public static Node searchKthLastNode(Node head, int k) {
		if (head == null || k < 0)
			return head;
		if (k >= getListLength(head))
			return null;
		Node target = head;
		Node temp = head;
		for (int i = 0; i <= k; i++) {
			temp = temp.next;
		}
		// n-k
		while (temp != null) {
			target = target.next;
			temp = temp.next;
		}
		temp = null;
		target.next = null;
		System.out.print("target.value: " + target.value + "  ");
		return target;
	}
//	查找链表中中间节点
	public static Node searchMiddleNode(Node head) {
		if (head == null || head.next == null)
			return head;
		// 一般查找问题都是两个指向头节点的指针（快慢指针）共同移动。
		int length = getListLength(head);
		Node fast = head;
		Node slow = head;
		Node temp = slow;//如果有两个中间节点，取前一个
		while (fast != null && fast.next != null) {
			temp = slow;
			fast = fast.next.next;
			slow = slow.next;
		}
		temp.next = null;
		slow.next = null;
		//System.out.print("temp.value: " + temp.value + " slow.value: " + slow.value);
		if(length % 2 != 0)
			return slow;
		else
			return temp;
	}
//	删除倒数第n个节点
	public static Node removeNthLastNode(Node head, int n) {
		if (head == null || n < 0)
			return head;
		if (n > getListLength(head))
			return null;
		Node temp = head;
		Node target = head;
		while (n > 0) {
			temp = temp.next;// 定位倒数第n个节点
			n--;
		}
		// System.out.print("temp.value: " + temp.value + "  ");
		// 删除第一个节点
		if (temp == null)
			return head.next;
		while (temp.next != null) {
			target = target.next;// 定位倒数第n+1个节点
			temp = temp.next;
		}
		temp = null;
		// System.out.print("target.value: " + target.value + "  ");
		if (target.next != null)
			target.next = target.next.next;
		return head;
	}
//	判断链表是否存在环
	public static boolean isExistCycleList(Node head) {
		/*
		 * 时间复杂度为O(n)，空间复杂度为O(1) 当一个存在环的链表足够长，而环足够小，那么会存在快指针永远不会追上慢指针的情况。
		 * 快慢指针只适用于环出现在链表尾部的情况，也就是单链表环的问题，而无法解决链表存在循环的问题
		 */
		if (head == null || head.next == null)
			return false;
		Node fast = head;
		Node slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast)
				return true;
		}
		slow = null;
		fast = null;
		return false;
	}
//	利用set的无重复性，判断链表是否存在环
	public static boolean isExistCycleListBySet(Node head) {
		if (head == null || head.next == null)
			return false;
		HashSet<Node> set = new HashSet<Node>();
		while (head != null) {
			if (set.contains(head))
				return true;
			else {
				set.add(head);
				head = head.next;
			}
		}
		return false;
	}
//	利用hashmap获得链表环的开始位置
	public static Node getFirstNodeInCycleHashMap(Node head) {
		Node target = null;
		HashMap<Node, Boolean> map = new HashMap<Node, Boolean>();
		while (head != null) {
			if (map.containsKey(head)) {
				target = head;
				break;
			} else {
				map.put(head, true);
				head = head.next;
			}
		}
		return target;
	}
//	利用快慢指针获得链表环的开始位置
	public static Node getFirstNodeInCycleFSPointer(Node head) {
		/*
		 * 用快慢指针，与判断一个单链表中是否有环一样，找到快慢指针第一次相交的节点，
		 * 此时这个节点距离环开始节点的长度和链表投距离环开始的节点的长度相等
		 */
		Node fast = head;
		Node slow = head;
		// 找到快慢指针第一次相交的节点
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast)
				break;
		}
		if (fast == null || fast.next == null)
			return null;
		// slow从链表头出发,fast从第一次相遇的位置出发,再次相遇时即为环开始的地方。
		// 证明详见我博客http://blog.csdn.net/u011489043
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		fast = null;
		return slow;
	}
//	判断两个链表是否相交 思路一
	public static Node isIntersect(Node head1, Node head2) {
		/*
		 * 如果两个没有环的链表相交于某个节点，那么在这个节点之后的所有节点都是两个链表所共有的
		 * 1）遍历链表1，记录其长度len1，遍历链表2，记录其长度len2
		 * 2）按尾部对齐，如果两个链表的长度不相同，让长度更长的那个链表从头节点先遍历abs(len1-en2),这样两个链表指针指向对齐的位置
		 * 3）然后两个链表齐头并进，当它们相等时，就是交集的节点 时间复杂度O(n+m)，空间复杂度O(1)
		 */
		Node target = null;
		if (head1 == null || head2 == null)
			return target;
		int lenNode1 = getListLength(head1);
		int lenNode2 = getListLength(head2);
		if (lenNode1 >= lenNode2) {
			for (int i = 0; i < lenNode1 - lenNode2; i++)
				head1 = head1.next;
		} else {
			for (int i = 0; i < lenNode2 - lenNode1; i++)
				head2 = head2.next;
		}
		while (head1 != null && head2 != null) {
			if (head1 == head2) {
				target = head1;
				break;
			} else {
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		return target;
	}
	
//	判断两个链表是否相交 思路二
public static Node isIntersect2(Node head1, Node head2) {
	/*
	假定List1长度:a+n，List2 长度:b+n,且a<b(同理a>=b)
	那么p1会先到链表尾部,这时p2走到a+n位置,将p1换成List2头部
	接着p2再走b+n-(n+a)=b-a 步到链表尾部,这时p1也走到List2的b-a位置，还差a步就到可能的第一个公共节点。
	将p2换成List1头部，p2走a步也到可能的第一个公共节点。如果恰好p1==p2,那么p1就是第一个公共节点。
	或者p1和p2一起走n步到达列表尾部，二者没有公共节点，退出循环 
	时间复杂度O(n+a+b)
	*/
	Node p1 = head1;
	Node p2 = head2;
	while (p1 != p2) {
		p1 = (p1 == null) ? head2 : p1.next;
		p2 = (p2 == null) ? head1 : p2.next;
		// 上面两行代码是下面几句的精简版
//			if (p1 != null) p1 = p1.next;
//			if (p2 != null) p2 = p2.next;
//			if (p1 != p2) {
//				if (p1 == null) p1 = head2;
//				if (p2 == null) p2 = head1;
//			}
	}
	return p1;
}
	
//	利用递归合并两个有序链表
	public static Node mergeTwoListRecursive(Node head1, Node head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;
		Node head;
		if (head1.value < head2.value) {
			head = head1;
			head1.next = mergeTwoListRecursive(head1.next, head2);
		} else {
			head = head2;
			head2.next = mergeTwoListRecursive(head1, head2.next);
		}
		return head;
	}
//	合并两个有序链表
	public static Node mergeTwoList(Node head1, Node head2) {
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;
		Node head = new Node(0);
		Node temp = head;
		while (head1 != null && head2 != null) {
			if (head1.value < head2.value) {
				temp.next = head1;
				head1 = head1.next;
			} else {
				temp.next = head2;
				head2 = head2.next;
			}
			temp = temp.next;
		}
		if (head1 != null)
			temp.next = head1;
		if (head2 != null)
			temp.next = head2;
		return head.next;
	}
//	合并k个有序链表
	public Node mergeKLists(ArrayList<Node> lists) {
		if (lists == null || lists.size() == 0)
			return null;

		Node list_head = lists.get(0);
		for (int i = 1; i < lists.size(); i++) {
			list_head = mergeTwoList(list_head, lists.get(i));
		}
		return list_head;
	}
//	链表排序之 归并排序
	public static Node mergeSortList(Node head) {
		if (head == null || head.next == null)
			return head;
		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Node right = mergeSortList(slow.next);
		slow.next = null;
		Node left = mergeSortList(head);
		// /待左右两边各自有序，进行归并即可
		Node temp_head = new Node(0);
		Node temp_node = temp_head;
		while (left != null && right != null) {
			if (left.value < right.value) {
				temp_node.next = left;
				left = left.next;
			} else {
				temp_node.next = right;
				right = right.next;
			}
			temp_node = temp_node.next;
		}
		if (left != null)
			temp_node.next = left;
		if (right != null)
			temp_node.next = right;
		return temp_head.next;
	}
//	链表排序之 插入排序
	public static Node insertSortList(Node head) {
		if (head == null)
			return null;
		Node helper = new Node(0);// 辅助指针，防止头指针发生变化
		Node pre = helper;
		Node cur = head;
		while (cur != null) {
			Node next = cur.next;
			pre = helper;
			while (pre.next != null && pre.next.value <= cur.value) {
				pre = pre.next;
			}
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		return helper.next;
	}
//	删除链表里的重复元素，只保留一个
	public static Node removeDuplicateList(Node head) {
		if (head == null || head.next == null)
			return head;
		Node pre = head;
		while (head != null && head.next != null) {
			if (head.value == head.next.value) {
				head.next = head.next.next;
			} else {
				head = head.next;
			}
		}
		return pre;
	}
//	删除链表里的重复元素，一个不留（思路一）
	public static Node removeDuplicateListAll(Node head) {
		if (head == null || head.next == null)
			return head;
		Node pre = new Node(0);
		pre.next = head;
		Node fir = pre;// fir保存的是当前节点的前一个节点
		Node sec = head;
		while (sec != null) {
			int value = sec.value;
			int count_repeat = 0;
			while (sec != null && value == sec.value) {
				sec = sec.next;
				count_repeat++;// 统计每个节点重复次数
			}
			if (count_repeat > 1) {// 若出现次数大于1，说明该节点对应的值需要删掉
				fir.next = sec;
			} else {
				fir = fir.next;
			}
			if (fir != null) {
				sec = fir.next;
			}
		}
		return pre.next;
	}
	
//	删除链表里的重复元素，一个不留（思路二：递归）
	public static Node deleteDuplication(Node pHead) {
		if (pHead == null)
			return null;
		if (pHead != null && pHead.next == null)
			return pHead;
		Node current;
		if (pHead.next.value == pHead.value) {
			current = pHead.next.next;
			while (current != null && current.value == pHead.value)
				current = current.next;
			return deleteDuplication(current);
		} else {
			current = pHead.next;
			pHead.next = deleteDuplication(current);
			return pHead;
		}
	}
	
//	前后两两交换元素的值（递归）
	public static Node swapPairs(Node head) {
		if (head == null || head.next == null)
			return head;
		Node temp = head.next;
		head.next = swapPairs(temp.next);
		temp.next = head;
		return temp;
	}

//	前后两两交换元素的值
	public static Node swapPairsXH(Node head) {
		if (head == null || head.next == null)
			return head;
		Node helper = new Node(0);
		helper.next = head;
		Node pre = helper;
		Node cur = head;
		Node temp;
		while (cur != null && cur.next != null) {
			temp = cur.next;
			cur.next = temp.next;
			temp.next = pre.next;
			pre.next = temp;
			pre = cur;
			cur = cur.next;
		}
		return helper.next;
	}

	public static Node partition(Node head, int x) {
		if (head == null)
			return head;
		Node first = new Node(0);
		Node second = new Node(0);
		Node temp_fir = first;
		Node temp_sec = second;
		while (head != null) {
			if (head.value < x) {
				first.next = head;
				first = first.next;
			} else {
				second.next = head;
				second = second.next;
			}
			head = head.next;
		}
		second.next = null;// /如果链表最后一个元素小于x，则second.next不为null
		first.next = temp_sec.next;
		return temp_fir.next;
	}

	public static Node rotateRight(Node head, int n) {
		if (head == null || head.next == null)
			return head;
		Node target = head;
		int len = 1;
		while (target.next != null) {
			len++;
			target = target.next;
		}
		// System.out.println("len：" + len + " target: " + target);
		target.next = head;// 首尾相连构成环
		for (int i = 1; i < len - n % len; i++) {
			head = head.next;// 走到需要断开位置的前一位置
		}
		target = head.next;
		head.next = null;
		return target;
	}

	public static Node reorderList(Node head) {
		if (head == null || head.next == null)
			return head;
		// 首先，找到中间点，分为前后两部分
		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// 其次，将后部分的链表进行逆置
		Node second = slow.next;
		Node pre_rever = null;
		Node nex_rever = null;
		while (second != null) {
			nex_rever = second.next;
			second.next = pre_rever;
			pre_rever = second;
			second = nex_rever;
		}
		// 最后对前后两部分head和second进行交叉连接
		Node temp_second = pre_rever;
		slow.next = null;
		Node temp_head = head;
		while (temp_second != null) {
			Node pre = temp_second.next;
			// System.out.println("temp.value: " + temp.value);
			temp_second.next = temp_head.next;
			temp_head.next = temp_second;
			temp_second = pre;
			temp_head = temp_head.next.next;
		}
		return head;
	}
	
//	判断是否为回文链表  利用栈的特性
	public static Boolean isPalindrome(Node head) {
		if(head == null)
			return false;
		 Stack<Integer> sta = new Stack<Integer>();
	        Node temp = head;
	        while(temp != null){
	            sta.push(temp.value);
	            temp = temp.next;
	        }
	        while(!sta.isEmpty()){
	            if(sta.pop() != head.value)
	                return false;
	            head = head.next;
	        }
	        return true;
	}
	
//	判断是否为回文链表  利用栈和快慢指针的特性，减少空间开销
	public static Boolean isPalindrome2(Node head){
		if(head == null)
            return false;
        Node slow = head;
        Node fast = head;
        Stack<Integer> sta = new Stack<Integer>();
        while(fast !=null && fast.next != null){
            sta.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null)//如果有奇数个节点，slow再往后移动一个
            slow = slow.next;
       
        while(!sta.isEmpty()){
            if(sta.pop() != slow.value)
                return false;
            slow = slow.next;
        }
        return true;
	}
	
//	判断是否为回文链表  先反转，再和之前的依次比较
//	public static Boolean isPalindrome3(Node head){
//		if(head == null)
//			return false;
//		Node temp = head;
//		Node reverseHead = reverseList(temp);
//		while(head != null){ ///head 为什么只剩一个节点了？？
//			System.out.print(head.value + " -> ");
//			head = head.next;
//		}
//		System.out.println();
//		while(reverseHead != null){
//			System.out.print(reverseHead.value + " -> ");
//			reverseHead = reverseHead.next;
//		}
//        return true;
//	}
	
//	判断是否为回文链表  递归的思想
	static Node temp_pp = null;
	public static Boolean isPalindrome4(Node head){
		if(head == null)
            return true;      
        if(temp_pp == null)
        	temp_pp = head;
        if(isPalindrome4(head.next) && (temp_pp.value == head.value)){
        	temp_pp = temp_pp.next;
            return true;
        }
        temp_pp = null;
        return false;
	}
	
	public static void main(String[] args) {
		/*
		 * 主函数： （注）上面有很多函数体并未在下面调用大家可按需查找，调用测试。 
		 * 如果有疑问或者建议，欢迎交流。
		 */
		Scanner scan = new Scanner(System.in);
		Node head = null;// 测试链表一
		// Node head_second = null;//测试链表二
		int i = 0;
		// int j= 0;
		System.out.println("请依次输入链表1的元素：");
		// 输入头节点
		if (scan.hasNextInt()) {
			head = new Node(scan.nextInt());
		}
		// 输入其他链表元素
		Node temp = head;
		while (scan.hasNext()) {
			temp.next = new Node(scan.nextInt());
			temp = temp.next;
			i++;
			if (i == 4)// 决定链表元素的个数
				break;
		}
		// System.out.println("请依次输入链表2的元素：");
		// 输入头节点
		// if(scan.hasNextInt()){
		// head_second = new Node(scan.nextInt());
		// }
		// 输入其他链表元素
		// Node temp_second = head_second;
		// while(scan.hasNext()){
		// temp_second.next = new Node(scan.nextInt());
		// temp_second = temp_second.next;
		// j++;
		// if(j == 4)//决定链表元素的个数
		// break;
		// }

		 System.out.print("输入两个整数： ");
		//int m = scan.nextInt();
		// int n = scan.nextInt();
		scan.close();

		// case 1
		System.out.print("链表元素： ");
		display(head);

		// case 2
		// System.out.print("前后交叉排列后： ");
		// Node reorderNode = reorderList(head);
		// display(reorderNode);

		// case 3
		// System.out.println("链表长度： " + getListLength(head) + " 是否为有环链表：" +
		// cycleList(head));

		// case 4
		// System.out.print("两两交换位置后： ");
		// Node swapNode = swapPairs(head);
		// Node swapNodeXH = swapPairsXH(head);
		// display(swapNodeXH);

		// case 5
		// System.out.print("根据与x的大小重新分组后： ");
		// Node partition = partition(head, m);
		// display(partition);

		// case 6
		// System.out.print("删除全部重复元素后： ");
		// Node removeDuplicateAll = removeDuplicateListAll(head);
		Node removeDuplicateAllDG = deleteDuplication(head);
		display(removeDuplicateAllDG);

		// case 7
		// System.out.print("删除重复元素后： ");
		// Node removeDuplicate = removeDuplicateList(head);
		// display(removeDuplicate);

		// case 8
		// System.out.print("翻转第m到第n个值： ");
		// Node reverseBetween = reverseListBetween(head, m, n);
		// display(reverseBetween);

		// case 9
		// System.out.print("循环合并： ");
		// Node mergeSorted = mergeTwoList(head, head_second);
		// display(mergeSorted);

		// case 10
		// System.out.print("递归合并： ");
		// Node DGmergeSorted = mergeTwoListRecursive(head, head_second);
		// display(DGmergeSorted);

		// case 11
		// Node target = rotateRight(sortHead, x);
		// System.out.print(leetcode将链表倒数k位反转： ");
		// display(target);

		// case 12
		// Node sortHead = insertSortList(head);
		// System.out.print("将链表插入排序后输出： ");
		// display(sortHead);

		// case 13
		// System.out.print("归并排序后输出： ");
		// Node mergeSortNode = mergeSortList(head);
		// display(mergeSortNode);

		// case 14
		// System.out.print("通过栈的先入后出特性实现链表逆序输出： ");
		// reverseByStack(head);

		// case 15
		// System.out.print("通过递归实现链表逆序输出： ");
		// reverseByRecursive(head);
		// System.out.println();

		// case 16
		// System.out.print("通过调整指针实现链表反转：： ");
		// Node reHead = reverseList(head);
		// display(reHead);

		// case 17
		// System.out.print("通过递归实现链表反转：： ");
		// Node reHead = reverseListRecursive(head);
		// display(reHead);

		// case 18
//		 System.out.print("输出中间节点后的链表元素： ");
//		 Node midHead = searchKthLastNode(head, m);
//		 display(midHead);
		
		// case 19
//		 System.out.print("通过动态数组实现链表逆序输出： ");
//		 printListFromTailToHead(head);
//		 System.out.println();
		
		// case 20
//		 System.out.println("栈：是回文链表吗？：  " + isPalindrome(head));
//		 System.out.println("栈和快慢指针：是回文链表吗？：  " + isPalindrome2(head));
//		 //System.out.println("是回文链表吗？：  " + isPalindrome3(head));
//		 System.out.println("递归思路：是回文链表吗？：  " + isPalindrome4(head));
	}
}
