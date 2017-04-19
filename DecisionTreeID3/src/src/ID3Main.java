package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ID3Main {
	Node root;
	
	//Main Method
	public static void main(String[] args){
		List <List<String>> availableAttributes = new ArrayList<List<String>>();
		ArrayList<Instance> data = new ArrayList<Instance>();
		ArrayList<Metadata> metadata = new ArrayList<Metadata>();
		TreeToXml xml = new TreeToXml();
		//Get data from Data file and metadata(attributes and possible values)
		fetchData(data,metadata,"\\data\\car.data","\\data\\car.metadata");
		availableAttributes=metadata.get(0).getListAttributes();
		ID3Main dt = new ID3Main();
		//Start building the tree, tree is stored in object dt.root
		dt.learn(data, metadata,availableAttributes);
		//pass dt.root to method generateXmlFromNode to create XML
		xml.generateXmlFromNode(dt.root);
		System.out.println("Decision Tree Successfuly created");
		
	}
	
	
	
	public void learn(ArrayList<Instance> instances, ArrayList<Metadata> metadata, List<List<String>> availableAttributes) {
		this.root = generate(instances, metadata, availableAttributes);
	}
	
	private Node generate(ArrayList<Instance> instances, ArrayList<Metadata> metadata, List<List<String>> availableAttributes) {
		
		//Instantiate the tree
		Node root = new Node(null, instances);
		root.setEntropy(metadata.get(0).getClassVal());
		//build the tree
		expand(root, metadata,availableAttributes);
		return root;
	}

	private void expand(Node root, List<Metadata> meta, List<List<String>> availableAttributes) {
		if(((root.getEntropy()!=0.0)||(root.getEntropy()!=1.0))&&(availableAttributes.size()>=1) )//if 
		{
		List<Node> children=new ArrayList<Node>();
		List<Instance> nodeInstance = root.getInstances();
		double inforGain;
		double childEntropy;
		double maxGain = inforGain = childEntropy = 0.0;
		String maxGainAttribute = new String();
		
		//Create all the possible children dynamically
		for(int AttributeCounter=0; AttributeCounter<availableAttributes.size();AttributeCounter++){//Travel through all the attributes
			//Travel through the attribute values
			//First value is attribute name
			childEntropy = 0.0;
			for(int j=1,valueCount = 0; j<availableAttributes.get(AttributeCounter).size();j++,valueCount++){
				//for each attribute value create a child and add all the instances that belongs there
				Node child = new Node(root, new ArrayList<Instance>());
				child.setNodeLabels(availableAttributes.get(AttributeCounter).get(0));
				for(int k=0; k<nodeInstance.size();k++){//
						if(nodeInstance.get(k).getAttrVal().get(AttributeCounter).equals(availableAttributes.get(AttributeCounter).get(j))){
						child.addInstance(nodeInstance.get(k));	
						child.setParentValue(availableAttributes.get(AttributeCounter).get(j));
					}
				}
				child.setEntropy(meta.get(0).getClassVal());
				//Find the summation of weighted entropy of all
				childEntropy += child.getEntropy() * child.getInstances().size()/root.getInstances().size();
				children.add(child);
			}
			//Check attribute which provides maximum information gain
			inforGain = root.getEntropy() - childEntropy; 
			if(maxGain < inforGain){
				maxGain = inforGain;
				maxGainAttribute = availableAttributes.get(AttributeCounter).get(0);
				
			}
			
		}
		//Add children created by attribute having maximum information gain to root
		for(int i=0; i<children.size();i++)
		{
			if(children.get(i).instances.size()>=1)
			{
			if(children.get(i).getNodeLabels().equalsIgnoreCase(maxGainAttribute))
			{
				root.children.add(children.get(i));
			}
			}
		}
		//if entropy =0.0 set object label
		if(!maxGainAttribute.isEmpty())
		{
			root.setLabel(maxGainAttribute);
		}
		for(int i=0;i<root.getChildren().size();i++)
		{
			expand(root.getChildren().get(i), meta, availableAttributes);
		}
		
		return;
		}
		else 
		{	
			return;
		}
		//From the children remove the attribute which has been used up.
	}
	// Method to read data from training data file and metadata
	public static void fetchData(List<Instance> data, List<Metadata> metadata, String pathData, String pathMetadata){
		String filePath = new File("").getAbsolutePath();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath + pathData));
			String line;
			while ((line = br.readLine()) != null) {
				Instance ins = new Instance();
				ins.setInstance(line);
				data.add(ins);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath + pathMetadata));
			String line;
			Metadata tempMet = new Metadata();
			while ((line = br.readLine()) != null) {
				tempMet.addData(line);
			}
			metadata.add(tempMet);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
