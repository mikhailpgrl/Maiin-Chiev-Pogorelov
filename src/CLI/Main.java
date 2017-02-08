package CLI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import utils.Tools;

public class Main {
	
	public static Path filePath;
	//find number of Nodes in files
	public final static CharSequence csNode ="Nodes:";
	//path of the PageRank File
	public static Path filePathPageRank;
	public static float epsilone = (float) 0.0001;
	public static float zap = (float) 0.1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String namefile = "p2p-Gnutella08.txt";
		//String namefile = "test.txt";
		filePath = Paths.get(System.getProperty("user.dir") + "/src/files/" + namefile);
		List<String> list = Tools.readFiles(filePath);
		int nbNodes = Tools.readFilesNode(filePath);
		CLI cli = new CLI(nbNodes);
		cli.createCLI(list);
		cli.showCLI();
		PageRank pr = new PageRank(cli);
		while(true){
			System.out.println("0 for PageRank0, 1 to PageRank-zip or 2 to stop");
			Scanner scan = new Scanner(System.in);
			String address = scan.nextLine();
			switch (address) {
			case "0":
				pr.pageRankZero();
				break;
			case "1":
				pr.pageRankZap();
				break;
			case "2":
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

}
