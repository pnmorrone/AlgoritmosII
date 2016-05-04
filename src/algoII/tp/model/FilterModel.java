package algoII.tp.model;

public class FilterModel {

	private String filterName;
	private String filterValue;

	public FilterModel(String filter, String value) {
		this.filterName = filter;
		this.filterValue = value;
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

}
