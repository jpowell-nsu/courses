public class Main {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;

        System.out.println(CoinChange.countChange(amount, coins, 0));
    }
}
