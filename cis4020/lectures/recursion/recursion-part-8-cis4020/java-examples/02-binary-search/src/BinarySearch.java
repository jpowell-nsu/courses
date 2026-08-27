public class BinarySearch {
    // Searches a sorted array for target between indices low and high,
    // inclusive. Returns the index of target if found, or -1 otherwise.
    public static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) {                   // base case: not found
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {           // base case: found
            return mid;
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high);
        } else {
            return binarySearch(arr, target, low, mid - 1);
        }
    }
}
