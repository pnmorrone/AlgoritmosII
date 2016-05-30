package algoII.tp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Artist")
public class ArtistEntity
{	@Id
	@Column(name="name_artist")
	private String name;


	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="title_artist",joinColumns=@JoinColumn(name="name_artist"),inverseJoinColumns=@JoinColumn(name="name_title"))
	private List<TitleEntity> titles;
	
	
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
	
	public ArtistEntity(String name,List<TitleEntity>titles)
	{
		this.name=name;
		this.titles=titles;
	}
	public ArtistEntity(String name)
	{
		this.name=name;
	}


}
