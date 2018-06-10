import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Collection;
import java.util.Iterator;

/**
 * Repraesentiert den Staedtegraph
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
        
        if(cities.get(originName) == null || cities.get(destinationName) == null){
            throw new IllegalArgumentException("invalid origin or destination Name");
        }
        
        if(originName.equals(destinationName)){
            return (IRoute) new Route(cities.get(originName));
        }
        
        ICity origin = null;
        
        //alle Knoten in Q(FluffiList) speichern und initialisieren
        ArrayList<ICity> fluffiList = new ArrayList<ICity>(); //die noch zu bearbeitenden Staedte
        ArrayList<ICity> citiesdone = new ArrayList<ICity>(); //die fertigen Staedte mit Distanz etc in einer Liste
        ArrayList<ICity> citiesarray = new ArrayList<ICity>(); //ArrayList aus der HashMap
        citiesarray.addAll(cities.values());
     
        
        for(int i=0; i<citiesarray.size(); i++){
            
            ICity stadt = citiesarray.get(i);
            
            if(stadt.getName().equals(originName)){
                stadt.reset();
                stadt.setDistance(0);
                citiesdone.add(stadt);
                origin = stadt;
            }
            else if(stadt.getName().equals(destinationName)){
                stadt.reset();
                fluffiList.add(stadt);
            }
            else{
                stadt.reset();
                fluffiList.add(stadt);
            }
        }
        ICity fluffihausen = origin; //damit am anfang origin als min Distanz-Stadt ausgewaehlt wird
        
        while(fluffiList.size() > 0){
            //
            //Den Knoten k mit minimaler Distanz finden
            double min = Double.MAX_VALUE;
            int indexstadt = -1;
            if(!fluffihausen.equals(origin)){    // da origin nicht in fluffiList vorhanden ist
                for(int i=0; i<fluffiList.size(); i++){
                    if((fluffiList.get(i)).getDistance() < min){
                        
                        min = fluffiList.get(i).getDistance();
                        indexstadt = i;
                    }
                }
            
                //
                //k aus Q entfernen und Predecessor setzen
                fluffihausen = fluffiList.remove(indexstadt);
                citiesdone.add(fluffihausen);
                //
            }
            
            //Iterator ueber die Nachbar-Collection erstellen
            Collection<Connection> nachbarncol = fluffihausen.getConnections();
            ArrayList<Connection> nachbarn = new ArrayList<Connection>();
            nachbarn.addAll(nachbarncol);
            
            
            for(int r = 0; r < nachbarn.size(); r++){
                Connection verbindung = nachbarn.get(r);
                ICity nachbar = verbindung.getDestination();
                
                if(fluffiList.contains(nachbar)){
                    
                    int index = fluffiList.indexOf(nachbar);
                    double weg = verbindung.getDistance();
                    double distanz = nachbar.getDistance();
                    
                    
                    
                    if(fluffihausen.getDistance()+weg < distanz){ //eigentlicher Test der Distanz
                        nachbar.setDistance(fluffihausen.getDistance()+weg); ///////////
                        nachbar.setPredecessor(fluffihausen);
                        fluffiList.set(index, nachbar);
                    } 
                
                }
                
            }
            
            if(fluffihausen.equals(origin)){
                fluffihausen = new City("NotACity");      //da equals mit einer Null City nicht funktioniert
            }
        }
        
        ArrayList<ICity> routelist = new ArrayList<ICity>();
        ICity currentstadt = null;
        
        for(int i=0; i<citiesdone.size(); i++){                   // findet die destination Stadt in citiesdone
            
            if(citiesdone.get(i).getName().equals(destinationName)){
                currentstadt = citiesdone.get(i);
                break;
            }
        }
        
        while(currentstadt.getPredecessor() != null){
            routelist.add(currentstadt);
            currentstadt = currentstadt.getPredecessor();
        }
        routelist.add(currentstadt); //da predecessor == null weil currentstadt origin ist
        
        if(routelist.size()<=1 || !(routelist.get(0).getName().equals(destinationName)) || !(routelist.get(routelist.size()-1).getName().equals(originName))){
            throw new NoRouteFoundException("No Route from Origin to Destination was found.");
        }
        
        
        IRoute route = (IRoute) new Route(origin);
        for(int i=routelist.size()-1; i>0; i--){
            route.addConnectionToRoute(routelist.get(i).getConnection(routelist.get(i-1).getName()));
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
     */
    public IRoute calculateShortestPathLimited(String originName, String destinationName, double limit) throws IllegalArgumentException, NoRouteFoundException {
        
        
        if(cities.get(originName) == null || cities.get(destinationName) == null){
            throw new IllegalArgumentException("invalid origin or destination Name");
        }
        
        if(originName.equals(destinationName)){
            return (IRoute) new Route(cities.get(originName));
        }
        
        
        ICity origin = null;
        
        //alle Knoten in Q(FluffiList) speichern und initialisieren
        ArrayList<ICity> fluffiList = new ArrayList<ICity>(); //die noch zu bearbeitenden Staedte
        ArrayList<ICity> citiesdone = new ArrayList<ICity>(); //die fertigen Staedte mit Distanz etc in einer Liste
        ArrayList<ICity> citiesarray = new ArrayList<ICity>(); //ArrayList aus der HashMap
        citiesarray.addAll(cities.values());
     
        
        for(int i=0; i<citiesarray.size(); i++){
            
            ICity stadt = citiesarray.get(i);
            
            if(stadt.getName().equals(originName)){
                stadt.reset();
                stadt.setDistance(0);
                citiesdone.add(stadt);
                origin = stadt;
            }
            else if(stadt.getName().equals(destinationName)){
                stadt.reset();
                fluffiList.add(stadt);
            }
            else{
                stadt.reset();
                fluffiList.add(stadt);
            }
        }
        ICity fluffihausen = origin; //damit am anfang origin als min Distanz-Stadt ausgewaehlt wird
        
        while(fluffiList.size() > 0){
            //
            //Den Knoten k mit minimaler Distanz finden
            double min = Double.MAX_VALUE;
            int indexstadt = -1;
            if(!fluffihausen.equals(origin)){    // da origin nicht in fluffiList vorhanden ist
                for(int i=0; i<fluffiList.size(); i++){
                    if((fluffiList.get(i)).getDistance() <= min){
                        
                        min = fluffiList.get(i).getDistance();
                        indexstadt = i;
                    }
                }
                
                //
                //k aus Q entfernen und Predecessor setzen
                fluffihausen = fluffiList.remove(indexstadt);
                citiesdone.add(fluffihausen);
                //
            }
            
            //Iterator ueber die Nachbar-Collection erstellen
            Collection<Connection> nachbarncol = fluffihausen.getConnections();
            ArrayList<Connection> nachbarn = new ArrayList<Connection>();
            nachbarn.addAll(nachbarncol);
            
            
            for(int r = 0; r < nachbarn.size(); r++){
                Connection verbindung = nachbarn.get(r);
                ICity nachbar = verbindung.getDestination();
                
                if(fluffiList.contains(nachbar)){
                    
                    int index = fluffiList.indexOf(nachbar);
                    double weg = verbindung.getDistance();
                    double distanz = nachbar.getDistance();
                    
                    if(fluffihausen.getDistance()+weg < distanz && weg <= limit){ //eigentlicher Test der Distanz 
                        nachbar.setDistance(fluffihausen.getDistance()+weg); ///////////
                        nachbar.setPredecessor(fluffihausen);
                        fluffiList.set(index, nachbar);
                    } 
                
                }
                
            }
            
            if(fluffihausen.equals(origin)){
                fluffihausen = new City("NotACity");      //da equals mit einer Null City nicht funktioniert
            }
        }
        
        ArrayList<ICity> routelist = new ArrayList<ICity>();
        ICity currentstadt = null;
        
        for(int i=0; i<citiesdone.size(); i++){                   // findet die destination Stadt in citiesdone
            
            if(citiesdone.get(i).getName().equals(destinationName)){
                currentstadt = citiesdone.get(i);
                break;
            }
        }
        
        while(currentstadt.getPredecessor() != null){
            routelist.add(currentstadt);
            currentstadt = currentstadt.getPredecessor();
        }
        
        routelist.add(currentstadt); //da predecessor == null weil currentstadt origin ist
        
        
        if(routelist.size()<=1 || !(routelist.get(0).getName().equals(destinationName)) || !(routelist.get(routelist.size()-1).getName().equals(originName))){
            throw new NoRouteFoundException("No Route from Origin to Destination was found.");
        }
        
        
        
        
        IRoute route = (IRoute) new Route(origin);
        for(int i=routelist.size()-1; i>0; i--){
            route.addConnectionToRoute(routelist.get(i).getConnection(routelist.get(i-1).getName()));
        }
        
    
        
        return route;
    }
}
