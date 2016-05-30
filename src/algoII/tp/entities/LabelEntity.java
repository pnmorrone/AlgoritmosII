package algoII.tp.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Label")
public class LabelEntity 
{
	
	private String name;
	
	private FilterEntity filter;
	
	@ManyToOne
	@JoinColumn(name="name_filter")
	public FilterEntity getFilter()
	{
		return filter;
	}

	public void setFilter(FilterEntity filter)
	{
		this.filter=filter;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public LabelEntity(String name)
	{
		this.name = name;
	}
	
	
	public LabelEntity(String name,FilterEntity filterEntity)
	{
		this.name = name;
		this.filter=filterEntity;
	}
	

	@Id
	@Column(name="name_label")
	public String getName()
	{
		return name;
	}
	


}
