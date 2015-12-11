// Special.java

import java.io.*;
import java.util.*;

public enum Special {
	// Special constants
	STUN,
	WILDCARD,
	WILDSTORM,
	DISABLE,
	RECHARGE,
	NONE;

	public static Special toSpec(String name) {
		/*Convert String value to Special type*/
		name = name.replace(" ", "");

		if (name.equals("")) {
			return Special.NONE;
		}
		else {
			return Special.valueOf(name.toUpperCase());
		}
	}
}