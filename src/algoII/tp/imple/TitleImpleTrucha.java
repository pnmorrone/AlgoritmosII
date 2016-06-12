package algoII.tp.imple;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sun.javafx.collections.MappingChange.Map;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Title;
import algoII.tp.entities.FilterEntity;
import algoII.tp.entities.LabelEntity;
import algoII.tp.entities.TitleEntity;
import algoII.tp.utils.HibernateUtils;

public class TitleImpleTrucha implements Title
{

	private String name;
	private String path;
	
	public TitleImpleTrucha(String n,String p)
	{
		this.name=n;
		this.path=p;	
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public String getPath()
	{
		return this.path;
	}

	@Override
	public Hashtable<Filter,List<Label>> getAtts()
	{
		
		Session session = HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery(" select distinct F.name from TitleEntity T "
				+ "inner join T.filters F "
				+ "inner join F.labels L "
				+ "where T.name = "+"'"+this.name+"'");
		List <String> list = query.list();

		//aca adentro itero la lista de filters, creo un filter lo pongo en el map y hago una query para pedir los labels y lo pongo en el vvalue del map ...saludos....
		
		Hashtable<Filter,List<Label>> tabla=new Hashtable<>();
		List<Label>labels;
		
		for( String f: list){
			Filter filter = new FilterImpleTrucha(f);
			labels=new ArrayList<Label>();
			
			query = session.createQuery(" select L from TitleEntity T "
					+ "inner join T.filters F "
					+ "inner join F.labels L "
					+ "where T.name = "+"'"+this.name+"' "
					+ "and F.name = "+"'"+f+"' ");
			 List <LabelEntity> listOfLabels = query.list();			 	
			 for(LabelEntity labelEntity: listOfLabels){
				 Label label = new LabelImpleTrucha(labelEntity.getName()); 
				 labels.add(label);
			 }
			 
			 tabla.put(filter,labels);	 
		}
		
		return tabla;
	}

}
