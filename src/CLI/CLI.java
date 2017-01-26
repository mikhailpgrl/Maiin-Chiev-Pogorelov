package CLI;

import java.util.ArrayList;
import java.util.List;

import utils.Node;

public class CLI {

	private List<Float> c;
	private List<Integer> l;
	private List<Integer> i;
	private int nbNodes;
	
	public CLI(int nbNodes){
		this.c = new ArrayList<Float>();
		this.l = new ArrayList<Integer>();
		this.i = new ArrayList<Integer>();
		this.nbNodes = nbNodes;
	}
	
	public void createCLI(List<String> listString){
		List<Node> listNode = new ArrayList<Node>();
		String[] split = listString.get(0).split("\t");
		Node n = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		Node n2; 
		int lastNode = 0;
		listNode.add(n);
		initializeL(n.getStart());
		
		for (int i = 1 ; i < listString.size(); i++){
			split = listString.get(i).split("\t");
			n2 = new Node(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
			if (n2.getStart() == n.getStart())
				listNode.add(n2);
			else {
				addCLI(listNode,lastNode);
				lastNode = n.getStart();
				n = n2;
				listNode.add(n);
			}
		}
		addCLI(listNode,lastNode);
		finalizeL(n.getStart());
	}
	private void finalizeL(int edge){
		int addVal = l.get(l.size()-1);
		for (int i = edge; i < nbNodes - 1; i++) {
			this.l.add(addVal);
		}
//		for (int i = 0; i < l.size() ; i++) {
//			if (i % 10 == 0 && i != 0){
//				System.out.println( l.get(i));
//			}else
//				System.out.print(l.get(i) +"\t");
//		}
//		System.out.println("Nodes: " + this.nbNodes + "\t liste: " + this.l.size());
	}
	private void initializeL(int start) {
		// TODO Auto-generated method stub
		if (start != 0){
			for (int i = 0; i < start; i++) {
				this.l.add(0);
			}
		}else
			this.l.add(0);
	}

	public void addC(List<Node> listNode){
		for(int j = 0 ; j < listNode.size(); j++){
			this.c.add((float) 1 / listNode.size());
		}
	}
	
	public void addL(List<Node> listNode,int lastNode){
		int lastStart = l.get(l.size() - 1);
		int currentStart = listNode.get(0).getStart();
		int addVal = lastStart + listNode.size();
		for (int i = lastNode; i < currentStart - 1 ; i++) {
			this.l.add(lastStart);
		}
		this.l.add(addVal);
	}
	
	public void addI(List<Node> listNode){
		for(int j = 0 ; j < listNode.size(); j++){
			this.i.add(listNode.get(j).getEnd());
		}
	}
	
	public void addCLI(List<Node> listNode,int lastNode){
		addC(listNode);
		addL(listNode,lastNode);
		addI(listNode);
		listNode.clear();
	}
	
	public void showC(){
		System.out.println("\nC :");
		for (int i = 0; i < c.size(); i++) {
			if (i % 10 == 0 && i != 0){
				System.out.println( c.get(i));
			}else
				System.out.print(c.get(i) +"\t");
			
		}
	}
	public void showL(){
		System.out.println("\nL :");
		for (int i = 0; i < l.size(); i++) {
			if (i % 10 == 0 && i != 0){
				System.out.println( l.get(i));
			}else
				System.out.print(l.get(i) +"\t");
			
		}
	}
	public void showI(){
		System.out.println("\nI :");
		for (int i = 0; i < this.i.size(); i++) {
			if (i % 10 == 0 && i != 0){
				System.out.println( this.i.get(i));
			}else
				System.out.print(this.i.get(i) +"\t");
			
		}
	}
	public void showCLI(){
		showC();
		showL();
		showI();
	}
	
}
