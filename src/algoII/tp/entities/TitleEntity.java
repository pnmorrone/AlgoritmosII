package algoII.tp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="Title")
public class TitleEntity 
{
	
	@Id
	@Column(name="name_title")
	private String name;
	
	@Column(name="path_title")
	private String path;

	@ManyToMany(cascade=CascadeType.ALL,mappedBy="titles")
	private List<ArtistEntity> artists;
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="titles")
	private List<FilterEntity> filters;
	
	
	public List<FilterEntity> getFilters()
	{
		return filters;
	}


	public void setFilters(List<FilterEntity> filters)
	{
		this.filters=filters;
	}


	public List<ArtistEntity> getArtists()
	{
		return artists;
	}

	
	public void setArtists(List<ArtistEntity> artists)
	{
		this.artists=artists;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path=path;
	}

	
	public TitleEntity(String name,String path,List<ArtistEntity> artists)
	{
		this.name=name;
		this.path=path;
		this.artists=artists;
	}
	
	public TitleEntity(String name,String path,List<ArtistEntity> artists,List<FilterEntity>filters )
	{
		this.name=name;
		this.path=path;
		this.artists=artists;
		this.filters=filters;
	}
	
	public TitleEntity(String name,String path){
		this.name=name;
		this.path=path;
	}
	
	
}
