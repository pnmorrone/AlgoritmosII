package algoII.tp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

	public static String getExtensionFile(File f) {
		String name = f.getName();
		int extensionIndex = name.lastIndexOf(".");
		String extension = name.substring(extensionIndex + 1);
		return extension;
	}

	public static Boolean isImage(File f) {
		if (getExtensionFile(f).equals("jpg")) {
			return true;
		}
		return false;
	}

	public static Boolean isLittleImage(File f) {
		if (f.getName().equals(PropertiesHelper.littleImageName())){
			return true;
		}
		return false;
	}

	public static Boolean isInfo(File f) {
		if (getExtensionFile(f).equals("jml")) {
			return true;
		}
		return false;
	}

	public static ArrayList<String> readStringFile(File f) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			FileReader inputFile = new FileReader(f);
			BufferedReader buffer = new BufferedReader(inputFile);

			String line;
			while ((line = buffer.readLine()) != null) {
				list.add(line);
			}
			buffer.close();
		} catch (Exception e) {
			System.out.println("Error while reading file paths line by line:" + e.getMessage());
		}
		return list;
	}

	public static Map<String, String> readFile(File f) {
		Map<String, String> dictionary = new HashMap<String, String>();

		try {
			FileReader inputFile = new FileReader(f);
			BufferedReader bufferReader = new BufferedReader(inputFile);

			String line;
			while ((line = bufferReader.readLine()) != null) {

				String[] strings = line.split(PropertiesHelper.separatorKeyValue());

				if (!line.isEmpty() && strings.length > 1) {
					String key = strings[0];
					String value = strings[1];
					dictionary.put(key, value);
				}
			}

			bufferReader.close();
		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}

		return dictionary;
	}

}
