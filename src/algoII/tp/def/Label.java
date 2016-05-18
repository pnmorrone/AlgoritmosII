package algoII.tp.def;

import java.util.List;

import algoII.tp.entities.FilterEntity;

public interface Label
{
	public String getName();
	public List<Title> getTitles();
	public List<String> getSublabels();
}
