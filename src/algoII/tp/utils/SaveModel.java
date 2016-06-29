package algoII.tp.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

import algoII.tp.model.Album;
import algoII.tp.model.AlbumModel;

public class SaveModel {

	private Album a;
	private File f;
	
	public SaveModel(Album a){
		this.a = a;
		this.f = new File(PropertiesHelper.getModelPath());
	}
	
	public SaveModel(){
		this.a = null;
	}
	
	public void serializeModel(){
		
		try{
			this.f.delete();
			FileOutputStream fout = new FileOutputStream(PropertiesHelper.getModelPath());
			ObjectOutputStream oos = new ObjectOutputStream(fout);  
			oos.writeObject(this.a);
			oos.close();
			   
		 }
		 catch(Exception ex){
			   ex.printStackTrace();
		 }
	 }
	
	public Album deserializeModel(){
		Album a = new Album();
		try {
		    FileInputStream streamIn = new FileInputStream(PropertiesHelper.getModelPath());
		    ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		    a = (Album) objectinputstream.readObject();
		    objectinputstream.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return a;
	}
	
}
