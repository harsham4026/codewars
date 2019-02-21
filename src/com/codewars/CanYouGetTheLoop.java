package com.codewars;

/**
 * https://www.codewars.com/kata/can-you-get-the-loop
 */
public class CanYouGetTheLoop {
    public int loopSize(Node node) {
        Node slow = node;
        Node fast = node.getNext();

        while (slow != fast) { //guaranteed to have a loop
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        Node start = slow;
        Node tmp = slow;
        tmp = tmp.getNext();
        int length = 1;
        while (tmp != start) {
            tmp = tmp.getNext();
            length++;
        }
        return length;
    }

    //To make things compile
    private class Node {
        public Node getNext() {
            return null;
        }
    }
}