package dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Tools;

public class Word {

	public static Path filePath;
	public List<String> listWord;
	
	public Word(){
		filePath = Paths.get(System.getProperty("user.dir") + "/src/files/Word.txt");
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
	
	public void showListWordOneLine(){
		for(int i = 0 ; i < this.listWord.size() ; i++){
			System.out.println(listWord.get(i));
		}
	}
	
	public void removeUpperCase(){
		Tools.removeUpperCase(listWord);
	}
	
	public void removeAccents(){
		Tools.removeAccents(listWord);
	}
	public void sort(){
		Collections.sort(listWord);
	}
	
	public void removeDuplicate(){
		Tools.removeDuplicate(listWord);
	}
	
	public void removeQuote(){
		Tools.removeQuote(listWord);
	}
	 
	public void removeUniqueCharactere(){
		Tools.removeACharacter(listWord);
	}

	public void removeEmptyWord(List<String> readFiles) {
		// TODO Auto-generated method stub
		Tools.removeEmptyWord(readFiles,listWord);
	}
	public void createFileWord(){
		String s = "";
		for (int i = 0; i < listWord.size(); i++) {
				s += listWord.get(i) + "\n";
		}
		byte[] inputBytes = s.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.wrap(inputBytes);
		FileChannel writeChannel;
		try {
			writeChannel = FileChannel.open(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			writeChannel.write(writeBuffer);
			//int noOfBytesWritten = writeChannel.write(writeBuffer);

			writeChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}
