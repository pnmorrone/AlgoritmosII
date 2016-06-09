package algoII.tp.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;

import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Library;
import algoII.tp.def.Title;
import algoII.tp.entities.ArtistEntity;
import algoII.tp.entities.FilterEntity;
import algoII.tp.entities.LabelEntity;
import algoII.tp.entities.TitleEntity;
import algoII.tp.imple.LibraryImpleTrucha;
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
		// manager.searchMusic();
		DirectoryManager manager = new DirectoryManager();
		
		createBD(manager);
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
				// filterModel.getFilterName();//Filter
				// filterModel.getFilterValue();//Label
				String filterName=filterModel.getFilterName();
				filterEntity=filtersPersistance.stream().filter(x -> x.getName().equals(filterName)).findAny().orElse(null);

				if(filterEntity==null)
				{
					filterEntity=new FilterEntity(filterName,new ArrayList<TitleEntity>(),new ArrayList<LabelEntity>());
					title.getFilters().add(filterEntity);
					filtersPersistance.add(filterEntity);
				}

				LabelEntity labelEntity;
				////////////////
				String[] labels=filterModel.getFilterValue().split(";");
				for(String label:labels)
				{
					labelEntity=labelsPersistance.stream().filter(x -> x.getName().equals(label)).findAny().orElse(null);
					if(labelEntity==null)
					{
						labelEntity=new LabelEntity(label,filterEntity);
						labelsPersistance.add(labelEntity);
						filterEntity.getLabels().add(labelEntity);
					}
				}
				///////////////////
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

		// for(ArtistEntity artistEntity:artistsPersistance)
		// {
		// session.saveOrUpdate(artistEntity);
		//
		// }
		session.getTransaction().commit();
		//session.close();

	}

}
