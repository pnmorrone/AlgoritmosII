package algoII.tp.imple;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import algoII.tp.def.Filter;


@Entity
@Table(name="Filter")
public class FilterImpleTrucha implements Filter
{

	private String name;
	
	private List<LabelImpleTrucha> labels;
	
	
	public FilterImpleTrucha(String n)
	{
		name = n;
	}

	@Id
	@Column(name="name_filter")
	@Override
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name=name;
	}

	public void setLabels(List<LabelImpleTrucha> labels)
	{
		this.labels=labels;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="filter")
	@Override
	public List<LabelImpleTrucha> getLabels()
	{	
		return this.labels;
	}

	@Override
	public void addLabel(LabelImpleTrucha label)
	{
		label.setFilter(this);
		this.labels.add(label);
	}
	
	
}
