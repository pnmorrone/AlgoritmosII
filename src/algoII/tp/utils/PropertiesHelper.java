package algoII.tp.utils;

public class PropertiesHelper {

	static PropertiesManager prop = new PropertiesManager();
	private static final String pathKey = "path-albumsFile";
	private static final String libraryMusic = "music-library-path";
	private static final String delimiterKey = "delimiter"; 
	private static final String separatorKey = "separator";
	private static final String filtersKey = "filters";
	private static final String littleImageKey = "littleImage";
	private static final String imageDefaultKey = "imageNameDefault";
	private static final String sublabelsKey = "sublabels";
	private static final String modelPathKey = "model-path";
	
	
	public static String getValueForKey(String key){
		return prop.getPropValues(key);
	}
	
	public static String getPathsFile(){
		return prop.getPropValues(pathKey);
	}
	
	public static String getLibraryMusic(){
		return prop.getPropValues(libraryMusic);
	}
	
	public static String delimiterModelInfo(){
		return prop.getPropValues(delimiterKey);
	}
	
	public static String separatorKeyValue(){
		return prop.getPropValues(separatorKey);
	}
	
	public static String littleImageName(){
		return prop.getPropValues(littleImageKey);
	}
	
	public static String imageDefaultName(){
		return prop.getPropValues(imageDefaultKey);
	}
	
	public static String[] filters(){
		String filter = prop.getPropValues(filtersKey);
		String[] filters = filter.split(delimiterModelInfo());
		return filters;
	}
	
	public static Boolean isFilter(String value){
		for (String filter : filters()) {
			if (filter.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public static String[] subLabels(){
		String sl = prop.getPropValues(sublabelsKey);
		String[] sublabels = sl.split(delimiterModelInfo());
		return sublabels;
	}
	
	public static String getModelPath(){
		return prop.getPropValues(modelPathKey);
	}
}
