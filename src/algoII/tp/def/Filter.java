package algoII.tp.def;

import java.util.List;

import algoII.tp.imple.LabelImpleTrucha;

public interface Filter
{
	public String getName();
	public List<LabelImpleTrucha> getLabels();
	public void addLabel(LabelImpleTrucha label);
}
