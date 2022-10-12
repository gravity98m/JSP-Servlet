package ex;

class Parent1{
	public void pprn() {
		System.out.println(" P : pprn()");
	}
}

class Child1 extends Parent1{
//	public void pprn() {
//		System.out.println(" C : pprn()");
//	}
	
	// alt shift s + v => 오버라이딩 메서드 호출
	@Override
	public void pprn() {
		 super.pprn(); // 부모의 은닉된 메서드를 호출
		// System.out.println(" C : pprn()");
	}
	
	public void cprn() {
		System.out.println(" C : cprn()");
	}
}

class Itwill{
	// 클래스 구성요소
	// 1) 변수
	// 2) 생성자
	// 3) 메서드
	// 기본생성자는 컴파일러가 자동 생성
	// => 오버로딩된 생성자가 없을 경우
	
	public Itwill() {
		System.out.println("Itwill 생성자");
	}

	public Itwill(int a) {
		System.out.println("Itwill 생성자(a)");
	}
	
	
	
}

class ItwillBusan extends Itwill {
	public ItwillBusan() {
		// super(); 생략
		super(0);
		System.out.println("ItwillBusan 생성자");
	}
}
// * 생성자는 상속 가능하다 [x] => 상속되지 않는 유일한 멤버 메서드
// * 개발자가 별도의 명시적 호출없이 자동으로 서브클래스의 생성자는 슈퍼클래스의 생성자 호출

// => main() 포함하는 메서드
public class Test2 {

	public static void main(String[] args) {
		
		Parent1 p1 = new Parent1();
		p1.pprn();
		
		System.out.println("---------------------------------------------");
		
		Child1 c1 = new Child1();
		c1.cprn();
		c1.pprn();
		
		// this : 나 자신의 객체 레퍼런스
		// super : 부모의 객체 레퍼런스
		// => 처음 클래스 생성시 컴파일러가 1개씩 생성
		
		Itwill will = new Itwill(3);
		
		System.out.println("----------------------------------------------");
		ItwillBusan willbs = new ItwillBusan();
		
		
		
	}

}
