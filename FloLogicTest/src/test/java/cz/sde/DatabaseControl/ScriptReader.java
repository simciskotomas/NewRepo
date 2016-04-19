package cz.sde.DatabaseControl;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ScriptReader {

    public static String ReadScript(String filePath) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        try {
            StringBuilder sb = new StringBuilder();
            line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String loadedString = sb.toString();

//            System.out.println(loadedString);

            return loadedString;

        }
        finally {
            if (br != null) {
                br.close();
            }
        }
    }
}