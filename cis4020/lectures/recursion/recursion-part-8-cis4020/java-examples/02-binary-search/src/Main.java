public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
        int[] targets = {23, 4, 91};

        for (int target : targets) {
            int index = BinarySearch.binarySearch(arr, target, 0, arr.length - 1);
            if (index == -1) {
                System.out.println(target + " was not found.");
            } else {
                System.out.println(target + " was found at index " + index + ".");
            }
        }
    }
}
