package algoII.tp.entities;

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
public class FilterEntity 
{

	private String name;
	
	private List<LabelEntity> labels;
	
	
	public FilterEntity(String n)
	{
		name = n;
	}

	@Id
	@Column(name="name_filter")
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name=name;
	}

	public void setLabels(List<LabelEntity> labels)
	{
		this.labels=labels;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="filter")
	public List<LabelEntity> getLabels()
	{	
		return this.labels;
	}

	public void addLabel(LabelEntity label)
	{
		label.setFilter(this);
		this.labels.add(label);
	}
	
	
}
