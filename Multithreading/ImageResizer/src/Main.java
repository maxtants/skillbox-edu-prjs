import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "/users/sortedmap/Desktop/src";
        String dstFolder = "/users/sortedmap/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int middle = files.length / 2;

        File[] files1 = new File[middle];
        System.arraycopy(files, 0, files1, files1.length);
        ImageResizer ir1 = new ImageResizer(files1, 300, dstFolder);
        ir1.start();

        File[] files2 = new File[files.length - middle];
        System.arraycopy(files, middle, files2, files2.length - middle);
        ImageResizer ir2 = new ImageResizer(files2, 300, dstFolder);
        ir2.start();

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
