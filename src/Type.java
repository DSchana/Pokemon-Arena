// Dilpreet Chana
// Type.java
// Enum Type Type for Pokemon types

import java.io.*;
import java.util.*;

public enum Type {
	// Type constants
	EARTH,
	FIRE,
	LEAF,
	WATER,
	FIGHTING,
	ELECTRIC,
	NONE;

	public static Type toType(String name) {
		/*Convert string value tp Type tyep*/
		if (name.equals(" ")) {
			return Type.NONE;
		}
		else {
			return Type.valueOf(name.toUpperCase());
		}
	}
}