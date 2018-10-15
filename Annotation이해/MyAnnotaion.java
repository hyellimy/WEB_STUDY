import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 클래스 애노테이션(소스코드에 붙이는 메타데이터) 정의 -> 라벨과 같은 역할

//@Target(ElementType.TYPE)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotaion {
	public String name();
	public String value();
}

/*
@Target : 애노테이션을 적용할 대상

값은 enum타입 ElementType의 상수값 사용
ElementType.CONSTRUCTOR : 생성자에 적용
ElementType.FIELD : 필드에 적용 
ElementType.LOCAL_VARIABLE : 지역변수에 적용 
ElementType.METHOD : 메서드에 적용
ElementType.PACKAGE : 패키지에 적용
ElementType.PARAMETER : 매개변수에 적용
ElementType.TYPE : class, interface, enum 에 적용

@Retention : 해당 애노테이션의 적용scope 지정

값은 enum타입 RetentionType의 상수값 사용
RetentionType.SOURCE : 소스파일에만 적용됨. 컴파일부터는 적용되지않음
RetentionType.CLASS : 클래스 파일에 적용됨. 실행시에는 사용하지 못함 
RetentionType.RUNTIME : 소스파일과 클래스파일, 실행시 적용

*/