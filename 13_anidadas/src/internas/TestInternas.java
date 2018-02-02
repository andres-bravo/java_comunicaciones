package internas;
class Externa{
	private int s;
	class Interna{
		private int a;
		public void imprimir() {
			System.out.println("Interna "+s);
		}
	}
	public void metodoExterna() {

		Interna in=new Interna();
		in.imprimir();
		in.a=6;//puede llamar a atributos privados con objeto de la interna
	}
	
}


public class TestInternas {

	public static void main(String[] args) {
		/*Externa ex=new Externa();
		Externa.Interna in=ex.new Interna();*/
		Externa.Interna in=new Externa().new Interna();
		in.imprimir();
		//in.a=1;//error de compilación
	}

}
