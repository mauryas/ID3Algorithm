package src;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TreeToXml{
	
	public boolean generateXmlFromNode(Node tree)
	{
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
	         // Create root element, create and set values of attributes
	         Element rootElement = doc.createElement("tree");
	         doc.appendChild(rootElement);
	         Attr attr = doc.createAttribute("entropy");
	         attr.setValue(tree.getStringEntropy());
	         Attr attr2 = doc.createAttribute("classes");
	         attr2.setValue(tree.getClassLabel());
	         rootElement.setAttributeNode(attr);
	         rootElement.setAttributeNode(attr2);
	         //call method to build tree using recursion
	         doc=addChildren(doc,tree,rootElement);
	         // write the content into xml file
	         TransformerFactory transformerFactory =
	         TransformerFactory.newInstance();
	         Transformer transformer =
	         transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         // Store xml in an output file
	         StreamResult result =
	         new StreamResult(new File("C:\\Users\\ShivamMaurya\\Documents\\WaknaBoys_cars.xml"));
	         transformer.transform(source, result);
	         // Output to console for testing
	         StreamResult consoleResult =
	         new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	         return false;
	      }
		return true;
	}
	// Method for adding child nodes and setting values of their attributes
	public Document addChildren(Document doc, Node tree, Element parentNode)
	{
		for(int i=0;i<tree.children.size();i++)
		{
			Node childNode=tree.children.get(i);
			if(tree.children.size()>0)
			{
				if(childNode.getEntropy()==0.0)
				{
					Element child = doc.createElement("node");
					parentNode.appendChild(child);
					Attr attr = doc.createAttribute("entropy");
					attr.setValue(childNode.getStringEntropy());
					Attr attr2 = doc.createAttribute("classes");
					attr2.setValue(childNode.getClassLabel());
					Attr attr3 = doc.createAttribute(tree.getLabel());
					attr3.setValue(childNode.getParentValue());
					child.setAttributeNode(attr);
					child.setAttributeNode(attr2);
					child.setAttributeNode(attr3);
					child.appendChild(doc.createTextNode(childNode.getLabel()));
				}
				else{
						Element child = doc.createElement("node");
						parentNode.appendChild(child);
						Attr attr = doc.createAttribute("entropy");
						attr.setValue(childNode.getStringEntropy());
						Attr attr2 = doc.createAttribute("classes");
						attr2.setValue(childNode.getClassLabel());
						Attr attr3 = doc.createAttribute(tree.getLabel());
						attr3.setValue(childNode.getParentValue());
						child.setAttributeNode(attr);
						child.setAttributeNode(attr2);
						child.setAttributeNode(attr3);
						doc=addChildren(doc, childNode, child);
				}
					
			}
		
		}
		return doc;
	}

}
