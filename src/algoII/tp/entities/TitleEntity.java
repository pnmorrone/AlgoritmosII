package algoII.tp.entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Title;


@Entity
@Table(name="Title")
public class TitleEntity 
{
	
	@Id
	@Column(name="name_title")
	private String name;
	
	@Column(name="path_title")
	private String path;

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

}
