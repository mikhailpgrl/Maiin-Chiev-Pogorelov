package CLI;

import java.util.ArrayList;
import java.util.List;

public class PageRank {
	private CLI cli;
	private List<Float> pr;
	
	public PageRank(CLI cli){
		this.cli = cli;
		this.pr = new ArrayList<Float>();
	}
	
	public void pageRankZero(){
		pr.add((float) 1);
		for (int i = 1; i < cli.getNbNodes(); i++) {
			pr.add((float) 0);
		}
	}
	
	public void showPageRankZero(){
		System.out.println("Page rank Zero");
		for (int i = 0; i < pr.size(); i++) {
			System.out.println(pr.get(i));
		}
	}
}
