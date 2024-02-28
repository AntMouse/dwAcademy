package chap9_inheritance_polymorphism;

class Animal {
	public void eat() {
		System.out.println("Animal");
	}
}
class Cow extends Animal {
    public void eat() {
    	System.out.println("AnimalCow");
    }              
    public void eat(String s) {
    	
    }    
}
class CowTest extends Cow {
	public void eat() {
	    System.out.println("AnimalCowTest");
	   } 
}
public class C4OverloadTest {
	public void calc(int x, double y) {	
	}    
    public void calc(){
    	
    }                     
    public void calc(int x) {
    	
    }                 
    public void calc(double y) {
    	
    }            
    public void calc(double y, int x) {
    	
    } 
    // public void calc(int a, double b){}  
    // public int calc(int a, double b({ return 1;} 

	public static void main(String[] args) {
		Animal aa = new Animal();
		aa.eat();
		// aa.eat("Grass");
		Animal ac = new Cow();
		ac.eat();
		// ac.eat("Grass");
		
		Cow cc = new Cow();
		cc.eat();
		cc.eat("Grass");
	}

}
