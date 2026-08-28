public class Main {
    public static void main(String[] args) {
        int order = 2;
        int size = 1 << order;   // size = 2 ^ order

        for (int row = 0; row < size; row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < size; col++) {
                line.append(SierpinskiTriangle.isFilled(row, col, size) ? '*' : ' ');
            }
            System.out.println(line);
        }
    }
}
