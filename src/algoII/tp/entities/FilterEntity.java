package algoII.tp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Filter")
public class FilterEntity
{
	@Id
	@Column(name="id_filter")
	@GeneratedValue(strategy=GenerationType.AUTO)

	private int id;

	@Column(name="name_filter")
	private String name;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="filter")
	private List<LabelEntity> labels;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="title_filter", joinColumns=@JoinColumn(name="id_filter") , inverseJoinColumns=@JoinColumn(name="id_title") )
	private List<TitleEntity> titles;

	
	
	public FilterEntity(){
		
	}
	
	
	public FilterEntity(String name)
	{
		this.name=name;
	}

	public FilterEntity(String name,List<TitleEntity> titles)
	{
		this.name=name;
		this.titles=titles;
	}

	public FilterEntity(String name,List<TitleEntity> titles,List<LabelEntity> labels)
	{
		this.name=name;
		this.titles=titles;
		this.labels=labels;
	}

	public List<TitleEntity> getTitles()
	{
		return titles;
	}

	public void setTitles(List<TitleEntity> titles)
	{
		this.titles=titles;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id=id;
	}

	public void setLabels(List<LabelEntity> labels)
	{
		this.labels=labels;
	}

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
