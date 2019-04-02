public class Lab00 {
    public static void main(String[] args) {
        int x = 5;
        String y = "hello";
        double z = 9.8;
        System.out.println("x: " + x + " " + "y: " + y + " " + "z: " + z);

        int[] nums = new int[] { 3, 6, -1, 2 };
        for (int num = 0; num < nums.length; num++) {
            System.out.println(nums[num]);
        }

        int numFound = char_count(y, 'l');
        System.out.println("Found:" + numFound);

        for (int i = 1; i < 11; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static int char_count(String s, char c) {
        int count = 0;
        for (char ch : s.toCharArray())
        // for (int i = 0;i < s.length();i++)
        {
            if (ch == c) {
                count += 1;
            }
        }
        return count;
    }

}