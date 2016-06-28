package algoII.tp.test;

import java.io.File;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;
import algoII.tp.entities.ArtistEntity;
import algoII.tp.entities.FilterEntity;
import algoII.tp.entities.LabelEntity;
import algoII.tp.entities.TitleEntity;
import algoII.tp.imple.FilterImpleTrucha;
import algoII.tp.imple.LabelImpleTrucha;
import algoII.tp.imple.LibraryImpleTrucha;
import algoII.tp.imple.TitleImpleTrucha;
import algoII.tp.imple.MusicFile;
import algoII.tp.model.AlbumModel;
import algoII.tp.model.FilterModel;
import algoII.tp.utils.DirectoryManager;
import algoII.tp.utils.HibernateUtils;
import algoII.tp.utils.PropertiesHelper;

public class Main
{

	static String PATH="C:\\Users\\Niko\\UTN Algoritmos avanzados\\ALBUMS";

	public static void main(String[] args)

	{

		// Mostramos toda la info para guardar en la base de datos
		// DirectoryManager manager = new DirectoryManager();
		DirectoryManager manager = new DirectoryManager();
		createBD(manager);
		LabelImpleTrucha x =new LabelImpleTrucha("Jazz");
		List <Title> v=x.getTitles();
		for (Title t : v){
			System.out.println("name:"+t.getName()+ "    path:"+t.getPath());
		}
		
		System.out.println("------------------");
		LibraryImpleTrucha b= new LibraryImpleTrucha();
		 List <Title> l2=b.getTitles();
		
		for (Title t : l2){
			System.out.println("name:"+t.getName()+ "    path:"+t.getPath());
		}
		
		List <Filter> l3=b.getFilters() ;
		
		for (Filter f : l3){			
			System.out.println("|||||||||||||||");
			System.out.println("name of filter:  "+f.getName());
			List<Label> lab=f.getLabels();
			for(Label l:lab){
				System.out.println(l.getName());
			}
			
			
		}
		
		System.out.println(b.getFilter("Genre").getName());
		System.out.println(b.getFilter("Instrument").getName());
		System.out.println(b.getFilter("Year"));
		
		System.out.println("******************************");
		for(Label l:b.getLabels(new FilterImpleTrucha("Genre"))){
			System.out.println(l.getName());
		}
		
		System.out.println("******************************");
		for(Label l:b.getLabels(new FilterImpleTrucha("Instrument"))){
			System.out.println(l.getName());

		}
		
		System.out.println("******************************");
		for(Label l:b.getLabels(new FilterImpleTrucha("Year"))){
			System.out.println(l.getName());

		}
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Blues").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Bolero & Latin").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Bossa Nova").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Brasil").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Cuba").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Espa�a").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Jazz").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Genre"),"Rock").getName());
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(b.getLabel(new FilterImpleTrucha("Instrument"),"Guitar").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Instrument"),"Piano").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Instrument"),"Saxophone").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Instrument"),"Vibraphone").getName());
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(b.getLabel(new FilterImpleTrucha("Year")," 1971").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Year"),"1957").getName());
		System.out.println(b.getLabel(new FilterImpleTrucha("Year"),"1963").getName());

		System.out.println("...................................");
		
		List <Title> titles=b.getTitles(new FilterImpleTrucha("Genre"),new LabelImpleTrucha("Jazz"));
		System.out.println("All albums of jazz");
		for(Title t : titles){
			System.out.println(t.getName());
		}
		
		
		Title t = new TitleImpleTrucha("Esencia Romantica","C:/Users/Niko/UTN Algoritmos avanzados/ALBUMS/Trio Los Panchos/Esencia Romantica");
		t.getAtts();
		Hashtable<Filter,List<Label>> prueba=t.getAtts();		
		Set<Filter> keys=prueba.keySet();
		for(Filter f : keys){
			System.out.println("filter key: " + f.getName());
			List <Label> z=prueba.get(f);
			for (Label l:z ){
				System.out.println("label value:"+l.getName());
			}

		}
		
	}

	public static void createBD(DirectoryManager manager)
	{
		ArrayList<AlbumModel> albums = manager.searchMusic();

		List<ArtistEntity> artistsPersistance=new ArrayList<ArtistEntity>();
		List<TitleEntity> titlesPersistance=new ArrayList<TitleEntity>();
		List<FilterEntity> filtersPersistance=new ArrayList<FilterEntity>();
		List<LabelEntity> labelsPersistance=new ArrayList<LabelEntity>();

		for(AlbumModel albumModel:albums)
		{

			TitleEntity title=new TitleEntity(albumModel.getDiskName(),albumModel.getPathDisk(),new ArrayList<ArtistEntity>(),new ArrayList<FilterEntity>());
			List<String> namesOfArtists=albumModel.getArtist();
			List<FilterModel> filterOfAlbum=albumModel.getFilters();

			ArtistEntity artist;

			for(String artistName:namesOfArtists)
			{
				artist=artistsPersistance.stream().filter(x -> x.getName().equals(artistName)).findAny().orElse(null);
				if(artist==null)
				{
					artist=new ArtistEntity(artistName,new ArrayList<TitleEntity>());
					title.getArtists().add(artist);
					artistsPersistance.add(artist);
				}
				artist.getTitles().add(title);
			}

			FilterEntity filterEntity;

			for(FilterModel filterModel:filterOfAlbum)
			{

				String filterName=filterModel.getFilterName();

					filterEntity=new FilterEntity(filterName,new ArrayList<TitleEntity>(),new ArrayList<LabelEntity>());
					title.getFilters().add(filterEntity);
	

				LabelEntity labelEntity;

				String[] labels=filterModel.getFilterValue().split(";");
				for(String label:labels)
				{

						labelEntity=new LabelEntity(label,filterEntity);
						labelsPersistance.add(labelEntity);
						filterEntity.getLabels().add(labelEntity);
				
				}
		
				filterEntity.getTitles().add(title);
			}

			titlesPersistance.add(title);

		}

		Session session=HibernateUtils.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		// IMPORTANTE EL ARTISTA JOHN LENON TIENE UNA CARPETA QUE NO ES UN ALBUM
		// Se la tiene que sacar del archivo que esta en el path
		for(TitleEntity titleEntity:titlesPersistance)
		{
			session.saveOrUpdate(titleEntity);
		}
		session.getTransaction().commit();

	}
}


