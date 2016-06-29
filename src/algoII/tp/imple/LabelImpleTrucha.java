package algoII.tp.imple;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import algoII.tp.def.Label;
import algoII.tp.def.Title;
import algoII.tp.entities.LabelEntity;
import algoII.tp.entities.TitleEntity;
import algoII.tp.utils.HibernateUtils;

public class LabelImpleTrucha implements Label
{
	private String name;

	public LabelImpleTrucha(String name)
	{
		this.name=name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public List<Title> getTitles()
	{
		Session session=HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();

		// me devuelve una lista de objetos, las dos persistentes
		// select * from title
		// inner join title_filter on title.id_title=title_filter.id_title
		// innrt join filter on title_filter.id_filter=filter.id_filter
		// Query query2=session.createQuery("from TitleEntity as T INNER JOIN
		// T.filters");

		// me devuelve una lista de TitleEntity's
		// select title.name_title from title
		// inner join title_filter on title.id_title=title_filter.id_title
		// inner join filter on title_filter.id_filter=filter.id_filter;
		// Query query2=session.createQuery("select T from TitleEntity as T
		// INNER JOIN T.filters");

		Query query=session.createQuery("select T from TitleEntity as T INNER JOIN T.filters as F "+"INNER JOIN F.labels as L WHERE L.name = "+"'"+this.name+"'");
		List<TitleEntity> list=query.list();
		List<Title> titles=null;
		if(list!=null&&!list.isEmpty())
		{
			titles=new ArrayList<Title>();
		}

		for(TitleEntity f:list)
		{
			Title title=new TitleImpleTrucha(f.getName(),f.getPath());
			titles.add(title);
		}
		return titles;
	}

	@Override
	public List<String> getSublabels()
	{
		Session session=HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("select distinct SL.name from LabelEntity as L INNER JOIN L.labels as SL WHERE L.name = "+"'"+this.name+"'");		
		List<String> sublabels=query.list();
//		List <String> list=new ArrayList<String>();
//		for(LabelEntity sublabel:sublabels)
//		{
//			list.add(sublabel.getName());
//		}
		return sublabels;	
	}

}
