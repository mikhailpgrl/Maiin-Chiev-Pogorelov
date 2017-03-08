package dictionary;

import java.nio.file.Path;
import java.nio.file.Paths;

import utils.Tools;

public class WordMain {

	public static String domain;
	public static String url;
	public static String balise;
	public static Path emptyFile; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String namefile = "EmptyWord.txt";
		emptyFile = Paths.get(System.getProperty("user.dir") + "/src/files/" + namefile);
		
		domain = "https://fr.wiktionary.org";
		url = "/wiki/Wiktionnaire:10000-wp-fr";
		balise = " title=";
		
		
		//Tools.splitQuoteAndReturnContent("","");
		Word word = new Word();
		word.getWord();
		word.removeUpperCase();
		word.removeAccents();
		word.removeDuplicate();
		word.removeUniqueCharactere();
		word.removeEmptyWord(Tools.readFiles(emptyFile));
		word.sort();
		word.removeQuote();
		word.removeQuote();
		word.removeQuote();
		word.removeQuote();
		//word.showListWord();
		word.showListWordOneLine();
		//Tools.testSplit("", balise);
		
		word.createFileWord();
	}

}
