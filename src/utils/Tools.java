package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import CLI.Main;

public class Tools {
	public static List<String> readFiles(Path fileName){
        List<String> listString = new ArrayList<String>();
        try {
            listString = Files.readAllLines(fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (listString.size() != 0 && isntNumber(listString.get(0))){
        	listString.remove(0);
        }
        return listString;
    }
	
	public static int readFilesNode(Path fileName){
        List<String> listString = new ArrayList<String>();
        int node = 0;
        CharSequence cs = Main.csNode;
        try {
            listString = Files.readAllLines(fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        for (int i = 0; i < listString.size(); i++) {
			if (listString.get(i).contains(cs)){
				String[] split = listString.get(i).split(" ");
				for (int j = 0; j < split.length; j++) {
					if (split[j].equals(cs)){
						try {
							node = Integer.parseInt(split[j+1]);
						} catch (IndexOutOfBoundsException e) {
							// TODO: handle exception
							System.err.println("Error : (Tools) ReadFilesNode Out Of Bounds");
							node = 0;
						}
						break;
					}
				}
				break;
			}
		}
        return node;
    }
	
	public static boolean isntNumber(String c){
		try {
			Integer.parseInt(c.substring(0,1));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.err.println(e);
			return true;
		}
		return false;
	}
	/**
	 *  Copy t1 in t2
	 * @param t1
	 * @param t2
	 */
	public static void copyFloatTable(float[] t1, float[] t2){
		for (int i = 0; i < t2.length; i++) {
			t2[i] = t1[i];
		}
	}

	/**
	 *  Reverse t1 and t2
	 * @param t1
	 * @param t2
	 */
	public static void reverseFloatTable(float[] t1, float[] t2){
		float[] temp = new float[t1.length];
		for (int i = 0; i < t2.length; i++) {
			temp[i] = t2[i];
			t2[i] = t1[i];
			t1[i] = temp[i];
		
		}
	}
	
}