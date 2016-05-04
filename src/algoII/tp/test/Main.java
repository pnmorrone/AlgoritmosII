package algoII.tp.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.imple.DirectoryManager;
import algoII.tp.imple.FilterImpleTrucha;
import algoII.tp.imple.LabelImpleTrucha;
import algoII.tp.imple.LibraryImpleTrucha;
import algoII.tp.utils.HibernateUtils;

public class Main
{

	
	static String PATH="C:\\Users\\Niko\\UTN Algoritmos avanzados\\ALBUMS";

	public static void main(String[] args)
	
	{
		DirectoryManager manager = new DirectoryManager();
		manager.searchMusic();
		
			
//		Session session=HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
//		session.beginTransaction();
//		
//		
//		/*TEST */
//		FilterImpleTrucha filter2= new FilterImpleTrucha("Mundo");
//		filter2.setLabels(new ArrayList<LabelImpleTrucha>());
//		filter2.addLabel(new LabelImpleTrucha("Brasil"));
//		filter2.addLabel(new LabelImpleTrucha("Spain"));
//		filter2.addLabel(new LabelImpleTrucha("England"));
//		filter2.addLabel(new LabelImpleTrucha("Mexico"));
//		
//		FilterImpleTrucha filter= new FilterImpleTrucha("Genero");
//		filter.setLabels(new ArrayList<LabelImpleTrucha>());
//		filter.addLabel(new LabelImpleTrucha("Jazz"));
//		filter.addLabel(new LabelImpleTrucha("Rock"));
//		filter.addLabel(new LabelImpleTrucha("Pop"));
//		filter.addLabel(new LabelImpleTrucha("Reggae"));
//		session.save(filter);
//		session.save(filter2);
//		
//		
//		
//		/*TEST (FALTA PERSISTIR TODAS LAS CLASES)*/
////		Artist artist1=new Artist("Joaquin Sabina",new ArrayList<Title>());
////		Artist artist2=new Artist("Calamaro",new ArrayList<Title>());
////		Artist artist3=new Artist("Pity Alvarez",new ArrayList<Title>());
////
////		Title title1 =new Title("Album1",new ArrayList<Artist>());		
////		Title title2 =new Title("Album2",new ArrayList<Artist>());
////
////		title1.getArtists().add(artist1);
////		title1.getArtists().add(artist2);
////		title2.getArtists().add(artist2);
////		title2.getArtists().add(artist3);
////		artist1.getTitles().add(title1);
////		artist2.getTitles().add(title1);
////		artist2.getTitles().add(title2);
////		artist3.getTitles().add(title2);
////
////		session.save(title1);
////		session.save(title2);
////		session.save(artist1);
////		session.save(artist2);
////		session.save(artist3);
//		
//		session.getTransaction().commit();
//		session.close();


	}

}
