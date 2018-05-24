public class CityFactory {

    /**
     * Erstellt ein neues Stadt-Objekt welches das Interface von ICity implementiert.
     *
     * @param name der Name der Stadt, welche erstellt werden muss.
     * @return eine neues Stadt-Objekt mit dem gegeben Namen.
     */
    public static ICity createCity(String name) {
        ICity tmp = (ICity) new City(name);
        return tmp;
    }


    /**
     * Erstellt eine neue Route vom Typ IRoute.
     * @param origin der Startpunkt der Route
     * @return eine neue Route von Typ  IRoute.
     */
    public static IRoute createRoute(ICity origin) {
        IRoute tmp = (IRoute) new Route(origin);
        return tmp;
    }

}
