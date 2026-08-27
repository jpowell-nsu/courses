public class Merge {
    // Merges sorted array a and sorted array b into result, starting at
    // indices i, j, and k respectively. Call with i = 0, j = 0, k = 0 to
    // merge the two arrays from the start. result must have space for at
    // least a.length + b.length elements.
    public static void merge(int[] a, int[] b, int[] result, int i, int j, int k) {
        if (i == a.length && j == b.length) {   // base case: both used up
            return;
        } else if (i == a.length) {              // base case: only b left
            result[k] = b[j];
            merge(a, b, result, i, j + 1, k + 1);
        } else if (j == b.length) {              // base case: only a left
            result[k] = a[i];
            merge(a, b, result, i + 1, j, k + 1);
        } else if (a[i] <= b[j]) {
            result[k] = a[i];
            merge(a, b, result, i + 1, j, k + 1);
        } else {
            result[k] = b[j];
            merge(a, b, result, i, j + 1, k + 1);
        }
    }
}
