import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuffer;

public class Route implements IRoute {
    	
	private ICity origin;
	private ArrayList<Connection> route;

    	public Route(ICity origin) {
        	this.origin = origin;
		this.route = new ArrayList<Connection>();
    	}

	/**
	* Fuegt eine neue Verbindung an das Ende dieser Route dazu.
	*
	* @param connection die neue Verbindung
	*/
	public void addConnectionToRoute(Connection connection){
		this.route.add(connection);
	}

	/**
	* Gibt die Laenge der aktuellen Route zurueck.
	* (Vom Startpunkt bis an das Ende der Route)
	*
	* @return die Laenge der Route
	*/
	public double getLength(){
		double sum = 0.0;
		for(int i = 0; i < route.size(); i++){
			sum += route.get(i).getDistance();
		}
		return sum;
	}

	/**
	* Gibt eine Liste der Verbindungen dieser Route zurueck.
	* @return die Liste von Verbindungen
	*/
	public List<Connection> getConnections(){
		return (List<Connection>) route;
	}

	/**
	* Gibt die Stadt zurueck, in welcher diese Route started.
	* @return die Start-Stadt dieser Route
	*/
	public ICity getOrigin(){
		return origin;
	}

	/**
	* Gibt eine String Representation der Route mit dem folgendem Pattern zurueck:
	* start-stadt, stadt-2, .., stadt-n; distance: value km.
	* Als Beispiel, die Route  Zuerich->1.2->Bern-3.1->Basel waere wie folgt dargestellt:
	* "Zuerich, Bern, Basel; distance: 4.3 km."
	* "No route." falls keine Route existiert (d.h. Die Route hat nur eine Start-Stadt, aber keine Connections)
	*
	* @return die String Representation der Route
	*/
	@Override
	public String toString(){

		StringBuffer str = new StringBuffer();
		
		if(route.get(0) == null || route.size() == 0){
			return "No Route.";
		}
		
		str.append(origin.getName());

		for(int i = 0; i < route.size(); i++){
			str.append(", ");
			str.append(route.get(i).getDestination().getName());
		}
		
		str.append("; distance: ");
		
		double dist = 0.0;
		for(int i = 0; i < route.size(); i++){
			dist += route.get(i).getDistance();
		}

		str.append(dist);
		str.append(" km.");
		return str.toString();
	}
}
