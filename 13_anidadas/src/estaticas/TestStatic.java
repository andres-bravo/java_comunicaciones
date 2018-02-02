package estaticas;

class Externa{
	private int s;
	static class Estatica{
		private int a;
		public void imprimir() {
			//s=10; //error, solo puede usar elementos de la clase en d¡la que se encuentra, que también sean static
			System.out.println("Estática "+a);
		}
	}
	
	public void metodoExterna() {
		Estatica es=new Estatica();
		es.imprimir();
		es.a=10;
	}
}


public class TestStatic {

	public static void main(String[] args) {
		Externa.Estatica es=new Externa.Estatica();
		es.imprimir();

	}

}
