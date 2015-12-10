// Type.java

import java.io.*;
import java.util.*;

public enum Type {
	/* Constants for the pokemon types */
	EARTH, FIRE, LEAF, WATER, FIGHTING, ELECTRIC, NONE;

	public static Type toType(String name) {
		if (name.equals(" ")) {
			return Type.NONE;
		}
		else {
			return Type.valueOf(name.toUpperCase());
		}
	}
}