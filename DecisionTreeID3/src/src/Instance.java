package src;

import java.util.ArrayList;
import java.util.List;

public class Instance {
	String label;
	List<String> attrVal = new ArrayList<String>();
	

	//Add the values into the list of data.
	public void setInstance(String instanceString){
		String[] temp = instanceString.split(",");
		this.label = temp[temp.length - 1].trim();
		for(int i = 0; i<temp.length-1;i++){
			this.attrVal.add(temp[i].trim());
		}
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<String> getAttrVal() {
		return attrVal;
	}
	public void setAttrVal(List<String> attrVal) {
		this.attrVal = attrVal;
	}
}
