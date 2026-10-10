package practice_assignment;

public class ProductPriceFinder {

    // Lower bound binary search: Finds first index where price >= lower
    public static int findLowerBound(int[] prices, int lower) {
        int low = 0, high = prices.length - 1, ans = prices.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] >= lower) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Upper bound binary search: Finds last index where price <= upper
    public static int findUpperBound(int[] prices, int upper) {
        int low = 0, high = prices.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] <= upper) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int lower = 150, upper = 300;

        int lowerIdx = findLowerBound(prices, lower);
        int upperIdx = findUpperBound(prices, upper);

        int count = (lowerIdx <= upperIdx) ? (upperIdx - lowerIdx + 1) : 0;

        System.out.println("Lower bound index " + lowerIdx);
        System.out.println("Upper bound index " + upperIdx);
        System.out.println("Count " + count);
    }
}
