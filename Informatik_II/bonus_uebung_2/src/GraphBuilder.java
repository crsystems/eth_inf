import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphBuilder {

    /**
     * Loads the data from the CSV file.
     *
     * @param filename the path of the file. (absolute or relative)
     * @return the data as a list of arrays of tokens
     */
    public static ArrayList<String[]> readCSV(String filename) {
        ArrayList<String[]> result = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                result.add(line.split(cvsSplitBy));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        return result;
    }

    /**
     * Decodes a route Graph from the input data in the csv file.
     *
     * @param input the encoded Graph
     * @return the decoded route Graph
     */
    public static Graph buildGraph(ArrayList<String[]> input) {
        Graph graph = new Graph();
        String[][] matrix = input.toArray(new String[1][1]);
        for (int i = 1; i < matrix.length; i++) {
            String[] row = matrix[i];
            String origin = row[0];
            ICity originCity = graph.getCity(origin);
            for (int j = i + 1; j < row.length; j++) {
                String entry = row[j];
                if (!entry.equals("-")) {
                    // There is information about a connection to another city
                    double length = Double.valueOf(entry);
                    String destination = matrix[0][j];
                    ICity destinationCity = graph.getCity(destination);
                    originCity.addConnection(new Connection(destinationCity, length));
                    destinationCity.addConnection(new Connection(originCity, length));
                }
            }
        }
        return graph;
    }

    /**
     * Decodes the route Graph from the given file.
     *
     * @param filename the path of the file. (absolute or relative)
     * @return the decoded route Graph
     */
    public static Graph buildGraphFromFile(String filename) {
        return buildGraph(readCSV(filename));
    }
}
