import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassMet {
	// Consular un registro espec�fico
	public static void consultaPass() throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		Scanner scFile = new Scanner(new FileReader("files/pass"));
		Pass p = new Pass();
		System.out.print("Introduce un sitio  ");
		String inputSitio = sc.nextLine();
		while (scFile.hasNext()) {
			String line = scFile.nextLine();
			String[] parts = line.split(":");
			p.setSitio(parts[0]);
			p.setLog(parts[1]);
			p.setPassword(parts[2]);
			if (p.getSitio().equals(inputSitio)) {
				System.out.println("Usuario/s y clave/s de " + inputSitio);
				System.out.println("-------------------");
				System.out.println("Usuario: " + p.getLog() + "\n" + "clave: " + p.getPassword());
			}
		}
		sc.close();
		scFile.close();
	}
	// Ingresar un nuevo registro
	public static void ingresaRegistro() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Sitio: ");
		String inputSitio = sc.nextLine();
		System.out.println("Usuario: ");
		String inputUsuario = sc.nextLine();
		System.out.println(("clave: "));
		String inputPass = getMD5(sc.nextLine());
		FileWriter file;
		PrintWriter pw;
		try {
			file = new FileWriter("files/pass", true);
			pw = new PrintWriter(file);
			pw.println(inputSitio + ":" + inputUsuario + ":" + inputPass);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-- Nuevos datos registrados --");
		sc.close();
	}
	//Encriptar claves con md5
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
	   
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	// Ver todos los registros
	public static void verTodo() throws FileNotFoundException {
		Scanner scFile = new Scanner(new FileReader("files/pass"));
		Pass p = new Pass();
		while (scFile.hasNext()) {
			String line = scFile.nextLine();
			String[] parts = line.split(":");
			p.setSitio(parts[0]);
			p.setLog(parts[1]);
			p.setPassword(parts[2]);
			System.out.println(p.getSitio() + " ---> Usuario: " + p.getLog() + "   clave: " + p.getPassword());
		}
		scFile.close();
	}
	// Eliminar un registro
	public static void eliminar() throws FileNotFoundException {
		Scanner scFile = new Scanner(new FileReader("files/pass"));
		Scanner scInput = new Scanner(System.in);
		String q;
		String inputNombre;
		String inputUser;
		FileWriter fw;
		PrintWriter pw;
		List<String> list = new ArrayList<String>();
		System.out.println("�Quieres ver antes todos los registros [s]|[n]");
		q = scInput.nextLine();
		if (q.equals("s")) {
			verTodo();
		}
		System.out.println("Introduce los siguientes datos del registro que quieres eliminar.");
		System.out.println("Sitio: ");
		inputNombre = scInput.nextLine();
		System.out.println("Usuario: ");
		inputUser = scInput.nextLine();
		while (scFile.hasNext()) {
			String line = scFile.nextLine();
			if ((!line.contains(inputNombre + ":")) && (!line.contains(inputUser + ":"))) {
				list.add(line);
			}
		}
		scFile.close();
		scInput.close();
		try {
			fw = new FileWriter("files/pass");
			pw = new PrintWriter(fw);
			for (String rec : list) {
				pw.println(rec);
			}
			fw.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out
				.println("Se ha eliminado el registro del usuario " + inputUser + " con sitio en " + inputNombre + ".");
	}
}
