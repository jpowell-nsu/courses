public class FinallyReturn {
    @SuppressWarnings("finally")
    static int parse(String s) {
        try {
            return Integer.parseInt(s);
        } finally {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(parse("42"));
        System.out.println(parse("abc"));
    }
}
