import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

/**
 * Repraesnentiert den Staedtegraph
 */
public class Graph {

    /**
     * Speichert alle Knoten des Graphen.
     * Die Key fuer einen Knoten entspricht dem Name der jeweiligen Stadt.
     */
    private HashMap<String, ICity> cities = new HashMap<>();

    private String last_origin = new String();
    private double last_limit = Double.MAX_VALUE;

    /**
     * Checks wether the Dijkstra algorithm was already run from this origin and resets the cities if not
     */
    private void setup(String origin, double limit){
	    if(!origin.equals(last_origin)){
		    Collection<ICity> ct = cities.values();
		    Iterator<ICity> it = ct.iterator();

		    while(it.hasNext()){
			    cities.get(it.next().getName()).reset();
		    }
		    this.last_origin = origin;
		    this.last_limit = limit;
		    this.initDijkstra(origin, limit);
	    }
    }


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
        
	ICity origin = cities.get(originName);
	ICity destination = cities.get(destinationName);

	if(origin == null){
		throw new IllegalArgumentException("Origin is not a valid city");
	}else if(destination == null){
		throw new IllegalArgumentException("Destination is not a valid city");
	}else if(origin.equals(destination)){
		return (IRoute) new Route(origin);
	}

	//setting up the cities with the help of the Dijkstra algorithm and an virtually infinte distance limit per connection
	this.setup(originName, Double.MAX_VALUE);

	IRoute route = (IRoute) new Route(origin);
	ArrayList<ICity> tmp_route = new ArrayList<ICity>();
	ICity cur = destination;
	
	//saving the route from destination to origin via the predecessor variable
	while(cur.getPredecessor() != null){
		tmp_route.add(cur);
		cur = cur.getPredecessor();
	}

	if(tmp_route.size() == 0){
		throw new NoRouteFoundException("No route was found");
	}

	//manually adding the origin as a offset
	tmp_route.add(origin);

	//iterating backwards over the temporary (and inversed) route to create the real route
	for(int i = tmp_route.size()-1; i > 0; i--){
		if(i > 1){
			//adding connection from current city to next city
			route.addConnectionToRoute(tmp_route.get(i).getConnection(tmp_route.get(i-1).getName()));
		}else{
			route.addConnectionToRoute(tmp_route.get(1).getConnection(destinationName));
		}

	}
	
        return route;
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
        
	ICity origin = cities.get(originName);
	ICity destination = cities.get(destinationName);

	if(origin == null){
		throw new IllegalArgumentException("Origin is not a valid city");
	}else if(destination == null){
		throw new IllegalArgumentException("Destination is not a valid city");
	}else if(origin.equals(destination)){
		return (IRoute) new Route(origin);
	}

	
	//setting up the cities with the help of the Dijkstra algorithm and the specified distance limit per connection
	this.setup(originName, limit);

	IRoute route = (IRoute) new Route(origin);
	ArrayList<ICity> tmp_route = new ArrayList<ICity>();
	ICity cur = destination;
	
	//saving the route from destination to origin via the predecessor variable
	while(cur.getPredecessor() != null){
		tmp_route.add(cur);
		cur = cur.getPredecessor();
	}

	if(tmp_route.size() == 0){
		throw new NoRouteFoundException("No route was found");
	}

	//manually adding the origin as a offset
	tmp_route.add(origin);

	//iterating backwards over the temporary (and inversed) route to create the real route
	for(int i = tmp_route.size()-1; i > 0; i--){
		if(i > 1){
			//adding connection from current city to next city
			route.addConnectionToRoute(tmp_route.get(i).getConnection(tmp_route.get(i-1).getName()));
		}else{
			route.addConnectionToRoute(tmp_route.get(1).getConnection(destinationName));
		}

	}
	
        return route;
    }

    /**
     * Method to initialize the cities of the hashmap with the dijkstra algorithm.
     * @param origin	the city where the Dijkstra algorithm sets its root
     * @param limit	the distance limit per connection. Set this to Double.MAX_VALUE to disable limiting
     */
    private void initDijkstra(String origin, double limit){

	ICity current = cities.get(origin);
	
	//setting up the origin
	current.setDistance(0.0);
	current.setPredecessor(null);

	//currently checked connection variable
	Connection cur_check = null;

	//arraylist of already completed cities
	ArrayList<String> known = new ArrayList<String>();

	//iterator over the connections of the origin
	Iterator<Connection> tmp = current.getConnections().iterator();

	//checking every connection that origin has 
	while(tmp.hasNext()){
		cur_check = tmp.next();
		//if the distance of the the connection is smaller than the distance variable in the destination city, replace the
		//values and set the predecessor accordingly
		//this will always be the case for the origin node
		if(cur_check.getDistance() < cur_check.getDestination().getDistance() && cur_check.getDistance() <= limit){
			cur_check.getDestination().setDistance(cur_check.getDistance());
			cur_check.getDestination().setPredecessor(current);
		}
	}

	//know origin is "known" as all connections were checked
	//as distance is the same in both directions further checking is unnecessary
	known.add(origin);
	
	while(current != null){
		
		//finding the next possible city via an iterator over all of them
		Iterator<ICity> nxt = cities.values().iterator();
		while(nxt.hasNext()){
			ICity test = nxt.next();
			//if we find a city that was given a value and is not in the known array, we take that as our new city
			if(!known.contains(test.getName()) && test.getDistance() < Double.MAX_VALUE){
					current = cities.get(test.getName());
					break;
			}else{
				current = null;
			}
		}
		
		//ugly method to exit the while loop id no city was found (so Dijkstra is finished)
		if(current == null){
			break;
		}

		//get all the connections of our current city in an iterator again
		Iterator<Connection> tmp2 = current.getConnections().iterator();

		//iterate over them all
		while(tmp2.hasNext()){
			cur_check = tmp2.next();
			//if the destination of the current connection is not known
			if(!known.contains(cur_check.getDestination().getName())){
				
				System.out.println("Connection from " + current + " to " + cur_check.getDestination().getName() + ".");
				System.out.println("Current distance: " + cur_check.getDestination().getDistance() + " 	vs.	" + (cur_check.getDistance() + current.getDistance()));
				//and if the distance to the current city plus the distance of the connection is smaller than
				//the distance of the destination city
				//AND
				//if the distance of the connection is smaller than the limit
				//set the distance and the predecessor of the destination accordingly
				if((current.getDistance() + cur_check.getDistance()) < cur_check.getDestination().getDistance() && cur_check.getDistance() <= limit){
					cur_check.getDestination().setDistance((current.getDistance() + cur_check.getDistance()));
					cur_check.getDestination().setPredecessor(current);
				}
			}
		}

		//after all connections were checked, this city is fully initialized and can be ignored next time
		known.add(current.getName());
	}
    }
}
