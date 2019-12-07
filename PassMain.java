import java.io.FileNotFoundException;
import java.util.Scanner;

public class PassMain {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		System.out.println("[c] para consultar");
		System.out.println("[i] para introducir nuevo registro");
		System.out.println("[e] para eliminar un registro");
		System.out.println("[a] para ver todos los registros");
		String input = sc.nextLine();
		
		if (input.equals("c")) {
			PassMet.consultaPass();
		} else if (input.equals("i")) {
			PassMet.ingresaRegistro();
		}else if(input.equals("a")) {
			PassMet.verTodo();
		}else if(input.equals("e")) {
			PassMet.eliminar();
		}else {
			System.out.println("ERROR: Algo estás haciendo mal.");
		}
		sc.close();
	}
}
