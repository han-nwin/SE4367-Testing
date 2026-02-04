public class Triangle {

    public static String classify(int s1, int s2, int s3) {
        if (s1 <= 0 || s2 <= 0 || s3 <= 0) {
            return "Invalid";
        }

        if (s1 + s2 <= s3 || s2 + s3 <= s1 || s3 + s1 <= s2) {
            return "Invalid";
        }

        if (s1 == s2 && s2 == s3) {
            return "Equilateral";
        } else if (s1 == s2 || s2 == s3 || s1 == s3) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }
}
