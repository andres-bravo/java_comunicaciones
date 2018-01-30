package principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MuestraContactos {

	public static void main(String[] args) {
		//Si no pongo nada utiliza puerto y recurso por defecto.
				String dir="http://apidev.accuweather.com/locations/v1/search?q=madrid,%20spain&apikey=hoArfRosT1215";
				try {
					URL url = new URL(dir);
					URLConnection con=url.openConnection();
					//lectura datos
					InputStream is=con.getInputStream();
					//A partir de aquí utilizo el API de Json
					
					//try con recursos para el cierre del fichero
					/*try(BufferedReader bf = new BufferedReader(new InputStreamReader(is));){
						bf.lines().forEach(s->System.out.println(s));
					}*/
					JSONParser parser = new JSONParser();
					JSONArray array =(JSONArray)parser.parse(new InputStreamReader(is));
					//Recorremos el array JSON y mostramos los nombres de los contactos
					/*
					for(Object ob:array) {
						JSONObject data=(JSONObject)ob;
						//JSONObject geoposition=(JSONObject)data.get("GeoPosition");
						
						System.out.println(((JSONObject)data.get("GeoPosition")).get("Latitude"));
					}*/
					
					//Es un ArrayList por lo que puedo sacar elementos con get
					JSONObject data= (JSONObject)array.get(0);
					System.out.println("Latitud: "+((JSONObject)data.get("GeoPosition")).get("Latitude"));
					System.out.println("Longitud: "+((JSONObject)data.get("GeoPosition")).get("Longitude"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}

}
