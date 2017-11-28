package net.uk.interconnect.sortingsearching;

public class BinarySearch {

    public static int[] sortedArray = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};

    public static void main(String[] args) {
        System.out.println(String.format("%s is present at index: %s",
                30, binarySearch(sortedArray, 30, 0, sortedArray.length - 1)));
        System.out.println(String.format("%s is present at index: %s",
                4, binarySearch(sortedArray, 4, 0, sortedArray.length - 1)));
        System.out.println(String.format("%s is present at index: %s",
                15, binarySearch(sortedArray, 15, 0, sortedArray.length - 1)));

        System.out.println("-------");

        System.out.println(String.format("%s is present at index: %s",
                30, binarySearchWhileLoop(sortedArray, 30)));
        System.out.println(String.format("%s is present at index: %s",
                4, binarySearchWhileLoop(sortedArray, 4)));
        System.out.println(String.format("%s is present at index: %s",
                15, binarySearchWhileLoop(sortedArray, 15)));
    }

    public static int binarySearch(int[] sortedArray, int number, int min, int max) {
        if (min > max) {
            return -1;
        }

        int mid = min + (max - min) / 2;
        if (sortedArray[mid] == number) {
            return mid;
        }

        if (sortedArray[mid] > number) {
            return binarySearch(sortedArray, number, min, mid - 1);
        } else {
            return binarySearch(sortedArray, number, mid + 1, max);
        }
    }

    public static int binarySearchWhileLoop(int[] sortedArray, int number) {
        int min = 0;
        int max = sortedArray.length - 1;

        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (sortedArray[mid] == number) {
                return mid;
            }

            if (sortedArray[mid] > number) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return  -1;
    }
}
