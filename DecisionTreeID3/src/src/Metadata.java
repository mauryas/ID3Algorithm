package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Metadata {
	List<String> classVal = new ArrayList<String>();
	List<List<String>> attrWithValue = new ArrayList<List<String>>();
		
	//getter and setter
	public List<String> getClassVal() {
		return classVal;
	}

	public List<List<String>> getAttrWithValue() {
		return attrWithValue;
	}
	
	public List<List<String>> getListAttributes() {
		return attrWithValue;
	}
	
	public void addData(String line) {
			String[] clsOrattr = line.split(":");
			String[] values = clsOrattr[1].split(",");
			if(clsOrattr[0].equals("class")){
				for(int i = 0; i<values.length;i++){
					this.classVal.add(values[i].trim());
				}
				
			} else if (!clsOrattr[0].equals("class")){
				this.attrWithValue.add(new ArrayList<String>(1));
				this.attrWithValue.get(this.attrWithValue.size()-1).add(clsOrattr[0]);
				for(int i =0;i<values.length;i++){
					this.attrWithValue.get(this.attrWithValue.size()-1).add(values[i].trim());
				}
			}	
		
	}
	
	

}
