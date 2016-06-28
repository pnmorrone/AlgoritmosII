package algoII.tp.model;

public class FilterModel {

	private String filterName; //Genre
	private String filterValue; //Internacional
	private String sublabels; // Brasil
	

	public FilterModel(String filter, String value, String sublabel) {
		this.filterName = filter;
		this.filterValue = value;
		this.sublabels = sublabel;
	}
	
	public FilterModel(String filter, String value) {
		this.filterName = filter;
		this.filterValue = value;
		this.sublabels = "";
	}
	
	public FilterModel(String sublabel){
		this.filterName = "Genre";
		this.filterValue = "Musica del Mundo";
		this.sublabels = sublabel;
	}
	
	public String getFilterName() {
		return this.filterName;
	}

	public void setFilterName(String filter) {
		this.filterName = filter;
	}

	public String getFilterValue() {
		return this.filterValue;
	}

	public void setFilterValue(String value) {
		this.filterValue = value;
	}
	
	public String getSubLabel(){
		return this.sublabels;
	}
	
	public void setSubLabel(String value){
		this.sublabels = value;
	}

}
