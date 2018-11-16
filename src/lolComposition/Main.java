package lolComposition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<Information> l = new LinkedList<Information>();
	public static List<String> l2 = new LinkedList<String>();

	public static void main(String[] args) {
		loadFile("files/Champions.txt");
		loadFile("files/Items.txt");
		loadFile("files/Runes.txt");
		loadFile("files/SortsInvocateurs.txt");

		System.out.println("--- Sources ---\nhttps://www.youtube.com/watch?v=aCY24lQ25VU\n\n--- Mots clés ---");
		int objectPerLine = 3;
		int compteur = 0;
		Collections.sort(l2);
		for (String s : l2) {
			if(compteur==objectPerLine) {
				System.out.println(s);
				compteur=0;
			}else {
				System.out.print(s+" ");
				compteur++;
			}
			
		}
		System.out.println("");

		Scanner sc = new Scanner(System.in);
		String answer = "";
		System.out.print("\n-------------------------------\nExit, Quit ou q pour Quitter.\nLa virgule sépare les différents mots clés\nRecherche : ");
		answer = sc.nextLine();
		while (!(answer.equals("exit") || answer.equals("quit") || answer.equals("q"))) {
			answer=answer.replace(" ", "");
			String[] criterias = answer.split(",");
			List<Information> list = l;
			for (int i = 0; i < criterias.length; i++) {
				list = findInformationByProperty(criterias[i].toLowerCase(), list);
			}
			System.out.println("Résultat : ");
			for (Information c : list) {
				System.out.println(c);
			}
			System.out.print("\nProperty : ");
			answer = sc.nextLine();

		}
	}

	public static void loadFile(String name) {
		try {
			File f = new File(name);
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String[] properties = s.nextLine().split(" ");
				Information c = new Information(properties[0]);
				for (int i = 1; i < properties.length; i++) {
					c.proprietes.add(properties[i].toLowerCase());
					if (!l2.contains(properties[i].toLowerCase())) {
						l2.add(properties[i].toLowerCase());
					}
				}
				l.add(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Information> findInformationByProperty(String property, List<Information> champions) {
		List<Information> sorted = new LinkedList<Information>();
		for (Information c : champions) {
			if (c.proprietes.contains(property)) {
				sorted.add(c);
			}
		}
		return sorted;
	}
}
