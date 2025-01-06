package a_Nadeem;

import org.testng.annotations.Test;

public class forloopesty {
  @Test
  public void f() {
	  for (int i = 1; i<3;i++) {
		  
		  buyorder();
		  modify();
	  }
  }
  
  public void buyorder() {
	  System.out.println("buy order");
  }
  
  public void modify() {
	  System.out.println("modify buy order"); 
  }
}
