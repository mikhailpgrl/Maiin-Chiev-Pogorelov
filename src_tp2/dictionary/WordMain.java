package dictionary;

import utils.Tools;

public class WordMain {

	public static String domain;
	public static String url;
	public static String balise;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		domain = "https://fr.wiktionary.org";
		url = "/wiki/Wiktionnaire:10000-wp-fr";
		balise = " title=";
		
		//Tools.splitQuoteAndReturnContent("","");
		Word word = new Word();
		word.getWord();
		word.removeDuplicate();
		word.removeUpperCase();
		word.showListWord();
		//Tools.testSplit("", balise);
	}

}
