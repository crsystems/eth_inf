public class Main {
    public static void main(String[] args) {
        //CodeExpert
	//Graph graph = GraphBuilder.buildGraphFromFile("./files/Cities.csv");
        //IDE
        Graph graph = GraphBuilder.buildGraphFromFile("./files/Cities.csv");
 	//System.out.println(graph.getCity("Zurich").getName());
        IRoute r = graph.calculateShortestPath("Locarno", "Basel");
        
	System.out.println(r);
        r = graph.calculateShortestPath("Locarno", "Zürich");
	
	System.out.println(r);
    }

}
