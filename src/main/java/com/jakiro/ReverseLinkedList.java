package com.jakiro;

/**
 * @description:类/接口 翻转链表
 * 反转一个单链表。

  示例:

  输入: 1->2->3->4->5->NULL

  输出: 5->4->3->2->1->NULL

 * @author: jakiro
 * @date: 2019/5/30
 * @time: 21:57
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head){

        ListNode prev=null;
        ListNode curr=head;

        while (curr!=null){
            ListNode nextTemp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nextTemp;
        }

        return null;
    }

    class ListNode{

        private Object data;
        private ListNode next;

        public ListNode(Object data,ListNode next){
            this.data=data;
            this.next=next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

}
