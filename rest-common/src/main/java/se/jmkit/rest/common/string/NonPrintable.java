package se.jmkit.rest.common.string;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The NonPrintable annotation tells the EmToStringConverter not to process the
 * decorated property.
 * 
 * This annotation is used to mark properties that reference back to a parent
 * object. For example, an Eaad has references to a collection of EaadBody
 * objects but also each EaadBody has a reference back to the Eaad. This would
 * otherwise cause a circular dependency problem.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NonPrintable {
}
