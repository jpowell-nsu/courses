public class Main {
    public static void main(String[] args) {
        int[] a = {1, 4, 7, 9};
        int[] b = {2, 3, 8, 10, 11};
        int[] result = new int[a.length + b.length];

        Merge.merge(a, b, result, 0, 0, 0);

        System.out.print("Merged list: ");
        for (int value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
