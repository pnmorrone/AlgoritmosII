package algoII.tp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<AlbumModel> array;
	
	public Album(){
		this.setArray(new ArrayList<AlbumModel>());
	}
	
	public Album(ArrayList<AlbumModel> am){
		this.setArray(am);
	}

	public ArrayList<AlbumModel> getArray() {
		return array;
	}

	public void setArray(ArrayList<AlbumModel> array) {
		this.array = array;
	}
	
	
	
}
