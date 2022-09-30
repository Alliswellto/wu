package com.yanwu.algorithm.datastruct.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ArrayListQueue
 * @Author yanwu
 * @Date 2022/9/21 16:05
 * @Version 1.0
 */
public class ArrayListQueue {
    int head;
    int tail;
    int count;
    String[] queue;

    public ArrayListQueue(int capacity) {
        this.head = 0;
        this.tail = 0;
        this.count = capacity;
        this.queue = new String[capacity];
    }

    public boolean enqueue(String item) {
        if (tail == count) {
            if (head == 0) return false;
            for (int i = head; i < tail; i ++) {
                queue[i - head] = queue[i];
            }
            tail -= head;
            head = 0;
        }
        queue[tail] = item;
        ++ tail;
        return true;
    }

    /**
     * 循环队列
     * @param item
     * @return
     */
    public boolean enqueueV2(String item) {
        if (tail + 1 == head) return false;
        queue[tail] = item;
        tail = tail + 1 % count;
        return true;
    }

    public String dequeue() {
        if (head == tail) return null;
        String res = queue[head];
        ++ head;
        return res;
    }

    public boolean hasNext() {
        return head != tail;
    }
}

class Entry_2 {
    public static void main(String[] args) {
        ArrayListQueue queue = new ArrayListQueue(5);
        queue.enqueueV2("1");
        queue.enqueueV2("2");
        queue.enqueueV2("3");
        queue.enqueueV2("4");
        queue.enqueueV2("5");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        while (queue.hasNext()) {
            System.out.println(queue.dequeue());
        }
    }
}
