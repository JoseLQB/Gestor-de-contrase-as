import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PassMet {
	public static void consultaPass() throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		Scanner scFile = new Scanner(new FileReader("files/pass"));
		Pass p = new Pass();
		System.out.print("Introduce un sitio  ");
		String inputSitio = sc.nextLine();

		while (scFile.hasNext()) {
			String line = scFile.nextLine();
			String[] parts = line.split(":");
			for (String s : parts) {
				p.setSitio(parts[0]);
				p.setLog(parts[1]);
				p.setPassword(parts[2]);
			}
			if (p.getSitio().equals(inputSitio)) {
				System.out.println("Usuario/s y contraseña/s de " + inputSitio);
				System.out.println("-------------------");
				System.out.println("Usuario: " + p.getLog() + "\n" + "Contraseña: " + p.getPassword());
			}
		}
		sc.close();
		scFile.close();
	}

	public static void ingresaRegistro() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sitio: ");
		String inputSitio = sc.nextLine();
		System.out.println("Usuario: ");
		String inputUsuario = sc.nextLine();
		System.out.println("Contraseña: ");
		String inputContraseña = sc.nextLine();

		FileWriter file;
		PrintWriter pw;
		try {
			file = new FileWriter("files/pass", true);
			pw = new PrintWriter(file);
			pw.println(inputSitio + ":" + inputUsuario + ":" + inputContraseña);

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-- Nuevos datos registrados --");
		sc.close();
	}

	public static void verTodo() throws FileNotFoundException {
		Scanner scFile = new Scanner(new FileReader("files/pass"));
		Pass p = new Pass();
		while (scFile.hasNext()) {
			String line = scFile.nextLine();
			String[] parts = line.split(":");
			for (String s : parts) {
				p.setSitio(parts[0]);
				p.setLog(parts[1]);
				p.setPassword(parts[2]);
			}
			System.out.println(p.getSitio() + " ---> Usuario: " + p.getLog() + "   Contraseña: " + p.getPassword());
		}
		scFile.close();
	}
}
