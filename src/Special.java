// Special.java

import java.io.*;
import java.util.*;

public enum Special {
	/* Constants for special attacks */
	STUN, WILDCARD, WILDSTORM, DISABLE, RECHARGE, NONE;

	public static Special toSpec(String name) {
		name = name.replace(" ", "");  // For 2 word specials

		if (name.equals("")) {
			return Special.NONE;
		}
		else {
			return Special.valueOf(name.toUpperCase());
		}
	}
}