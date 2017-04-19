package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
	

public class Node {
	Node parent;//parent node
	List<Node> children = new ArrayList<Node>();//children nodes
	double entropy;
	List<Instance> instances = new ArrayList<Instance>();//instances present in the node
	private String label = new String();//if leaf then give label for the node
	private String nodeLabels = new String();//hold the detail of branch attribute and its label
	private String ParentValue=new String();
	private String classLabel = new String();
	
	
	public String getStringEntropy(){
		return String.valueOf(entropy);
	}
	
	public List<Node> getChildren() {
		return children;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNodeLabels() {
		return nodeLabels;
	}

	public void setNodeLabels(String nodeLabels) {
		this.nodeLabels = nodeLabels;
	}

	public String getParentValue() {
		return ParentValue;
	}

	public void setParentValue(String parentValue) {
		ParentValue = parentValue;
	}

	public String getClassLabel() {
		return classLabel;
	}

	public void setClassLabel(String classLabel) {
		this.classLabel = classLabel;
	}

	public void setChildren(Node children) {
		this.children.add(children);
	}

	public Node(Node  parent, ArrayList<Instance> instances) {
		// TODO Auto-generated constructor stub
		this.parent = parent;
		this.instances = instances;
	}
	
	public void removeChildren(int location)
	{
		this.children.remove(location);
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
		//this.parent=null;
	}

	public void addInstance(Instance ins){
		this.instances.add(ins);		
	}

	public List<Instance> getInstances() {
		// TODO Auto-generated method stub
		return this.instances;
	}
	
	public double getEntropy() {
		return entropy;
	}
	//Method to set entropy for any node using instances
	public void setEntropy(List<String> classVal) {
		double maxProbablity=0.0;
		int[] count = new int[classVal.size()];
		StringBuffer totalProbablity=new StringBuffer();
		double TotalProbablity=0.0;
		for(int j=0;j<classVal.size();j++){
			double probablity=0.0;
			for(int i = 0; i<this.instances.size();i++){
				if(this.instances.get(i).label.equals(classVal.get(j))){
					count[j]++;
				}	
			}
			if(count[j] != 0){
				probablity = (double)count[j]/this.instances.size();
				if(maxProbablity<=probablity)
				{
					this.label=classVal.get(j);
					maxProbablity=probablity;
				}
				this.classLabel=this.classLabel+" "+classVal.get(j)+":"+count[j];
				TotalProbablity = TotalProbablity+probablity;
			// Entropy(S) = -P1*LOGc(P1) -P2*LOGc(P2) -.....-Pn*LOGc(Pn); 
			//log has a base as C, no of classes
				this.entropy -= probablity * (Math.log(probablity)/Math.log(classVal.size()));
			}
			totalProbablity.append(probablity+"+");
		}
		
	}
	

		
}
