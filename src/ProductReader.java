import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;

        ArrayList<Product> products = new ArrayList<>();

        String number = "ID#";
        String ptName = "Product Name";
        String desc = "Description";
        String cash = "Cost";
        String divider = "================================================================================";

        String id;
        String product;
        String description;
        String record = "";
        double cost;
        Product prod;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                System.out.printf("%-8s%-25s%-35s%-6s", number, ptName, desc, cash);
                System.out.print("\n");
                System.out.println(divider);


                while(reader.ready())
                {
                    rec = reader.readLine();

                    String[] data = rec.split(", ");

                    products.add(new Product(data[0], data[1], data[2], Double.parseDouble(data[3])));


                    System.out.printf("%-8s" , data[0]);
                    System.out.printf("%-25s" , data[1]);
                    System.out.printf("%-35s" , data[2]);
                    System.out.printf("%-6s" , data[3]);
                    System.out.print("\n");

                }
                reader.close();
                System.out.println("\n\nData file read!");


            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found!!!");
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }


    }
}