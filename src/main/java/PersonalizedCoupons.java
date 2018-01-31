import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.*;
/**
 * HackerRank Test for Illumination Works / Kroger
 *
 * @author Keith Wedinger <br>
 * Created On: 1/29/18
 */

class PersonalizedCoupons {
    // Complete this function
    static List<Map<String, Object>> personalizeCoupons(List<Map<String, Object>> coupons,
                                                        List<String> preferredCategories) {

        List<Map<String, Object>> filteredCoupons = coupons.stream()
                // Filter out coupons not in the category list
                .filter(coupon -> preferredCategories.contains(coupon.get("category").toString()))
                // Sort by % off, highest to lowest
                .sorted((coupon1, coupon2) -> {
                    float coupon1PercentageOff = (float) coupon1.get("couponAmount") / (float) coupon1.get("itemPrice");
                    float coupon2PercentageOff = (float) coupon2.get("couponAmount") / (float) coupon2.get("itemPrice");
                    // Reverse result of compare to get highest to lowest
                    return Float.compare(coupon1PercentageOff, coupon2PercentageOff) * -1;
                })
                // Remove code from each coupon
                .peek(coupon -> coupon.remove("code"))
                // Collect into a list
                .collect(Collectors.toList());

        // Prevent going beyond end of list when returning top 10
        int max = Math.min(10, filteredCoupons.size());
        return filteredCoupons.subList(0, max);
    }

    // DO NOT EDIT CODE BELOW
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        // Get the category list
        List<String> preferredCategories = Arrays.asList(input.nextLine().split(","));
        List<Map<String, Object>> coupons = new ArrayList<>();
        // Get the number of coupons
        int lines = Integer.parseInt(input.nextLine());
        // Get the coupons
        IntStream.range(0, lines).forEach(i -> coupons.add(readCoupon(input)));
        List<Map<String, Object>> personalizedCoupons = personalizeCoupons(coupons, preferredCategories);
        personalizedCoupons.stream().forEach(PersonalizedCoupons::printCoupon);
    }

    public static Map<String, Object> readCoupon(Scanner input) {
        String[] couponItems = input.nextLine().split(",");
        Map<String,Object> coupon = new HashMap<>();
        coupon.put("upc", couponItems[0]);
        coupon.put("code", couponItems[1]);
        coupon.put("category", couponItems[2]);
        coupon.put("itemPrice", Float.parseFloat(couponItems[3]));
        coupon.put("couponAmount", Float.parseFloat(couponItems[4]));
        return coupon;
    }

    public static void printCoupon(Map<String, Object> coupon)
    {
        System.out.print("{");
        System.out.print("\"couponAmount\":" +  coupon.get("couponAmount") + ",");
        System.out.print("\"upc\":\"" +  coupon.get("upc") + "\",");
        if(coupon.containsKey("code")) {
            System.out.print("\"code\":\"" +  coupon.get("code") + "\",");
        }
        System.out.print("\"itemPrice\":" +  coupon.get("itemPrice") + ",");
        System.out.println("\"category\":\"" +  coupon.get("category") + "\"}");
    }
}
