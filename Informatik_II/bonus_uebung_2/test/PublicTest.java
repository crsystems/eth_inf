import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class PublicTest {

    // IDE
    public static final String CITY_GRAPH_FILE_PATH = "./files/Cities.csv";

    // CodeExpert
    //public static final String CITY_GRAPH_FILE_PATH = "Root/files/Cities.csv";

    @Test
    public void check_city_class() {
        // Name
        ICity city1 = CityFactory.createCity("Test1");
        ICity city11 = CityFactory.createCity("Test1");
        ICity city2 = CityFactory.createCity("Test2");
        ICity city3 = CityFactory.createCity("Test3");

        Assert.assertEquals("getName() gibt nicht den richtigen Namen zurueck", "Test1", city1.getName());
        Assert.assertEquals("toString() gibt nicht den Namen der Stadt zurueck", "Test1", city1.toString());

        // Equals
        Assert.assertEquals("equals() ist nicht true falls der Name der Staedte gleich ist", city11, city1);

        // Distance
        city1.setDistance(10.11);
        Assert.assertEquals("getDistance() gibt nicht die richtige Distanz zurueck", 10.11, city1.getDistance(), 0.001);

        //Predecessor
        city1.setPredecessor(city2);
        Assert.assertEquals("getPredecessor() gibt nicht die richtigen Vorgaenger zurueck", city2.getName(), city1.getPredecessor().getName());

        //reset
        city1.reset();
        Assert.assertEquals(Double.MAX_VALUE, city1.getDistance(), 0.001);
        Assert.assertEquals(null, city1.getPredecessor());

        // connections
        Connection c1 = new Connection(city2, 2);
        Connection c2 = new Connection(city3, 3);

        city1.addConnection(c1);
        city1.addConnection(c2);

        for (Connection in : city1.getConnections()) {
            Assert.assertTrue(in.equals(c1) || in.equals(c2));
        }
        Assert.assertEquals(2, city1.getConnections().size());

    }

    private static String res1 = "Test0, Test1, Test2, Test3, Test4, Test5, Test6, Test7, Test8, Test9; distance: 45.0 km.";

    @Test
    public void check_route_class() {
        ICity[] cities = new City[10];
        double dist = 0;
        for (int i = 0; i < 10; i++) {
            cities[i] = CityFactory.createCity(String.format("Test%d", i));
        }

        IRoute r = CityFactory.createRoute(cities[0]);
        for (int i = 1; i < 10; i++) {
            r.addConnectionToRoute(new Connection(cities[i], (double) i));
            dist += i;
        }
        Assert.assertEquals("getLength() gibt nicht die richtige  Laenge zurueck", r.getLength(), dist, 0.001);
        Assert.assertEquals("toString() entspricht nicht dem spezifizierten Pattern", r.toString(), res1);

        Assert.assertEquals("getOrigin() gibt nicht den richtigen Startpunkt zurueck", r.getOrigin().getName(), cities[0].getName());

        int count = 1;
        for (Connection cur : r.getConnections()) {
            Assert.assertEquals(cur.getDestination().getName(), cities[count].getName());
            Assert.assertEquals(cur.getDistance(), ((double) count), 0.001);
            count++;
        }

    }

    private static Graph graph = GraphBuilder.buildGraphFromFile(CITY_GRAPH_FILE_PATH);

    private static String citiesData = "Basel;Bellinzona;Bern;Chur;Genf;Interlaken;Kreuzlingen;Lausanne;Locarno;Lugano;Luzern;Neuenburg;Sankt Gallen;Schaffhausen;Thun;Winterthur;Zug;Zurich";
    private static String[] cities = citiesData.split(";");

    private static String solBasel = "0.00,239.90,101.00,207.20,257.30,160.30,154.80,194.00,263.00,270.50,99.90,128.00,174.60,98.80,129.50,112.50,120.60,88.20";
    private static String solZurich = "88.20,203.50,125.00,119.00,281.30,131.70,66.60,218.00,226.60,234.10,63.50,176.60,86.40,52.70,153.50,24.30,32.40,0.00";
    private static String solLausanne = "194.00,331.60,93.00,333.70,63.30,152.30,284.60,0.00,354.70,362.20,191.60,72.80,304.40,270.70,121.50,242.30,222.70,218.00";
    private static String solLocarno = "263.00,23.10,261.70,139.10,418.00,231.30,267.10,354.70,0.00,53.70,163.10,313.30,242.10,279.30,262.10,250.90,194.20,226.60";

    private static double[] transform(String sol) {
        String[] items = sol.split(",");
        double[] res = new double[items.length];
        for (int iter = 0; iter < res.length; iter++) {
            res[iter] = Double.valueOf(items[iter]);
        }
        return res;
    }

    private Connection checkConnectionExists(Collection<Connection> conn, String name) {
        for (Connection c : conn)
            if (c.getDestination().getName().equals(name))
                return c;
        return null;
    }

    private void checkRouteValid(Graph g, IRoute route, String from, String to) {
        List<Connection> connections = route.getConnections();
        ICity cur = g.getCity(route.getOrigin().getName());
        Assert.assertEquals("Startpunkt der Route stimmt nicht", cur.getName(), from);
        for (Connection conn : connections) {
            Connection real = checkConnectionExists(cur.getConnections(), conn.getDestination().getName());
            Assert.assertTrue(real != null);
            Assert.assertEquals(conn.getDistance(), real.getDistance(), 0.001);
            cur = g.getCity(conn.getDestination().getName());
        }
        Assert.assertEquals("Ziel der Route stimmt nicht", cur.getName(), to);
    }

    private double roundTest(double in) {
        return ((double) ((long) (in * 100))) / 100;
    }

    private void checkSol(String start, String solString) {
        double[] solution = transform(solString);
        for (int i = 0; i < cities.length; i++) {
            IRoute r = graph.calculateShortestPath(start, cities[i]);
            checkRouteValid(graph, r, start, cities[i]);
            Assert.assertTrue("Nicht die kuerzeste Route!", roundTest(r.getLength()) <= solution[i]);
        }
    }

    @Test
    public void check_dist1() {
        checkSol("Basel", solBasel);
    }

    @Test
    public void check_dist2() {
        checkSol("Lausanne", solLausanne);
    }

    @Test
    public void check_dist3() {
        checkSol("Zurich", solZurich);
    }

    @Test
    public void check_dist4() {
        checkSol("Locarno", solLocarno);
    }

    private static void addConnections(ICity c1, ICity c2, double dist) {
        c1.addConnection(new Connection(c2, dist));
        c2.addConnection(new Connection(c1, dist));
    }

    private static Graph createLimitedGraph(int len, int dist) {
        assert (len >= 3);
        ICity[] cities = new ICity[len];
        Graph g = new Graph();
        for (int i = 0; i < cities.length; i++) {
            cities[i] = CityFactory.createCity("C_" + i);
            g.putCity("C_" + i, cities[i]);
        }

        addConnections(cities[0], cities[cities.length - 1], ((cities.length - 1) * dist) - 1);
        for (int i = 0; i < cities.length - 1; i++) {
            addConnections(cities[i], cities[i + 1], dist);
        }
        return g;
    }

    @Test
    public void check_dist_limited() {
        Graph g = createLimitedGraph(10, 2);
        IRoute r = g.calculateShortestPathLimited("C_0", "C_9", 3);
        checkRouteValid(g, r, "C_0", "C_9");
        Assert.assertEquals("Not a limited shortest path ", 9, r.getConnections().size());
        Assert.assertEquals("Not a limited shortest path ", 18.0, r.getLength(), 0.001);

    }
}
