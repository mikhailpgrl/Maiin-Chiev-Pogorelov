package dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.Tools;

public class Main {

	public static String domain;
	public static String url;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		domain = "https://fr.wiktionary.org";
		url = "/wiki/Wiktionnaire:10000-wp-fr";
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("b");
		list.add("b");
		list.add("b");
		list.add("œuvre");
		list.add("œuvre");
		list.add("œuvre");
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("a");
		set.add("a");
		set.add("b");
		set.add("b");
		set.add("b");
		set.add("œuvre");
		set.add("œuvre");
		set.add("œuvre");
		
		System.out.println(set.size());
		list.clear();
		list.addAll(set);
//		Tools.removeDuplicate(list);
//		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println(list.size());
		
		
		
	}

}
