package helpers;

public class StringHelper {
    public static String extraSpaceVertical(int number) {
        if (number < 10) {
            return " ";
        } else {
            return "";
        }
    }
    public static String extraSpaceHorizontal(int count, int number) {
        if (count <= 10) {
            return "";
        } else {
            return extraSpaceVertical(number);
        }
    }
    public static String extraSpace(int number) {
        if (number > 10) {
            return " ";
        } else {
            return "";
        }
    }
}
