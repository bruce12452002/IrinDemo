package teach0419;

public class Test {
    public static void main(String[] args) {
        int[] data = {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println(binarySearch(data, 0, data.length, 0));

//        System.out.println(Arrays.binarySearch(data, 20)); // 只能正序
    }

    private static int binarySearch(int[] data, int begin, int end, int item) {
        System.out.println("begin=" + begin + ", end=" + end);
        if (end >= begin) {
            int middle = (begin + end) / 2;
            if (data[middle] == item) {
                return middle;
            } else if (data[middle] > item) {
                System.out.println(data[middle] + " > " + item);
                return binarySearch(data, middle + 1, end, item);
            } else {
                System.out.println(data[middle] + " < " + item);
                return binarySearch(data, begin, middle - 1, item);
            }
        }
        return -1;
    }
}
