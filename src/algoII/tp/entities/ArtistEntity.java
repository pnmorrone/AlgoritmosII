package algoII.tp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Artist")
public class ArtistEntity
{
	@Column(name="name_artist")
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name=name;
	}


}
