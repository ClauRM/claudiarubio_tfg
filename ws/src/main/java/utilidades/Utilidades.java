package utilidades;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controladorDB.UsuarioDB;
import modelo.Medicamento;
import modelo.Tratamiento;
import modelo.Usuario;

public class Utilidades {

	public static String calcularTratamiento(String inicio, int horas, int duracion) {
		int repeticiones = 24 / horas * duracion;
		List<String> listado = new ArrayList<String>(); // variable arrayList
		LocalDateTime fechaDate;
		String fechaString;
		String stTratamientos = "";

		// aniadir la hora de inicio al arraylist
		listado.add(inicio);

		// convertir String a fecha para poder operar con ella
		fechaDate = convertirStringDate(inicio);

		// iteracion bucle for con longitud = repeticiones
		for (int i = 1; i < repeticiones; i++) {
			// en cada vuelta sumar el n de horas a la fecha
			fechaDate = fechaDate.plusHours(horas);

			// convertir LocalDate a String
			fechaString = fechaDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

			// aniadir fechaString al arraylist
			listado.add(fechaString);
		}

		// recorrer el listado y separar fechas por comas
		for (int i = 0; i < listado.size(); i++) {
			stTratamientos += listado.get(i) + ",";
		}

		// quitar la ultima coma del string
		stTratamientos = stTratamientos.substring(0, stTratamientos.length() - 1);

		// devolver el string con las horas del tratamiento
		return stTratamientos;
	}

	public static LocalDateTime convertirStringDate(String fecha) {
		LocalDateTime fechaDate = null;
		try {
			// definir el formato
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

			// parsear la fecha de String a LocalDate con el formato indicado
			fechaDate = LocalDateTime.parse(fecha, formato);

			// para visualizarla
			System.out.println(fechaDate);

		} catch (Exception e) {
			System.out.println("Error al convertir fecha String a Date: " + e.getMessage());
		}

		return fechaDate;
	}

	public static String validaUsuarioRegistro(Usuario usuarioTemporal) {
		String errores = "";
		String nombre, email, pass;

		// valores de las variables
		nombre = usuarioTemporal.getNombre();
		email = usuarioTemporal.getEmail();
		pass = usuarioTemporal.getPassword();

		// 1. evaluar que los campos no sean vacios
		if (nombre.length() == 0 || email.length() == 0 || pass.length() == 0) {
			errores = errores + "Debe completar todos los campos del formulario. \n";
		} else {
			// validaciones de nombre
			errores = errores + validaNombre(nombre, 20);

			// validaciones de email
			errores = errores + validaEmail(email);

			// validaciones de la logitud del password
			errores = errores + validaPassword(pass, 6);
		}

		System.out.println("Validaciones de registro: " + errores);
		return errores;
	}

	public static String validaTratamiento(Tratamiento tratamientoTemporal) {
		String errores = "";
		String paciente;
		int dosis, horas, duracion;

		// valores de las variables
		paciente = tratamientoTemporal.getPaciente();
		dosis = tratamientoTemporal.getDosis();
		horas = tratamientoTemporal.getHoras();
		duracion = tratamientoTemporal.getDuracion();
		
		//TODO validar

		return errores;
	}
	
	public static String validaMedicamento(Medicamento medicamentoTemporal) {
		String errores = "";
		String medicamento;
		int idmedicamento;
				
		//valores de variables
		idmedicamento = medicamentoTemporal.getIdmedicamento();
		medicamento = medicamentoTemporal.getMedicamento();
		
		//TODO validar
		
		return errores;
	}

	public static String getFechaActual() {
		String fecha = "";
		// zona horaria y formato de fecha
		fecha = ZonedDateTime.now(ZoneId.of("Europe/Paris")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

		System.out.println(fecha);

		return fecha;
	}

	public static List listarTratamientosxHoras(List tratamientos) {
		List<Tratamiento> tratamientosXhoras = new ArrayList<>(); // listado x horas
		int longitud;
		String stTratamiento; // string tratamiento
		String horas[]; // array de horas
		Tratamiento obTratamiento, obTratamientoAux; // objeto tratamiento
		// idTratamiento, idUsuario, idMedicamento, paciente, dosis, horas, duracion,
		// inicio, tratamiento [], observaciones, activo

		// verificar longitud del listado de tratamientos
		longitud = tratamientos.size();

		// RECORRER EL LISTADO DE TRATAMIENTOS
		for (int i = 0; i < tratamientos.size(); i++) {
			// convierto cada posicion en un objeto tratamiento
			obTratamiento = (Tratamiento) tratamientos.get(i);

			// obtengo el string x horas de tratamiento para esa posicion
			stTratamiento = obTratamiento.getTratamiento();

			// guardardo en array, separando con split (",")
			horas = stTratamiento.split(",");

			// RECORRER EL ARRAY DE CADA TRATAMIENTO
			for (int j = 0; j < horas.length; j++) {
				// crear un objeto tratamientoXhoras, seteando cada posicion y la primera
				// posicion del array
				// idtratamiento,fidusuario,fidmedicamento,paciente,dosis,horas,duracion,inicio,tratamiento,observaciones,activo
				obTratamientoAux = new Tratamiento();
				obTratamientoAux.setIdtratamiento(obTratamiento.getIdtratamiento());
				obTratamientoAux.setFidusuario(obTratamiento.getFidusuario());
				obTratamientoAux.setFidmedicamento(obTratamiento.getFidmedicamento());
				obTratamientoAux.setPaciente(obTratamiento.getPaciente());
				obTratamientoAux.setDosis(obTratamiento.getDosis());
				obTratamientoAux.setHoras(obTratamiento.getHoras());
				obTratamientoAux.setDuracion(obTratamiento.getDuracion());
				obTratamientoAux.setInicio(obTratamiento.getInicio());
				obTratamientoAux.setTratamiento(horas[j]); // recorrer el array
				obTratamientoAux.setObservaciones(obTratamiento.getObservaciones());
				obTratamientoAux.setActivo(obTratamiento.getActivo());
				// para los datos del medicamento, creo un nuevo objeto
				obTratamientoAux.setMedicamento(new Medicamento(obTratamiento.getFidmedicamento(),
						obTratamiento.getMedicamento().getMedicamento()));
				// para los datos del usuario, creo un nuevo objeto
				obTratamientoAux
						.setUsuario(new Usuario(obTratamiento.getFidusuario(), obTratamiento.getUsuario().getNombre(),
								obTratamiento.getUsuario().getEmail(), obTratamiento.getUsuario().getPassword()));

				// almacenar el objeto en el listado xhoras
				tratamientosXhoras.add(obTratamientoAux);
			}
		}
		// devolver el listado con el desglose por horas
		return tratamientosXhoras;
	}

	// validaciones del nombre
	private static String validaNombre(String nombre, int longitudMax) {
		String errores = "";
		int i = 0; // posicion de un caracter del string
		char caracter;
		boolean caracterInv = false;

		nombre = nombre.trim(); // limpio espacios

		// 2. longitud maxima de 20 caracteres
		if (nombre.length() > longitudMax) {
			errores = errores + "El nombre de usuario debe tener m�ximo 20 caracteres. \n";
		}

		// 3. no puede contener espacios
		if (nombre.contains(" ")) {
			errores = errores + "El nombre de usuario no puede contener espacios.\n";
		}

		// 4. no debe contener caracteres invalidos
		// recorro la cadena

		do {
			caracter = nombre.charAt(i);
			// Si no est� entre a y z, ni entre A y Z, ni es un espacio
			if (!((caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z'))) {
				errores = errores + "El nombre de usuario no puede contener caracteres inv�lidos. \n";
				caracterInv = true;
			}
			i++;
		} while (caracterInv && i < nombre.length());

		return errores;
	}

	// validaciones del email
	private static String validaEmail(String email) {
		String errores = "";
		UsuarioDB usuariodb = new UsuarioDB(); // instancia de clase para usar metodo validar
		int longitudDominio;

		email = email.trim(); // limpio espacios

		// Compruebo si el email ya esta incluido previamente en la bd
		errores = usuariodb.validarUsuario(email);

		// si hay errores es porque el usuario ya esta incluido en la bd
		if (errores == "") { // si no hay errores, entonces continuar validaciones
			// LONGITUD MINIMA 6 CARACTERES x@y.zz
			if (email.length() < 6) {
				errores = errores + "Longitud de email no v�lida. \n";
			}

			// CONTIENE ARROBA @
			if (!email.contains("@")) {
				errores = errores + "El email no contiene @. \n";
			}

			// @ DEBE ESTAR ENTRE POSICION 1 Y LONGITUD-5
			if (email.indexOf('@') < 1 || email.indexOf('@') > (email.length() - 5)) {
				errores = errores + "La posici�n de la @ en el campo email es incorrecta. \n";
			}

			// DESPUES DE LA @ DEBE HABER UN .
			if (email.contains(".")) {
				if (!(email.lastIndexOf('.') > email.indexOf('@'))) {
					errores = errores + "El . del email est� en posici�n incorrecta. \n";
				} else {
					longitudDominio = email.length() - email.lastIndexOf(".");
					
					System.out.println("longitud del email = "+email.length());
					System.out.println("posicion del ultimo punto = "+email.lastIndexOf("."));
					System.out.println("longitud email - posicion ultimo punto = "+ longitudDominio);
					
					// EL DOMINIO DEBE SER .xx o .xxx
					if (longitudDominio < 3 || longitudDominio > 4) {
						errores = errores + "Dominio del email incorrecto. \n";
					}
				}

			} else {
				errores = errores + "El email no contiene punto. \n";
			}

			// EMPEZAR EN LETRA
			if (email.toUpperCase().charAt(0) < 65 || email.toUpperCase().charAt(0) > 90) {
				errores = errores + "El email debe empezar por una letra. \n";
			}

			// TERMINAR EN LETRA
			if (email.toUpperCase().charAt(email.length() - 1) < 65
					|| email.toUpperCase().charAt(email.length() - 1) > 90) {
				errores = errores + "El email debe terminar en letra";
			}
		}

		return errores;
	}

	// validaciones del password
	private static String validaPassword(String pass, int longitud) {
		String errores = "";

		pass = pass.trim(); // limpio espacios

		// 2. longitud exacta
		if (pass.length() < longitud || pass.length() > longitud) {
			errores = errores + "La contrase�a debe ser de 6 caracteres. \n";
		}

		// 3. no puede contener espacios
		if (pass.contains(" ")) {
			errores = errores + "La contrase�a no puede contener espacios.\n";
		}

		return errores;
	}

}
