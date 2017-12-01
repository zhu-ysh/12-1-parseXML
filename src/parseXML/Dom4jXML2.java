package parseXML;
import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jXML2 {
	
	public void parseXML(Element element){
		if(null!=element){
			List<Element> eles = element.elements();
			for (Element element2 : eles) {
				System.out.println(element2.getName()+":"+element2.getText());
				List<Attribute> attrs = element2.attributes();
				for (Attribute attr : attrs) {
					System.out.println("\t"+attr.getName()+":"+attr.getText());
				}
				//递归
				parseXML(element2);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		SAXReader sax = new SAXReader();
		Document doc = sax.read(new File("src/class.xml"));
		Element root = doc.getRootElement();
		Dom4jXML2 dom  = new Dom4jXML2();
		dom.parseXML(root);
	}
}
