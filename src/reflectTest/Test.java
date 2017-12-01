package reflectTest;

import java.lang.reflect.*;
import beans.User;

public class Test {
	public static void main(String[] args) throws Exception {
		//Class userClass = User.class;
		//Class userClass = new User().getClass();
		Class userClass = Class.forName("beans.User");
		//类类型创建变量
		User user = (User) userClass.newInstance();
		user.setName("张三");
		//获取所有User的方法
		Method m = userClass.getMethod("play",new Class[]{String.class});
		Method [] methods = userClass.getMethods();
		//调用方法invoke(对象，参数列表);
		for (Method method : methods) {
			if(method.getName().equals("play")){
				//调用
				method.invoke(user, "篮球");
			}
		}
		System.out.println("--------------------------");
		//获取所有自己的属性
		Field[] fields = userClass.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getType()+":"+field.getName());
		}
	}
}
