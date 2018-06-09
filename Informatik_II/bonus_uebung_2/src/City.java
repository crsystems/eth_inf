import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class City implements ICity {
    
	private String name;
	private ArrayList<Connection> connections;
	private double distance;
	private ICity predecessor;

	public City(String name) {
        	this.name = name;
		this.distance = Double.MAX_VALUE;
		this.predecessor = null;
		this.connections = new ArrayList<Connection>();
    	}

    	/**
     	* Gibt den Namen dieser Stadt zurueck.
     	*
     	* @return der Name der Stadt
     	*/
    	public String getName(){
		return name;
	}

    	/**
     	* Fuegt dieser Stadt ein neune Verbindung zu einer anderen Stadt hinzu.
     	*
     	* @param con die neue Verbindung
     	*/
    	public void addConnection(Connection con){
		//not permitting duplicates
		if(!connections.contains(con)){
			connections.add(con);
		}
	}

    	/**
     	* Gibt die Verbindung von dieser Stadt zur gegeben Stadt zurueck.
     	* Falls diese Verbindung von dieser Stadt nicht existiert, gibt man null zurueck.
     	*
     	* @param destination der Name der Stadt, die man erreichen will
     	* @return die Verbindung zur gegebenen Stadt. Null falls keine Verbindung existiert.
     	*/
    	public Connection getConnection(String destination){
		for(int i = 0; i < connections.size(); i++){
			if(connections.get(i).getDestination().getName().equals(destination)){
				return connections.get(i);
			}
		}
		return null;
	}

    	/**
     	* Gibt alle Verbingung zu anderen Staedten von dieser Stadt zurueck.
     	*
     	* @return eine Collection von Connections
     	*/
    	public Collection<Connection> getConnections(){
		return connections;
	}

    	/**
     	* Die aktuelle Distanz zum Ursprung im Dijkstra-Algorithmus.
     	*
     	* @return die Distanz
     	*/
    	public double getDistance(){
		return distance;
	}


    	/**
     	* Setzt die aktuelle Distanz zum Ursprung im Dijkstra-Algorithmus auf einen neuen Wert.
     	* @param distance der neue Wert der Distanz
     	* @return die Distanz
     	*/
    	public void setDistance(double distance){
		this.distance = distance;
	}

    	/**
     	* Der aktuelle Vorgaenger-Knoten (i.e. Stadt) im Dijkstra-Algorithmus.
     	*
     	* @return der aktuelle Vorgaenger dieser Stadt
     	*/
    	public ICity getPredecessor(){
		return predecessor;
	}

    	/**
     	* Setzt den aktuelle Vorgaenger-Knoten dieser Stadt im Dijkstra-Algorithmus neu.
     	*
     	* @param predecessor der neue Vorgaenger-Knoten der Stadt
    	*/
    	public void setPredecessor(ICity predecessor){
		this.predecessor = predecessor;
	}

    	/**
     	* Setz die Dijkstra-Algorithmus Werte auf den Anfangszustand zurueck (i.e. Distanz auf den maximal moeglichen Wert
     	* und Vorgaenger auf null).
    	*/
    	public void reset(){
		this.distance = Double.MAX_VALUE;
		this.predecessor = null;
	}

    	/**
     	* Gibt den Namen dieser Stadt als String zurueck.
     	*
     	* @return der Name der Stadt
     	*/
    	public String toString(){
		return name;
	}
    
    	/**
     	* Zwei Staedte sind gleich, falls sie den gleichen Namen haben.
     	*
     	* @return true falls beiden Staedte den gleichen Namen haben, sonst false.
     	*/
   	public boolean equals(Object object){
		if(object instanceof ICity){
			ICity tmp = (ICity) object;
			return tmp.getName().equals(this.name);
		}else{
			return false;
		}
	}	
    
}
