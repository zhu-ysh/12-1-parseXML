package parseXML;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import java.io.*;
import java.util.Iterator;

//Dom4j的解析
public class Dom4jXML {
	public static void main(String[] args) throws Exception {
		//1.创建SAXReader对象
		SAXReader sax = new SAXReader();
		//2.读取配置文件，返回文档对象
		Document document = sax.read(new File("src/applicationContext.xml"));
		//3.获取根元素
		Element root =  document.getRootElement();
		System.out.println("元素名:"+root.getName());
		System.out.println("属性个数:"+root.attributeCount());
		System.out.println("节点类型名"+root.getNodeTypeName());
		System.out.println("节点类型"+root.getNodeType());
		System.out.println("文本:"+root.getText());
/*
		//4.获取所有root子元素
		List<Element> eles = root.elements();
		//5.遍历子节点
		for (Element element : eles) {
			System.out.println("\t"+element.getName());
			//所有子节点属性
			List<Attribute> attrs = element.attributes();
			for (Attribute attribute : attrs) {
				System.out.println("\t\t"+attribute.getName()
				              +":"+attribute.getText());
			}
			//获取子节点
			List<Element> eles2 = element.elements();
			for (Element element2 : eles2) {
				System.out.println("\t\t\t"+element2.getName());
				List<Attribute> attrs2 = element2.attributes();
				for (Attribute attribute2 : attrs2) {
					System.out.println("\t\t\t\t"+
					attribute2.getName()+":"+
					attribute2.getText());
				}
			}
		}
				
*/		
		//使用迭代器遍历
		Iterator<Element> it =  root.elementIterator();
		while(it.hasNext()){
			Element ele = it.next();
			System.out.println("\t"+ele.getName());
			
			Iterator<Attribute> attrs = ele.attributeIterator();
			while(attrs.hasNext()){
				Attribute attr = attrs.next();
				System.out.println("\t\t"+attr.getName()+":"+attr.getText());
			}
		}
	}
}
