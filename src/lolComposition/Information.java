package lolComposition;

import java.util.LinkedList;
import java.util.List;

public class Information {
	public String nom;
	public List<String> proprietes;
	
	public Information(String nom) {
		this.nom = nom;
		this.proprietes = new LinkedList<String>();
	}

	@Override
	public String toString() {
		return nom + ", " + proprietes;
	}
}
