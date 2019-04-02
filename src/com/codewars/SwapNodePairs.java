package com.codewars;

/**
 * https://www.codewars.com/kata/swap-node-pairs-in-linked-list/
 */
public class SwapNodePairs {
    public static Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node current = head;
        head = head.next;
        Node nextNode, prevNode = null;
        while (current != null && current.next != null) {
            nextNode = current.next;

            current.next = nextNode.next;
            nextNode.next = current;
            if (prevNode != null) {
                prevNode.next = nextNode;
            }
            prevNode = current;
            current = current.next;
        }
        return head;
    }

    public static Node swapPairsRecur(Node head) {
        if (head == null || head.next == null)
            return head;
        Node next = head.next;
        head.next = swapPairsRecur(next.next);
        next.next = head;
        return next;
    }

    public class Node {
        private String value;
        public Node next;

        public Node(String value) { this.value = value; }

        public String getValue() { return value; }

        public String toString() { return this.value; }

        public String printList() {
            if (this.next == null) return this.toString() + " ->";
            return this.toString() + " -> " + next.printList();
        }
    }
}

