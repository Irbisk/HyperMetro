package metro;

public class SinglyLinkedList<T> {
    private Node<T> head;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node() {
            this.data = data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    public void addToHead(T value) {
        if (this.head == null) {
            this.head = new Node<>();
            this.head.setData(value);
        } else {
            Node<T> newNode = new Node<>();
            newNode.setData(value);
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    public void addToTail(T value) {
        if (this.head == null) {
            this.head = new Node<>();
            this.head.setData(value);
        } else {
            Node<T> lastNode = this.head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            Node<T> newNode = new Node<>();
            newNode.setData(value);
            lastNode.setNext(newNode);
        }
    }

    public void printList() {
        if (this.head != null) {
            Node<T> currentNode = this.head;
            System.out.print("depot - ");
            while (currentNode != null) {
                System.out.print(currentNode.getData() + " - ");
                currentNode = currentNode.getNext();
            }
            System.out.println("depot");
        }
    }

    public void printAs1stStage() {
        if (this.head != null) {
            boolean completed = false;
            Node<T> headNewNode = this.head;
            Node<T> currentNode = this.head;
            while (!completed) {
                String result = "";
                for (int i = 0; i < 3; i++) {
                    if (currentNode == null) {
                        completed = true;
                        break;
                    }
                    result += currentNode.getData();
                    if (i < 2) {
                        result += " - ";
                    }
                    currentNode = currentNode.getNext();
                }
                headNewNode = headNewNode.getNext();
                currentNode = headNewNode;
                if (!completed) {
                    System.out.println(result);
                }
            }
        }
    }

}
