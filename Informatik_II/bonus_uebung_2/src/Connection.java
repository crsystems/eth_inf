/**
 * Representiert eine Verbindung zu einer anderen Stadt.
 */
public class Connection {

    /**
     * Das Ziel dieser Verbindung.
     */
    private ICity destination;

    /**
     * Die Laenge der Verbindung bis zu der Ziel-Stadt.
     */
    private double distance;

    /**
     * Erstellt eine neue Verbindung.
     * @param destination die Ziel-Stadt der Verbindung
     * @param distance die Laenge der Verbindung zu dieser Stadt
     */
    public Connection(ICity destination, double distance) {
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * Retourniert das Ziel dieser Verbindung.
     * @return die Ziel-Stadt
     */
    public ICity getDestination() {
        return destination;
    }

    /**
     * Retourniert die Laenge dieser Verbindug.
     * @return die Laenge der Verbindung
     */
    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Connection) {
            Connection oc = (Connection) other;
            return oc.destination.getName().equals(this.destination.getName())
                    && oc.distance == this.distance;
        }
        return false;
    }
}
