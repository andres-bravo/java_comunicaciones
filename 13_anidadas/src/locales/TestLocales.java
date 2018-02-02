package locales;
class Externa{
	private int s;
	public void metodoExterna() {
		int q=0;
		class Local{
			private int a;
			public void metodoLocal() {			
				//puede acceder a variables del método
				//siempre que no cambien su valor
				System.out.println("local "+q);	
				
			}
		}
		//q=3;
		Local l=new Local();
		l.metodoLocal();
		l.a=10;
		
	}
	
	
}

public class TestLocales {

	public static void main(String[] args) {
		Externa ex=new Externa();
		ex.metodoExterna();

	}

}
