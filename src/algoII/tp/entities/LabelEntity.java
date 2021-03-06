package algoII.tp.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="Label")
public class LabelEntity 
{
	private int id;
	
	private String name;
	
	private FilterEntity filter;
	
	@Id
	@Column(name="id_label")
	@GeneratedValue(strategy=GenerationType.AUTO)

	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	@Column(name="name_label")
	public String getName()
	{
		return name;
	}
	
	
	@ManyToOne
	@JoinColumn(name="id_filter")
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

	
	public LabelEntity()
	{
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
	
	

	private LabelEntity label;


	
	private Set<LabelEntity> labels = new HashSet<LabelEntity>();

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="superlabel_id")
	public LabelEntity getLabel()
	{
		return label;
	}

	public void setLabel(LabelEntity label)
	{
		this.label=label;
	}
	
	@OneToMany(mappedBy="label")
	public Set<LabelEntity> getLabels()
	{
		return labels;
	}

	public void setLabels(Set<LabelEntity> labels)
	{
		this.labels=labels;
	}


	


}
