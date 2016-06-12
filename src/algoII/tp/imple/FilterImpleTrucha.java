package algoII.tp.imple;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.utils.HibernateUtils;

public class FilterImpleTrucha implements Filter
{
	private String name;
	
	public FilterImpleTrucha(String n)
	{
		name = n;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public List<Label> getLabels()
	{
		Session session = HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session
				.createQuery("select distinct L.name from FilterEntity F "
						+ "inner join F.labels L where F.name = "
						+ "'"+this.name+"'");

		List<String> list= query.list();
		List <Label>labels=null;
		if(list!=null &&!list.isEmpty()){
			labels = new ArrayList<Label>(); 
		}
		for (String name :list){
			Label label = new LabelImpleTrucha(name);
			labels.add(label);
		}
		return labels;		
	}

}
