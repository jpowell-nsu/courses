package weather;

/**
 * One weather station in the network. PROVIDED TO STUDENTS: use as is.
 * Lab W14: The Weather Station Network.
 *
 * @param id the station's three-letter ID, such as ASH
 * @param name the station's name
 * @param county the county the station is in
 * @param elevation the station's elevation in feet
 * @author  Dr. Powell
 * @version 1.0
 */
public record Station(String id, String name, String county, int elevation) {

    /** @return a description such as "Ashby Field (Morrow County, 1148 ft)" */
    public String describe() {
        return name + " (" + county + ", " + elevation + " ft)";
    }
}
