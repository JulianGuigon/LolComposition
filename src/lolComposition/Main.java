package lolComposition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<Champion> l = new LinkedList<Champion>();
	public static List<String> l2 = new LinkedList<String>();

	public static void main(String[] args) {
		File f = new File("files/Champions.txt");
		try {
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String[] properties = s.nextLine().split(" ");
				Champion c = new Champion(properties[0]);
				for (int i = 1; i < properties.length; i++) {
					c.proprietes.add(properties[i]);
					if (!l2.contains(properties[i])) {
						l2.add(properties[i]);
					}
				}
				l.add(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Collections.sort(l2);
		for (String s : l2) {
			System.out.println(s);
		}

		Scanner sc = new Scanner(System.in);
		String answer = "";
		System.out.print("\nSearch : ");
		answer = sc.nextLine();
		while (!(answer.equals("exit") || answer.equals("quit") || answer.equals("q"))) {
			answer=answer.replace(" ", "");
			String[] criterias = answer.split(",");
			List<Champion> list = l;
			for (int i = 0; i < criterias.length; i++) {
				list = findChampionByProperty(criterias[i], list);
			}
			System.out.println("Résultat : ");
			for (Champion c : list) {
				System.out.println(c);
			}
			System.out.print("\nProperty : ");
			answer = sc.nextLine();

		}
	}

	public static List<Champion> findChampionByProperty(String property, List<Champion> champions) {
		List<Champion> sorted = new LinkedList<Champion>();
		for (Champion c : champions) {
			if (c.proprietes.contains(property)) {
				sorted.add(c);
			}
		}
		return sorted;
	}
}
