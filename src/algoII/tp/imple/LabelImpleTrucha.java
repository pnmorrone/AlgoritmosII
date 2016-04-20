package algoII.tp.imple;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import algoII.tp.def.Label;
import algoII.tp.def.Title;


@Entity
@Table(name="Label")
public class LabelImpleTrucha implements Label
{
	
	
	
	private String name;
	
	private FilterImpleTrucha filter;
	
	@Override
	@ManyToOne
	@JoinColumn(name="name_filter")
	public FilterImpleTrucha getFilter()
	{
		return filter;
	}

	public void setFilter(FilterImpleTrucha filter)
	{
		this.filter=filter;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public LabelImpleTrucha(String name)
	{
		this.name = name;
	}

	@Id
	@Column(name="name_label")
	@Override
	public String getName()
	{
		return name;
	}
	
	@Transient
	@Override
	public List<Title> getTitles()
	{
		return null;
	}
	
	@Transient
	@Override
	public List<String> getSublabels()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
