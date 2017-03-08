package collector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import utils.Node;
import utils.Page;

public class Collector {

	public List<Page> listPage;
	public List<Node> listNode;
	public static Path filePath;
	public static Path fileNodePath;
	
	public Collector(){
		this.listPage = new ArrayList<Page>();
		this.listNode = new ArrayList<Node>();
		filePath = Paths.get(System.getProperty("user.dir") + "/src/files/id-title.txt");
		fileNodePath = Paths.get(System.getProperty("user.dir") + "/src/files/graphe.txt");
	}
	
	public void initializeListPage(List<String> listFile){
		boolean balisePage = false;
		boolean first = true;
		String s;
		String title = null;
		int id = 0;
	//	List<String> listWord = new ArrayList<String>();
		for(int i = 0 ;  i < listFile.size() ; i++){
			if (listFile.get(i).contains("<page>")){
				balisePage = true;
			}
			if (balisePage && listFile.get(i).contains("<title>") && listFile.get(i).contains("</title>")){
				title = listFile.get(i);
				title = title.replace("<title>", "");
				title = title.replace("</title>", "");
				title = title.trim();
			}
			if (balisePage && first && listFile.get(i).contains("<id>") && listFile.get(i).contains("</id>")){
				s = listFile.get(i);
				s = s.replace("<id>", "");
				s = s.replace("</id>", "");
				s = s.trim();
				id = Integer.parseInt(s);
				first = false;
			}
//			
//			if (balisePage && listFile.get(i).contains("<text>") && listFile.get(i).contains("</text>")) {
//				listWord.clear();
//				listWord.addAll(findWord(listFile.get(i)));
//			}
			
			if (listFile.get(i).contains("</page>")){
				balisePage = false;
				first = true;
				listPage.add(new Page(title,id));
			}
		}
		
	}	
	public void initializeListNode(List<String> listFile){
		boolean balisePage = false;
		boolean first = true;
		String s;
		String title = null;
		int id = 0;
		List<String> listWord = new ArrayList<String>();
		for(int i = 0 ;  i < listFile.size() ; i++){
			if (listFile.get(i).contains("<page>")){
				balisePage = true;
			}

			if (balisePage && first && listFile.get(i).contains("<id>") && listFile.get(i).contains("</id>")){
				s = listFile.get(i);
				s = s.replace("<id>", "");
				s = s.replace("</id>", "");
				s = s.trim();
				id = Integer.parseInt(s);
				first = false;
			}
			if (balisePage && listFile.get(i).contains("<text>") && listFile.get(i).contains("</text>")) {
				listWord.clear();
				listWord.addAll(findWord(listFile.get(i)));
				
				for (int j = 0; j < listWord.size(); j++) {
					for (Page p : listPage) {
						if (p.getTitle().equals(listWord.get(j))){
//							System.out.print(id + "\t");
//							System.out.println(p.getId());
							listNode.add(new Node(id,p.getId()));
							break;
						}
					}
				}
			}
			
			if (listFile.get(i).contains("</page>")){
				balisePage = false;
				first = true;
				listPage.add(new Page(title,id));
			}
			
			
		}
		
	}
		
	public void showListNode(){
		for (int i = 0; i < listNode.size(); i++) {
			System.out.println(listNode.get(i).getPage() + "\t" + listNode.get(i).getNumberPage());
		}
	}

	private List<String> findWord(String s) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		boolean one = false;
		boolean two = false;
		int count = 0;
		String temp = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '['){
				if(!one)
					one = true;
				else
					if (two){
						count++;
						temp += s.charAt(i);
					}
					else
						two = true;
			}
			if (one && two){
				if (s.charAt(i) == '|'){
					list.add(temp);
					temp = "";
				}else
					temp += s.charAt(i);
			}
			
			if (s.charAt(i) == ']'){
				if (count > 0){
					count--;
					temp += s.charAt(i);
				}else{
					if(two)
						two = false;
					else{
						one = false;
						list.add(temp);
						temp = "";
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			if (temp.charAt(temp.length()-1) == ']'){
				temp = temp.substring(0, temp.length());
			}
			if (temp.charAt(0) == '['){
				temp = temp.substring(1, temp.length()-1);
			}
			list.set(i, temp);
		}
		return list;
	}

	public void deleteFile(){
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createFile() {
		String s = "";
		for (int j = 0; j < listPage.size(); j++) {
			System.out.println(j);
			s += listPage.get(j).getId() + "\t" + listPage.get(j).getTitle() + "\n";
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
	public void createNodeFile() {
		String s = "";

	    Comparator<Node> comparator = Comparator.comparing(node -> node.page);
	    comparator = comparator.thenComparing(Comparator.comparing(node -> node.numberPage));
	    listNode.sort(comparator);
		s += "# Nodes: " + listPage.get(listPage.size() - 1).getId() + " Edges: " + listNode.size() +"\n";
		for (int j = 0; j < listNode.size(); j++) {
			System.out.println(j);
			s += listNode.get(j).getPage() + "\t" + listNode.get(j).getNumberPage() + "\n";
		}
		byte[] inputBytes = s.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.wrap(inputBytes);
		FileChannel writeChannel;
		try {
			writeChannel = FileChannel.open(fileNodePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			writeChannel.write(writeBuffer);
			//int noOfBytesWritten = writeChannel.write(writeBuffer);

			writeChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public int listSize(){
		return listPage.size();
	}

	public void deleteNodeFile() {
		// TODO Auto-generated method stub
		try {
			Files.delete(fileNodePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
