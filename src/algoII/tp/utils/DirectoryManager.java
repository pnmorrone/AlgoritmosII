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
		String[] filterskey = PropertiesHelper.filters();
		
		for (String filter : filterskey) {
			if (dic.get(filter) != null) {
				if(!this.isLabelComposed(dic.get(filter))){
					album.addFilter(this.returnLabelOrsSublabel(filter, dic.get(filter)));
				}else{
					ArrayList<FilterModel> afm = this.procesarFiltro(dic, filter);
					for (FilterModel filterModel : afm) {
						album.addFilter(filterModel);
					}
				}
			}
		}
	}
	
	private Boolean isSubLabel(String value){
		String[] sl = PropertiesHelper.subLabels();
		Boolean isSubLabel = false;
		for (String s : sl) {
			if (value.equals(s)) {
				isSubLabel = true;
			}
		}
		return isSubLabel;
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
				if(!filterModel.getSubLabel().isEmpty()){
					System.out.println(
								"El filtro: " + filterModel.getFilterName() + " con label : " + filterModel.getFilterValue() + " y sublabel:  " + filterModel.getSubLabel());	
				}
				else{
					System.out.println(
							"El filtro: " + filterModel.getFilterName() + " con label : " + filterModel.getFilterValue());
				}
				
				
			}
			System.out.println("-----------FIN FILTROS-----------");
			System.out.println("--------------FIN DISCO--------------------");
		}
	}
	
	private Boolean isLabelComposed(String value){
		return value.split(PropertiesHelper.delimiterModelInfo()).length > 1;
	}
	
	private FilterModel returnLabelOrsSublabel(String filter, String label){
		if(this.isSubLabel(label)){
			return new FilterModel(label);
		}else{
			return new FilterModel(filter,label);
		}
	}
	
	private ArrayList<FilterModel> procesarFiltro(Map<String, String> dic, String filter){
		ArrayList<FilterModel> afm = new ArrayList<FilterModel>();
		for (String f : dic.get(filter).split(PropertiesHelper.delimiterModelInfo())) {
			afm.add(this.returnLabelOrsSublabel(filter, f));
		}
		return afm;
	}
	
	
}
