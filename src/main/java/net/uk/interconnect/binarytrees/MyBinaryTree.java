package net.uk.interconnect.binarytrees;

public class MyBinaryTree {

    public static class Node<T> {
        private T data;
        private Node<T> leftChiled;
        private Node<T> rightChiled;

        public Node(T data) {
            this.data = data;
        }

        public Node<T> getLeftChiled() {
            return leftChiled;
        }

        public void setLeftChiled(Node<T> leftChiled) {
            this.leftChiled = leftChiled;
        }

        public Node<T> getRightChiled() {
            return rightChiled;
        }

        public void setRightChiled(Node<T> rightChiled) {
            this.rightChiled = rightChiled;
        }
    }
}

