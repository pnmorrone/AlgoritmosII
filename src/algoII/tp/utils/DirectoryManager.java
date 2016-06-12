package algoII.tp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algoII.tp.imple.MusicFile;
import algoII.tp.model.AlbumModel;
import algoII.tp.model.FilterModel;

public class DirectoryManager {

	public ArrayList<AlbumModel> searchMusic() {
		
		this.createOrUpdateFile();
		
		File f = new File(PropertiesHelper.getPathsFile());
		ArrayList<String> paths = FileUtils.readStringFile(f);
		ArrayList<AlbumModel> albumList = new ArrayList<AlbumModel>();

		for (String path : paths) {
			albumList.add(this.readPath(path));
		}
		this.print(albumList);
		
		// Descomentar para ver como imprimir
		// for (AlbumModel albumModel : albumList) {
		// System.out.println("Los discos listados son: " +
		// albumModel.getDiskName());
		// ArrayList<FilterModel> filters = albumModel.getFilters();
		// for (FilterModel filterModel : filters) {
		// System.out.println("Los filtros son: " + filterModel.getFilterName()
		// + " " + filterModel.getFilterValue());
		// }
		// }

		return albumList;
	}

	public void createOrUpdateFile(){
		MusicFile mf = new MusicFile();
		mf.createOrUpdateFile();
		mf.printTree(mf.getArray());
	}
	
	private AlbumModel readPath(String path) {

		File f = new File(path);
		File files[] = f.listFiles();
		AlbumModel album = new AlbumModel();
		for (File file : files) {

			if (FileUtils.isLittleImage(file)) {
				album.setPathPicture(file.getAbsolutePath());
			} else if (FileUtils.isInfo(file)) {
				this.procesarInfo(file, album);
			}
		}
		return album;
	}

	private void procesarInfo(File f, AlbumModel album) {
		Map<String, String> dic = new HashMap<String, String>();
		dic.putAll(FileUtils.readFile(f));
		album.setPathDisk(f.getParent());
		album.setDiskName(dic.get("Tittle"));
		album.addArtist(dic.get("Artist"));
		 addFiltersModel(album, dic);
	}

	private void addFiltersModel(AlbumModel album, Map<String, String> dic) {
		String[] filtersKey = PropertiesHelper.filters();
		for (String string : filtersKey) {
			if (dic.get(string) != null) {
				FilterModel filter = new FilterModel(string, dic.get(string));
				album.addFilter(filter);
			}
		}
	}

	public void print(ArrayList<AlbumModel> albums) {
		for (AlbumModel albumModel : albums) {
			System.out.println("-----------------DISCO--------------------");
			System.out.println("Los discos listados son: " + albumModel.getDiskName());
			ArrayList<String> artists = albumModel.getArtist();
			for (String artist : artists) {
				System.out.println("Los artistas: " + artist);
			}
			System.out.println("El path del disco es: " + albumModel.getPathDisk());
			System.out.println("El path de la foto: " + albumModel.getPathPicture());
			ArrayList<FilterModel> filters = albumModel.getFilters();
			System.out.println("-------------FILTROS-------------");
			for (FilterModel filterModel : filters) {
				System.out.println(
						"Los filtros son: " + filterModel.getFilterName() + " " + filterModel.getFilterValue());
			}
			System.out.println("-----------FIN FILTROS-----------");
			System.out.println("--------------FIN DISCO--------------------");
		}
	}

}
