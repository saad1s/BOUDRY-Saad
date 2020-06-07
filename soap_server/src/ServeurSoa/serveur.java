package ServeurSoa;

import javax.xml.ws.Endpoint;

import dao.Fonctions;

public class serveur {

	public static void main(String[] args) {
		String url="http://localhost:1111/";
		Endpoint.publish(url, new Fonctions());
		System.out.println(url);

	}

}
