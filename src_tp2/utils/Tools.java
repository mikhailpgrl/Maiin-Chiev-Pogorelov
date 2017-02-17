package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public static String splitQuoteAndReturnContent(String word, String sequence) {
		//System.out.println("test");
		//String str = "Location \"Welcome  to india\" Bangalore " + "Channai \"IT city\"  Mysore";

		//word = "<a href=\"/wiki/Wiktionnaire:10000-wp-fr-1000\" title=\"Wiktionnaire:10000-wp-fr-1000\">Wiktionnaire:10000-wp-fr-1000</a>";
//		List<String> list = new ArrayList<String>();
////		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(word);
////		while (m.find())
////			list.add(m.group(1)/*.replace("\"","")*/); 
//
//		System.out.println(list);
//		for (String string : list) {
//			//if (string.contains(sequence)){
//				System.out.println(string + "\t  STIRNG LALALAL");
////				return string;
////			}
//			//System.out.println(string);
//		}
		
		String[] split = word.split("\"");
		for (int i = 0; i < split.length; i++) {
			//System.out.println(split[i]);
			if (split[i].contains(sequence)){
				return split[i];
			}
		}
		
		return "";
	}

	public static String splitQuoteAndReturnBaliseContent(String word, String balise) {
		//System.out.println("test");
		//String str = "Location \"Welcome  to india\" Bangalore " + "Channai \"IT city\"  Mysore";
//
//		List<String> list = new ArrayList<String>();
//		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(word);
//		while (m.find())
//			list.add(m.group(1).replace("\"","")); 

//		System.out.println(list);
		
		String[] split = word.split("\"");
		for (int i = 0; i < split.length; i++) {
			//System.out.println(split[i]);
			if (split[i].contains(balise)){
			//	System.out.println(split[i+1]);
				return split[i+1].replace(" (page inexistante)","");
			}
		}
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).contains(balise)){
//				System.out.println(list.get(i) + " \t " + " test ici");
//				return list.get(i+1);
//			}
//		}
		return "";
	}

	public static String testSplit(String word, String balise) {
	word = "<a href=\"/w/index.php?title=l%27anneau&amp;action=edit&amp;redlink=1\" class=\"new\" title=\"l'anneau (page inexistante)\">l'anneau</a>";
	String[] split = word.split("\"");
	for (int i = 0; i < split.length; i++) {
		System.out.println(split[i]);
//		if (split[i].contains(balise)){
//			System.out.println(split[i+1]);
//			return split[i+1];
//		}
	}
	return balise;
	}
}
