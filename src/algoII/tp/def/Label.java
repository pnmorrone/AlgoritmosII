package algoII.tp.def;

import java.util.List;


import algoII.tp.imple.FilterImpleTrucha;

public interface Label
{
	public String getName();
	public List<Title> getTitles();
	public List<String> getSublabels();
	public FilterImpleTrucha getFilter();
}
