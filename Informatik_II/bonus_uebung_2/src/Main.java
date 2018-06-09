public class Main {
    public static void main(String[] args) {
        //CodeExpert
	//Graph graph = GraphBuilder.buildGraphFromFile("./files/Cities.csv");
        //IDE
        Graph graph = GraphBuilder.buildGraphFromFile("./files/Cities.csv");
 	//System.out.println(graph.getCity("Zurich").getName());
        IRoute r = graph.calculateShortestPath("Basel", "Zurich");
        
	System.out.println(r);
        r = graph.calculateShortestPath("Locarno", "Zurich");
	
	System.out.println(r);
    }

}
