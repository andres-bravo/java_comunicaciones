package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {

	public static void main(String[] args) {
		//Si no pongo nada utiliza puerto y recurso por defecto.
		String dir="http://10.1.1.100:8080/servicio_contactos/Contactos";
		try {
			URL url = new URL(dir);
			URLConnection con=url.openConnection();
			//lectura datos
			InputStream is=con.getInputStream();
			//try con recursos para el cierre del fichero
			try(BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
				bf.lines().forEach(s->System.out.println(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
