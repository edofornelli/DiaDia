package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

public class ComparatorePerDirezione implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
