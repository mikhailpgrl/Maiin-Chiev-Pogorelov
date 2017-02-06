package CLI;

import java.util.ArrayList;
import java.util.List;

import utils.Tools;

public class PageRank {
	private CLI cli;
	private List<Float> pr;
	//vector
	private float[] x;
	// transpore
	private float[] y;
	
	public PageRank(CLI cli){
		this.cli = cli;
		this.pr = new ArrayList<Float>();
		this.x = new float[cli.getNbNodes()];
		this.y = new float[cli.getNbNodes()];
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
	
	public void initializeX(){
		this.x[0] = 1;
		if (x.length > 1)
			for (int i = 1; i < x.length; i++) {
				this.x[i] = 0;
			}
	}

	public void transposeProduct(){
		for (int i = 0; i < this.cli.getNbNodes(); i++) 
			y[i] = 0;
		for (int i = 0; i < this.cli.getNbNodes(); i++) {
			for (int j = this.cli.getL().get(i); j < this.cli.getL().get(i + 1); j++) {
				y[this.cli.getI().get(j)] += this.cli.getC().get(j) * x[i];
			}
		}
		Tools.copyFloatTable(y, x);
	}
	
	public void showTranspore(){
		System.out.println("Transpore");
		for (int i = 0; i < y.length; i++) {
			System.out.println(y[i]);
		}
	}
	
	public void directProduct(){
		for (int i = 0; i < this.cli.getNbNodes() - 1; i++) {
			y[i] = 0;
			for (int k = this.cli.getL().get(i); k < this.cli.getL().get(i + 1); k++) {
				y[i] += this.cli.getC().get(k) * x[this.cli.getI().get(i)];
			}
		}
	}
	public void showVector(){
		System.out.println("Vector");
		for (int i = 0; i < x.length; i++) {
			System.out.println(x[i]);
		}
	}
	public void sumVector(){
		float sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += y[i];
		}
		System.out.println(sum);
	}
	
}
