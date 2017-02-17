package dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Tools;

public class Word {

	public List<String> listWord;
	
	public Word(){
		this.listWord = new ArrayList<String>();
	}
	
	public void getWord(){
		int i = 0;
		boolean start = false;
		boolean start2 = false;
		try {
			URL u = new URL(WordMain.domain + WordMain.url);
			URLConnection conn = u.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				//System.out.println(inputLine);
				if (inputLine.contains("bodyContent"))
					start = true;
				if (inputLine.contains(WordMain.url) && start){
					URL u2 = new URL(WordMain.domain + Tools.splitQuoteAndReturnContent(inputLine, WordMain.url));
					System.out.println("ICI test ");
					//System.out.println(inputLine);
					System.out.println(WordMain.domain + Tools.splitQuoteAndReturnContent(inputLine, WordMain.url));
					URLConnection conn2 = u2.openConnection();
					BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));

					String inputLine2;
					while ((inputLine2 = br2.readLine()) != null) {
						//System.out.println(inputLine2);
						if (inputLine2.contains("bodyContent"))
							start2 = true;
						if (start2){
							if (Tools.splitQuoteAndReturnBaliseContent(inputLine2,WordMain.balise) != "")
								listWord.add(Tools.splitQuoteAndReturnBaliseContent(inputLine2,WordMain.balise));			
//							System.out.println("ICI test V2 ");
//							System.out.println(Tools.splitQuoteAndReturnBaliseContent(inputLine2,WordMain.balise));
						}
						if (inputLine2.contains("</ul>"))
							break;
					}
					br2.close();
					start2 = false;
					i++;
					System.out.println("test " + i );
				}
				if (inputLine.contains("</ul>"))
					break;
			}
			br.close();

			System.out.println("Done");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showListWord(){
		System.out.println("List Word : ");
		for (int i = 0; i < this.listWord.size(); i++) {
			if (i % 9 == 0 && i !=0)
				System.out.println(listWord.get(i) + "\t");
			else
				System.out.print(listWord.get(i) + "\t");
		}
	}
	
	public void removeUpperCase(){
		for (int i = 0; i < listWord.size(); i++) {
			listWord.set(i, listWord.get(i).toLowerCase());
		}
	}
	
	public void removeDuplicate(){
		Set<String> hs = new HashSet<>();
		hs.addAll(this.listWord);
		System.out.println(listWord.size());
		this.listWord.clear();
		this.listWord.addAll(hs);
		System.out.println(listWord.size());
	}
}
