package utils;

public class Matrice {

	private float[][] m;
	
	public Matrice(int sizeH, int sizeW){
		this.m = new float[sizeH][sizeW];
	}
	
	public void addLigne(int l, float[] tab){
		m[l] = tab;
	}
	
}
