Project- To build a program to create decision tree on Car Data using ID3 algorithm.
____________________________________________________________________________________________________________

Classes:
____________________________________________________________________________________________________________
	1> ID3Main.java: Its the main class containing main method. 
It also contains method void expand(Node root, List<Metadata> meta, List<List<String>> availableAttributes) to build the tree.

Main method first reads and stores the data(instances) and metadata in objects and use expand method to build the tree. 
It then passes the tree object(dt.root) which creates a decision tree. After that it calls method generateXmlFromNode(Node tree) 
to create xml object and save it in a xml file.

Entropy(S) = -P1*LOGc(P1) -P2*LOGc(P2) -.....-Pn*LOGc(Pn)
log has a base as C, no of classes

Information Gain = Entropy(S) - Summation((Sv/S)*Entropy(child))
Sv - total no. tuples for a particular value of an attribute.
S - Total no. tuple in the Parent node

At every node, Information gain is calculated for available attributes and attribute having maximum Information gain is selected.Using recersion, 
this process is repeated for all child nodes untill entropy on the node is 0.0.  

	2> Instance.java: class to store training data

	3> Metadata.java: class to store metadata, which includes all the attributes and corresponding values. 

We have metadata and instance(tuples) in diffrent files, So that with minimum changes this code will be able to handle datasets other than car data. 
Throughout the project we have taken care to write a program in a way that decision tree should use these matadata and instances only to be able to 
build a decision tree. 

	4> Node.java: Node class containing attributes and child nodes. It also contain method setEntropy(List<String> classVal) to calculate entropy of given node.

	5> TreeToXml.java: We are using DOM parser to create xml object and write it in a file. Output file path can be provided at line no. 43.

Setting up of code: 
____________________________________________________________________________________________________________
Java Version: 1.8 
Eclipse Version: Kepler(4.3 or higher)

1. Copy all files contained in the zip-Archive to a new directory in your Eclipse workspace, e.g. "workspace/DecisionTreeID3". 


2. Create a new Java-Project in Eclipse that has the same name as the directory you just created, e.g. "DecisionTreeID3".

3. Make sure that all *.jar files contained in the "lib" directory are listed as "referenced libraries". 
   If the are not listed as referenced libraries, open the project's properties, and add each jar file to the "build path".


4. Eclipse should be able to compile ID3Main.java. You can now run/start programming.


5. In order to start program in Eclipse, you first need to provide valid path for output file(Please find below details**).

 
   Finally, create a new RunConfiguration called 
"Java-Application" and add the class "src.ID3Main" as main class.
   Press "Run" and your program should start.


**The file path is needed to create the XML file. The user should provide a valid path while running the 
ID3Main.java main method and he should have create, write and read access.
	- TreeToXml.java	Line No: 43 	Method: generateXmlFromNode(Node tree)
	- Path path = Paths.get("C:\\Users\\<<User Name>>\\Documents\\WaknaBoys_cars.xml");

Result:
____________________________________________________________________________________________________________
The leaf nodes of the decision trees are having zero errors and represent only one class value. So, if the data set used for training represents all the real life data values correctly then our model is built correctly or else the decision 
tree built is overfitting the training data set.




