package algoII.tp.imple;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.xpath.internal.operations.Bool;

import algoII.tp.utils.FileUtils;
import algoII.tp.utils.PropertiesHelper;

public class MusicFile {
	
	private File file;
	private Set<String> array;
	
	public Set<String> getArray(){
		return this.array;
	}
	
	public void addStringToArray(String s){
		this.array.add(s);
	}
	
	
	public MusicFile(){
		this.file = new File(PropertiesHelper.getPathsFile());
		this.array = new HashSet<String>();
	}
	
	public File getFile() {
		return file;
	}
	
	public void createOrUpdateFile(){
		File x = new File(PropertiesHelper.getLibraryMusic());
		if(!this.file.exists()){
			this.readTree(x);
			this.createFile();
		}else{
			if(this.file.lastModified() < x.lastModified()){
				this.readTree(x);
				this.deleteFile();
				this.createFile();
			}
		}
	}
	
	public void createFile(){
		FileWriter fw;
		try{
			fw = new FileWriter(this.file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String string : array) {
				bw.write(string);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public void deleteFile(){
		this.file.delete();
	}
	
	public void readTree(File f){
		File arr[] = f.listFiles();
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				String lala = this.processFile(arr[i]);
				if (!lala.isEmpty()) {
					this.addStringToArray(lala);
				}
			}
		}
	}
	
	public String processFile(File f){
		String path = new String();
		if (f.isDirectory()) {
			this.readTree(f);
		}else if(FileUtils.isInfo(f)){
				path = f.getParent();
		}
		return path;
	}
	
	public void printTree(Collection<String> collection){		
		for (String path : collection) {
			System.out.println(path);
		}
	}
	
//	private Boolean isCookie(String lala){
//		return lala.contains("jmlcookies");		
//	}
	
	
}
