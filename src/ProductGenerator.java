import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Product> products = new ArrayList<>();

        boolean done = false;
        String number = "";
        String product = "";
        String description = "";
        String record = "";
        double cost = 0;
        String fileName = "";
        Product prod;

        do {
            number = SafeInput.getNonZeroLenString(in, "Please enter the ID of the product [6 DIGITS]");
            product = SafeInput.getNonZeroLenString(in, "Please enter the name of the product");
            description = SafeInput.getNonZeroLenString(in, "Please enter a description of the product");
            cost = SafeInput.getDouble(in, "Please enter the cost of the product");

            prod = new Product(number,product, description, cost);
            products.add(prod);

            done = SafeInput.getYNConfirm(in, "Are there anymore records to add?");
        } while (done);

        fileName = SafeInput.getNonZeroLenString(in, "Please name your file");
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            String p;
            for (Product rec: products){
                p = rec.toCSVDataRecord();
                System.out.println(p);
                writer.write(p, 0, p.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data has been written");
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
