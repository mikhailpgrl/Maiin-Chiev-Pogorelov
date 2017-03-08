package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
	
	public static void removeUpperCase(List<String> list){
		for (int i = 0; i < list.size(); i++) {
			list.set(i, removeUpperCase(list.get(i)));
		}
	}

	public static String removeUpperCase(String temp) {
		// TODO Auto-generated method stub
		return temp.toLowerCase();
	}
	
	public static void removeAccents(List<String> list){
	//	String s;
		for (int i = 0; i < list.size(); i++) {
//			s = Normalizer.normalize(list.get(i), Normalizer.Form.NFD);
//		    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		    list.set(i, removeAccents(list.get(i)));
		}
	}
	
	public static String removeAccents(String s){
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		return temp.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		
	}
	
	public static void removeDuplicate(List<String> list){
		Set<String> hs = new HashSet<String>(list);
		//hs.addAll(list);
		//System.out.println(list.size());
		list.clear();
		list.addAll(hs);
		//System.out.println(list).size());
	}
	
	public static void removeQuote(List<String> list){
		String[] split;
		for (int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).contains("'")){
				split = list.get(i).split("'");
				if( split.length > 1)
					list.set(i, split[1]);
			}
		}
	}
	public static void removeACharacter(List<String> list){
		for (int i = list.size() - 1  ; i > 0 ; i--) {
			if (list.get(i).length() == 1){
				list.remove(i);
			}
		}
	}
	

	public static List<String> readFiles(Path fileName){
        List<String> listString = new ArrayList<String>();
        try {
            listString = Files.readAllLines(fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listString;
    }

	public static void removeEmptyWord(List<String> list, List<String> listWord) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < listWord.size(); j++) {
				if (list.get(i).equals(listWord.get(j))){
					listWord.remove(j);
					break;
				}
			}
		}
		
	}

	
//	public static int findWorldInList(List<String> list,String word){
//		Collections.sort(list);
//		
//		
//		
//		return -1;
//	}
//	
//	private int find(List<String> list,String word, int min , int max){
//		if (min == max)
//			if (!word.equals(word))
//				return -1;
//			else
//				return min;
//		int mid = ( max + min ) / 2;
//		
//		return max;
//		
//	}
	
}
