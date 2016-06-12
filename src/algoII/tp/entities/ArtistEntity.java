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
import javax.persistence.Table;

@Entity
@Table(name="Artist")
public class ArtistEntity
{

	@Id
	@Column(name="id_artist")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="name_artist")
	private String name;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id=id;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="title_artist", joinColumns=@JoinColumn(name="id_artist") , inverseJoinColumns=@JoinColumn(name="id_title") )
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

	public ArtistEntity(String name,List<TitleEntity> titles)
	{
		this.name=name;
		this.titles=titles;
	}

	public ArtistEntity(String name)
	{
		this.name=name;
	}

}
