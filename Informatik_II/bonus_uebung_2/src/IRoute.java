import java.util.List;

/**
 * Representiert eine Route zwischen zwei Staedten.
 * Jede Route hat einen Ursprung und eine Liste von Verbindungen, welche die Route bestimmt.
 */
public interface IRoute {

    /**
     * Fuegt eine neue Verbindung an das Ende dieser Route dazu.
     *
     * @param connection die neue Verbindung
     */
    void addConnectionToRoute(Connection connection);

    /**
     * Gibt die Laenge der aktuellen Route zurueck.
     * (Vom Startpunkt bis an das Ende der Route)
     *
     * @return die Laenge der Route
     */
    double getLength();

    /**
     * Gibt eine Liste der Verbindungen dieser Route zurueck.
     * @return die Liste von Verbindungen
     */
    List<Connection> getConnections();

    /**
     * Gibt die Stadt zurueck, in welcher diese Route started.
     * @return die Start-Stadt dieser Route
     */
    ICity getOrigin();

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
    String toString();
}
