import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Repraesnentiert den Staedtegraph
 */
public class Graph {

    /**
     * Speichert alle Knoten des Graphen.
     * Die Key fuer einen Knoten entspricht dem Name der jeweiligen Stadt.
     */
    private HashMap<String, ICity> cities = new HashMap<>();

    public ICity getCity(String name) {
        if (cities.containsKey(name)) {
            return cities.get(name);
        }
        ICity c = CityFactory.createCity(name);
        cities.put(name, c);
        return c;
    }

    public void putCity(String name, ICity city) {
        cities.put(name, city);
    }

    /**
     * Berechnet den kuerzesten Weg in Kilometer (km) von der Start-Stadt zur Ziel-Stadt mit dem Dijkstra-Algorithmus.
     * Falls es mehrere kuerzeste Wege gibt, wird einfach einer dieser Wege zurueckgegeben.
     * Falls die Starts-Stadt dieselbe ist wie die Ziel-Stadt und im Graph vorhanden ist, dann wird
     * eine leere Route {@link IRoute} retourniert (d.h. Eine Route ohne Connections zurueckgegeben)
     *
     * @param originName      der Name der Start-Stadt {@link ICity}
     * @param destinationName der Name der Ziel-Stadt {@link ICity}
     * @return eine kuerzeste {@link IRoute} von der Start-Stadt zur Ziel-Stadt.
     * @throws IllegalArgumentException falls die Start-Stadt oder die Ziel-Stadt nicht existiert.
     * @throws NoRouteFoundException    falls keine Route von der Start-Stadt zur Ziel-Stadt existiert.
     */
    public IRoute calculateShortestPath(String originName, String destinationName) throws IllegalArgumentException, NoRouteFoundException {
        ICity origin = (ICity) new City(originName);
	ICity destination = (ICity) new City(destinationName);
	
	if(origin.equals(destination)){
		return (IRoute) new Route(origin);
	}

	this.initDijkstra();








        return null;
    }

    /**
     * Berechnet den kuerzesten Weg in Kilometer (km) von der Ursprungs-Stadt zur Ziel-Stadt
     * mit dem Dijkstra-Algorithmus, wobei eine gewaehlte Teilstrecke in der Route nicht 
     * laenger als ein gewaehltes Limit sein darf.
     * Falls es mehrere kuerzeste Wege gibt, wird einfach einer dieser Wege zurueckgegeben.
     * Falls Startstadt dieselbe ist wie die Zielstadt und im Graph vorhanden ist, dann wird
     * eine leere Route {@link IRoute} (i.e. Eine Route ohne Connections zurueckgegeben)
     *
     * @param originName      der Name der Ursprungs/Start-Stadt {@link ICity}
     * @param destinationName der Name der Ziel-Stadt {@link ICity}
     * @param limit           das Limit, welche die maximale Laenge einer Teilstrecke in der Route angibt.
     * @return eine kuerzeste {@link IRoute} von der Start-Stadt zur Ziel-Stadt.
     * @throws IllegalArgumentException falls die Start-Stadt oder die Ziel-Stadt nicht existiert.
     * @throws NoRouteFoundException    falls keine Route von der Start-Stadt zur Ziel-Stadt existiert.
     */
    public IRoute calculateShortestPathLimited(String originName, String destinationName, double limit) throws IllegalArgumentException, NoRouteFoundException {
        
	ICity origin = (ICity) new City(originName);
	ICity destination = (ICity) new City(destinationName);
	
	if(origin.equals(destination)){
		return (IRoute) new Route(origin);
	}




        return null;
    }


    private void initDijkstra(String origin){

	cities.get(origin).setDistance(0.0);
	cities.get(origin).setPredecessor(null);

	HashMap<String, ICity> tmp = new HashMap<String, ICity>();
	tmp.putAll(cities);

	tmp.remove(origin);

	ArrayList<ICity> cts = new ArrayList<ICity>();
	cts.addAll(tmp.values());
	
	while


    }
}
