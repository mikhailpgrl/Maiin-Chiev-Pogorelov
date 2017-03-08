package utils;

import java.util.ArrayList;
import java.util.List;

public class PageWord {

	
	public int id;
	public String word;
	public List<Integer> listPage;
	
	public PageWord(int id , String word){
		this.id = id;
		this.word = word;
		this.listPage = new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Integer> getListPage() {
		return listPage;
	}

	public void setListPage(List<Integer> listPage) {
		this.listPage = listPage;
	}
	
	public void addList(int i){
		this.listPage.add(i);
	}
	
	public int getListPageAtPosition(int i ){
		return listPage.get(i);
	}
	
}
