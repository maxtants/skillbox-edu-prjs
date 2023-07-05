import java.io.FileOutputStream;

public class Thread1 extends Thread {
    @Override
    public void run() {
        try {
            FileOutputStream writer = new FileOutputStream("res/numbers1.txt");
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            int regionCode = 199;

            for (int number = 1; number < 500; number++) {
                StringBuilder sbuilder = new StringBuilder();
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            sbuilder.append(firstLetter);
                            sbuilder.append(Loader.padNumber(number, 3));
                            sbuilder.append(secondLetter);
                            sbuilder.append(thirdLetter);
                            sbuilder.append(Loader.padNumber(regionCode, 2));
                            sbuilder.append("\n");
                        }
                    }
                }
                writer.write(sbuilder.toString().getBytes());
            }

            writer.flush();
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
