package com.yanwu.algorithm.datastruct.queue;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: LinkedListQueue
 * @Author yanwu
 * @Date 2022/9/21 15:22
 * @Version 1.0
 */
public class LinkedListQueue {
    LinkedList queue = new LinkedList("0");
    LinkedList head = queue;
    LinkedList tail = queue;

    public boolean enqueue(String item) {
        tail.next = new LinkedList(item);
        tail = tail.next;
        return true;
    }

    public String dequeue() {
        if (head == null) return null;
        String res = head.value;
        head = head.next;
        return res;
    }
}

@Data
@Accessors(chain = true)
class LinkedList {
    LinkedList next;
    String value;

    public LinkedList(String value) {
        this.value = value;
    }
}

class Entry {
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        for (int i = 0; i < 6; i ++) {
            System.out.println(queue.dequeue());
        }
    }
}