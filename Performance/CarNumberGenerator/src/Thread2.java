import java.io.FileOutputStream;

public class Thread2 extends Thread {
    @Override
    public void run() {
        try {
            FileOutputStream writer = new FileOutputStream("res/numbers2.txt");
            char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            int regionCode = 199;

            for (int number = 501; number < 1000; number++) {
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
