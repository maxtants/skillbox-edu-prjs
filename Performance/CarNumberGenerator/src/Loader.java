import java.io.FileOutputStream;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        new Thread1().start();
        new Thread2().start();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    public static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < padSize; i++) {
            stringBuilder.append('0');
            //numberStr = '0' + numberStr;
        }
        stringBuilder.append(numberStr);
        return stringBuilder.toString();
    }
}
