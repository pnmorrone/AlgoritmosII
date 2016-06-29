package algoII.tp.model;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Map;

import algoII.tp.utils.PropertiesHelper;

public class AlbumModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diskName;
	private ArrayList<String> artists;
	private String pathDisk;
	private String pathPicture;
	private ArrayList<FilterModel> filters;
	
	public AlbumModel(String name, String pathDisk, String pathPicture, ArrayList<String> array,ArrayList<FilterModel> filters) {
		this.artists = new ArrayList<>();
		this.artists.addAll(array);
		this.diskName = name;
		this.pathDisk = pathDisk;
		this.pathPicture = pathPicture;
		this.filters.addAll(filters);
		
	}
	
	public AlbumModel(){
		this.artists = new ArrayList<>();
		this.filters = new ArrayList<FilterModel>();
	}
	
	public String getDiskName(){
		return this.diskName;
	}
	
	public void setDiskName(String name){
		this.diskName = name;
	}
	
	public ArrayList<String> getArtist(){
		return this.artists;
	}
	
	public void addArtist(String name){
		String[] values = name.split(PropertiesHelper.delimiterModelInfo());
		for (String string : values) {
			this.artists.add(string);
		}
	}
	
	public String getPathDisk(){
		return this.pathDisk;
	}
	
	public void setPathDisk(String path){
		this.pathDisk = path;
	}
	
	public String getPathPicture(){
		return this.pathPicture;
	}
	
	public void setPathPicture(String path){
		this.pathPicture = path;
	}
	
	public void addFilter(FilterModel filter){
		this.filters.add(filter);
	}
	
	public ArrayList<FilterModel> getFilters(){
		return this.filters;
	}

}
