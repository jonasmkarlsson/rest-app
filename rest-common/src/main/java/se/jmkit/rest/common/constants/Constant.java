package se.jmkit.rest.common.constants;

import java.text.SimpleDateFormat;

/**
 * Contains static final values.
 * 
 * @author jkarlsso
 * 
 */
public class Constant {

    public Constant() {
    }

    public static final String PERSON = "person";
    public static final String TEAM = "team";
    public static final String COLUMN_ID = "id";

    public final static int PRIME = 31;
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SSS");
}
