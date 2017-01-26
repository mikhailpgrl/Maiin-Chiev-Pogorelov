package CLI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import utils.Tools;

public class Main {
	
	public static Path filePath;
	//find number of Nodes in files
	public final static CharSequence csNode ="Nodes:";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String namefile = "p2p-Gnutella08.txt";
		filePath = Paths.get(System.getProperty("user.dir") + "/src/files/" + namefile);
		List<String> list = Tools.readFiles(filePath);
		int nbNodes = Tools.readFilesNode(filePath);
//		int i = 0;
//		for (String string : list) {
//			System.out.println(string);
//			if (i++ == 10){
//				break;
//			}
//		}
		CLI cli = new CLI(nbNodes);
		cli.createCLI(list);
		
		System.out.println((float)1/10);
	}

}
