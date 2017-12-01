package reflectTest;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class FileApplicationContext {
    private File file;

    FileApplicationContext(String fileName){
        file = new File(fileName);
    }
    public Object getBean(String beanId) throws Exception {
        Element findElement = null;
        //判断文件是否存在
        if(file.exists()){
            //1.创建解析对象
            SAXReader sax = new SAXReader();
            //2.获取文档对象
            Document document = sax.read(file);
            //3.获取根节点beans
            Element root = document.getRootElement();
            //4.获取子节点
            List<Element> elements = root.elements();
            label:
            for(Element element : elements){
                List<Attribute> attributes = element.attributes();
                for(Attribute attribute : attributes){
                    if (attribute.getName().equals("id") && attribute.getText().equals(beanId)){
                        findElement = element;
                        break label;
                    }
                }
            }
            if(findElement!=null){
                //找到此对象的class
                Attribute attr = findElement.attribute("class");
                //拿到属性值，映射为真类型
                Class objClass = Class.forName(attr.getText());
                //创建对象:类类型使用newInstance()创建对象，调用类的无参构造
                Object obj = objClass.newInstance();
                //得到所有property子节点
                List<Element> properties = findElement.elements();
                for(Element element:properties){
                    Attribute nameAttri = element.attribute("name");
                    Attribute valueAttri = element.attribute("value");
                    String methodName = "set"+(nameAttri.getText().charAt(0)+"").toUpperCase()+nameAttri.getText().substring(1);
                    //得到属性，后面用到属性的类型
                    Field f = objClass.getDeclaredField(nameAttri.getText());
                    //类类型.getMethod("方法名",Class[]参数类型数组);
                    Method m = objClass.getMethod(methodName,new Class[]{f.getType()});
                    //设置参数把字符串转成相应的类型
                    Class classType = f.getType();
                    if (classType.equals(String.class)) {
                        m.invoke(obj,valueAttri.getText());
                    }else if(classType.equals(Integer.class)){
                        m.invoke(obj,Integer.parseInt(valueAttri.getText()));
                    }else if(classType.equals(Double.class)){
                        m.invoke(obj,Double.parseDouble(valueAttri.getText()));
                    }
                }
                return obj;
            }else{
                throw new Exception("没有此id");
            }
        }else{
            throw new FileNotFoundException();
        }
    }
}
