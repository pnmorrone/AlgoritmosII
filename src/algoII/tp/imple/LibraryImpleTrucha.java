package algoII.tp.imple;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.sun.istack.internal.Pool.Impl;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;
import algoII.tp.entities.FilterEntity;
import algoII.tp.entities.LabelEntity;
import algoII.tp.entities.TitleEntity;
import algoII.tp.utils.HibernateUtils;

public class LibraryImpleTrucha implements Library
{

	@Override
	public List<Title> getTitles()
	{
		Session session=HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();		
		Query query=session.createQuery("from TitleEntity");
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
	public List<Filter> getFilters()
	{
		Session session = HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("select distinct f.name from FilterEntity f");
		List<String> list= query.list();
		List <Filter>filters=null;
		if(list!=null &&!list.isEmpty()){
			filters = new ArrayList<Filter>();
		}
		for (String name :list){
			Filter filter = new FilterImpleTrucha( name);
			filters.add(filter);
		}
		return filters;
	}

	@Override
	public Filter getFilter(String filtername)
	{
		Session session = HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(" from FilterEntity f where f.name = " +"'"+filtername+"'");
		FilterEntity filterEntitiy =(FilterEntity) query.setMaxResults(1).uniqueResult();
		Filter filter = null;
		if (filterEntitiy!=null){
			filter=new FilterImpleTrucha(filterEntitiy.getName());
		}
		return filter;
	}

	@Override
	public List<Label> getLabels(Filter f)
	{
		Session session = HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(" select distinct L.name from LabelEntity L "
				+ "inner join L.filter F where F.name = " +"'"+f.getName()+"'"
				+" and "+"L.label is null");
		List <String> labelEntities= query.list();
		List <Label> labels=null;
		if(labelEntities!=null && !labelEntities.isEmpty()){			
			labels=new ArrayList<Label>();
		}
		
		for(String labelEntity :labelEntities ){
			labels.add(new LabelImpleTrucha(labelEntity));
		}
				
		return labels;
	}

	@Override
	public Label getLabel(Filter f, String labelname)
	{
		List <Label> labels= f.getLabels();
		Label label= labels.stream().filter(l -> l.getName().equals(labelname)).findAny().orElse(null);
		return label;
	}

	@Override
	public List<Title> getTitles(Filter f, Label lb)
	{

		Session session = HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select T from TitleEntity T "
				+ "inner join T.filters F "
				+ "inner join F.labels L "
				+ "where L.name = "+"'"+lb.getName()+"'");
		
		List <TitleEntity> titleEntities= query.list();
		
		List <Title> titles=null;
		
		if(titleEntities!=null && !titleEntities.isEmpty()){			
			titles=new ArrayList<Title>();
		}
		
		for(TitleEntity titleEntity :titleEntities ){
			titles.add(new TitleImpleTrucha(titleEntity.getName(),titleEntity.getPath()));
		}
				
		return titles;
	}

}
