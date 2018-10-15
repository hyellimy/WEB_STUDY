import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationExample {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		Class<User> cls = User.class;
		MyAnnotaion annotation = cls.getAnnotation(MyAnnotaion.class);
		System.out.println("name: " + annotation.name());
		System.out.println("value: " + annotation.value());
		
		
		Annotation[] annotations = cls.getAnnotations();
		for (Annotation an : annotations) {
			if(an instanceof MyAnnotaion) {
				MyAnnotaion myAnnotaion = (MyAnnotaion)an;
				System.out.println("name: " + myAnnotaion.name());
				System.out.println("value: " + myAnnotaion.value());
			}
		}
	
		Field field = cls.getDeclaredField("someField");
		MyAnnotaion annotation2 = field.getAnnotation(MyAnnotaion.class);
		System.out.println("name: " + annotation2.name());
		System.out.println("value: " + annotation2.value());
		
		Method method = cls.getDeclaredMethod("someMethod");
		MyAnnotaion annotation3 = method.getAnnotation(MyAnnotaion.class);
		System.out.println("name: " + annotation3.name());
		System.out.println("value: " + annotation3.value());
	}

}
