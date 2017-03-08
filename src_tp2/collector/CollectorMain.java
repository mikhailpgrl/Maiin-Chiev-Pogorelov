package collector;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import utils.Tools;

public class CollectorMain {
	public static Path filePath;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String namefile = "frwiki-20151226-pages-articles.xml";
		//String namefile = "frwiki-latest-stub-articles1.xml";
		String namefile = "test.xml";
		filePath = Paths.get(System.getProperty("user.dir") + "/src/files/" + namefile);
		List<String> list = Tools.readFiles(filePath);
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		Collector r = new Collector();
		r.initializeListPage(list);
		System.out.println(r.listSize());
		r.deleteFile();
		r.createFile();
		r.initializeListNode(list);
		r.showListNode();
		r.deleteNodeFile();
		r.createNodeFile();
		
	}

}
