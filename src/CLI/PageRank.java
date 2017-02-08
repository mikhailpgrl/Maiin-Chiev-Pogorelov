package CLI;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import utils.Tools;

public class PageRank {
	public static Path fileVectorPath;
	private CLI cli;
//	private List<Float> pr;
	// old vector
	private float[] x;
	// new vector
	private float[] y;
	private double norm;
	private int step;
	
	public PageRank(CLI cli){
		fileVectorPath = Paths.get(System.getProperty("user.dir") + "/src/files/Vector.txt");
		this.cli = cli;
//		this.pr = new ArrayList<Float>();
		this.x = new float[cli.getNbNodes()];
		this.y = new float[cli.getNbNodes()];
		this.step = 0;
	}
	public double getNorm() {
		return norm;
	}

	public void pageRankZero(){
		initializeXPageRank0();
		this.norm = Main.epsilone + 1;
		deleteFile();
		while(this.norm > Main.epsilone){
			showStep();
			transposeProduct();
			showX();
			sumX();
			System.out.println("Calcul Norm");
			calculeNorm();
			showNorm();
			createFile(0);
			incStep();
		}
	
	}
	
	public void initializeXPageRank0(){
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
		Tools.reverseFloatTable(y, x);
	}
	public void addZapInX(){
		for (int i = 0; i < x.length; i++) {
			x[i] = (x[i] * (1 - Main.zap)) + (Main.zap / cli.getNbNodes());
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
	
	public void sumY(){
		float sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += y[i];
		}
		System.out.println("Sum : " + sum);
	}	
	public void sumX(){
		float sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i];
		}
		System.out.println("Sum : " + sum);
	}
	public void calculeNorm(){
		double somme = 0; 
		for (int i = 0; i < x.length; i++) {
			somme += Math.pow(x[i] - y[i], 2);
		}
		System.out.println(somme);
		norm = Math.sqrt(somme);
		
	}
	public void showX(){
		System.out.println("X :");
		for (int i = 0; i < x.length; i++) {
			if (i % 9 == 0 && i !=0)
				System.out.println(x[i] );
			else
				System.out.print(x[i]+ "\t");
		}
		System.out.println();
	}
	public void showY(){
		System.out.println("Y :");
		for (int i = 0; i < y.length; i++) {
			if (i % 9 == 0 && i !=0)
				System.out.println(y[i]);
			else 
				System.out.print(y[i]+ "\t");
		}
		System.out.println();
	}
	public void showNorm(){
		System.out.println("Norm : " +  norm);
	}
	
	public void showStep(){
		System.out.println("Step : " + step);
	}
	public void incStep(){
		step++;
	}
	public void initializeXPageRankZap(){
		for (int i = 0; i < x.length; i++) {
			x[i] = 1 / cli.getNbNodes();
		}
	}
	
	public void pageRankZap(){
		initializeXPageRankZap();
		this.norm = Main.epsilone + 1;
		deleteFile();
		while(this.norm > Main.epsilone){
			showStep();
			transposeProduct();
			addZapInX();
			showX();
			sumX();
			System.out.println("Calcul Norm");
			calculeNorm();
			showNorm();
			createFile(1);
			incStep();
		}
	}
	public void deleteFile(){
		try {
			Files.delete(fileVectorPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createFile(int operation) {
		String s = "";
		if (operation == 0)
			s= "PageRankZero : \n";
		else 
			s= "PageRankZap : \n";
		s += "Step : " + this.step + "\n";
		for (int i = 0; i < x.length; i++) {
			if (i % 9 == 0 && i !=0)
				s += x[i] + "\n";
			else
				s += x[i] + "\t";
		}
		s += "\n";
		byte[] inputBytes = s.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.wrap(inputBytes);
		FileChannel writeChannel;
		try {
			writeChannel = FileChannel.open(fileVectorPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			writeChannel.write(writeBuffer);
			//int noOfBytesWritten = writeChannel.write(writeBuffer);

			writeChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
