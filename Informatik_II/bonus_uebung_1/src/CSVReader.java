import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Diese Klasse liest eine *.csv Datei ein.
 * Wie das intern genau funktioniert ist fuer die Uebung nicht relevant
 */
public class CSVReader {

    /**
     * Diese Methode liest eine *.csv Datei ein und
     * speichert die einzelnen Eintraege als Elemente
     * eines zweidimensionalen Arrays.
     *
     * @param filename Pfad zur Datei, z.b. ".Root/test/echo.csv"
     * @return {@code String[][] s}  Wobei {@code s[i][j]} Der j-te Wert in der i-ten Zeile ist
     */
    public String[][] readCSV(String filename) {
        ArrayList<String[]> result = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                result.add(line.split(cvsSplitBy));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toArray(new String[result.size()][]);
    }
}