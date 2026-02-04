Program:

```java

import java.util.Scanner;

public class Triangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lengths of the three sides of the triangle");
        int s1 = sc.nextInt();
        int s2 = sc.nextInt();
        int s3 = sc.nextInt();
        sc.close();

        if (s1 <= 0 || s2 <= 0 || s3 <= 0) {
            System.out.println("Invalid");
            return;
        }

        if (s1 + s2 <= s3 || s2 + s3 <= s1 || s3 + s1 <= s2) {
            System.out.println("Invalid");
            return;
        }

        if (s1 == s2 && s2 == s3) {
            System.out.println("Equilateral");
        } else if (s1 == s2 || s2 == s3 || s1 == s3) {
            System.out.println("Isosceles");
        } else {
            System.out.println("Scalene");
        }
    }
}
```

```



Test cases:
 1. All sides positive and equal (e.g., 5, 5, 5) - should print "Equilateral"
 2. Two sides equal and one different (e.g., 5, 5, 3) - should print "Isosceles"
 3. All sides different and valid triangle (e.g., 3, 4, 5) - should print "Scalene"
 4. One or more sides zero or negative (e.g., 0, 4, 5 or -1, 4, 5) - should print "Invalid"
 5. Sum of two sides equal to the third side (e.g., 2, 3, 5) - should print "Invalid"
 6. Sum of two sides less than the third side (e.g., 1, 2, 4) - should print "Invalid"
```
