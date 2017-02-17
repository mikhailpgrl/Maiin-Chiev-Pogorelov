package dictionary;

import utils.Tools;

public class Main {

	public static String domain;
	public static String url;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		domain = "https://fr.wiktionary.org";
		url = "/wiki/Wiktionnaire:10000-wp-fr";
		
		//Tools.splitQuoteAndReturnContent("","");
		Word word = new Word();
		word.getWord();
	}

}
