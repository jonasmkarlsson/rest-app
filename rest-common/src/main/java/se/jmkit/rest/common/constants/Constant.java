package se.jmkit.rest.common.constants;

import java.text.SimpleDateFormat;

/**
 * Contains static final values.
 * 
 * @author jkarlsso
 * 
 */
public class Constant {

    private Constant() {
    }

    public static final String TABLE_PERSON = "person";
    public static final String TABLE_TEAM = "team";
    public static final String COLUMN_ID = "id";

    public static final int PRIME = 31;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SSS");
}
