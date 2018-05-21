import java.util.Collection;

/**
 * Representiert eine Stadt im Staedte-Graph.
 * Beinhalted auch Hilfs-Methoden fuer den Dijkstra algorithmus,
 * um den kuerzesten Weg zwischen zwei Staedten zu finden (Distanz und aktueller Vorgaenger).
 */
public interface ICity {

    /**
     * Gibt den Namen dieser Stadt zurueck.
     *
     * @return der Name der Stadt
     */
    String getName();

    /**
     * Fuegt dieser Stadt ein neune Verbindung zu einer anderen Stadt hinzu.
     *
     * @param con die neue Verbindung
     */
    void addConnection(Connection con);

    /**
     * Gibt die Verbindung von dieser Stadt zur gegeben Stadt zurueck.
     * Falls diese Verbindung von dieser Stadt nicht existiert, gibt man null zurueck.
     *
     * @param destination der Name der Stadt, die man erreichen will
     * @return die Verbindung zur gegebenen Stadt. Null falls keine Verbindung existiert.
     */
    Connection getConnection(String destination);

    /**
     * Gibt alle Verbingung zu anderen Staedten von dieser Stadt zurueck.
     *
     * @return eine Collection von Connections
     */
    Collection<Connection> getConnections();

    /**
     * Die aktuelle Distanz zum Ursprung im Dijkstra-Algorithmus.
     *
     * @return die Distanz
     */
    double getDistance();


    /**
     * Setzt die aktuelle Distanz zum Ursprung im Dijkstra-Algorithmus auf einen neuen Wert.
     * @param distance der neue Wert der Distanz
     * @return die Distanz
     */
    void setDistance(double distance);

    /**
     * Der aktuelle Vorgaenger-Knoten (i.e. Stadt) im Dijkstra-Algorithmus.
     *
     * @return der aktuelle Vorgaenger dieser Stadt
     */
    ICity getPredecessor();

    /**
     * Setzt den aktuelle Vorgaenger-Knoten dieser Stadt im Dijkstra-Algorithmus neu.
     *
     * @param predecessor der neue Vorgaenger-Knoten der Stadt
     */
    void setPredecessor(ICity predecessor);

    /**
     * Setz die Dijkstra-Algorithmus Werte auf den Anfangszustand zurueck (i.e. Distanz auf den maximal moeglichen Wert
     * und Vorgaenger auf null).
     */
    void reset();

    /**
     * Gibt den Namen dieser Stadt als String zurueck.
     *
     * @return der Name der Stadt
     */
    @Override
    String toString();
    
    /**
     * Zwei Staedte sind gleich, falls sie den gleichen Namen haben.
     *
     * @return true falls beiden Staedte den gleichen Namen haben, sonst false.
     */
    @Override
    boolean equals(Object object);
    
}