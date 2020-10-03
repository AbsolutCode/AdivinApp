package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Adivinapp extends Application {
	
	private Label etiquetaSolucion; // creamos la etiqueta que mostraremos en el programa
	private Button botonComprobar; // creamos el bot�n que usaremos para comprobar los datos
	private TextField textoSolucion; // creamos el textfield en la que introduciremos los datos
	private int aleatorio = (int) Math.floor(Math.random()*(100-1+1)+1); // creamos un numero aleatorio que retorna un entero aleatorio entre min (incluido) y max (excluido)
	private int intentos = 0; // creamos una variable entera que nos indicar� la cantidad de enteros usados hasta adivinar el n�mero
	
	public void start(Stage primaryStage) throws Exception {
		
		// A�adir el Label y sus caracter�sticas
		
		etiquetaSolucion = new Label(); // instanciamos la etiqueta
		etiquetaSolucion.setText("Introduce un n�mero del 1 al 100"); // le ponemos el texto que va a mostrar la etiqueta
				
		// A�adir el Bot�n y sus caracter�sticas
		
		botonComprobar = new Button(); // instanciamos el bot�n
		botonComprobar.setText("Comprobar"); // le ponemos el texto que va a mostrar el bot�n
		botonComprobar.setOnAction(e -> comprobarBotonAction(e)); // le decimos al bot�n que debe realizar la acci�n en base a lo que se ejecuta dentro de esta funci�n a la que llamamos
		botonComprobar.setDefaultButton(true); // elegimos el bot�n como bot�n por defecto
				
		// A�adir el Textfield y sus caracter�sticas
				
		textoSolucion = new TextField(); // instanciamos el textfield
		textoSolucion.setPrefColumnCount(5);
		textoSolucion.setPromptText("Introduce una sentencia");
		textoSolucion.setMaxWidth(150); // el ancho m�ximo en p�xeles que tendr� el textfield
		
		// Creamos el Vertical Box
		
		VBox root = new VBox();  // creamos e instanciamos el Vertical Box para a�adirle los elementos creados anteriormente
		root.setSpacing(5); // a�adimos espacios entre el VBox y los marcos de la escena
		root.setAlignment(Pos.CENTER); // a�adimos la posici�n que queremos que tenga el VBox dentro de la escena
		root.getChildren().addAll(etiquetaSolucion, textoSolucion, botonComprobar);
		
		Scene escena = new Scene(root, 320, 200); // creamos la escena, la instanciamos, le a�adimos el VBox y el ancho y largo que tendr� la ventana al mostrarse
		
		primaryStage.setScene(escena); // le a�adimos la escena al primaryStage para que se muestre en la ventana
		primaryStage.setTitle("Adivinapp"); // le ponemos el t�tulo que va a tener al mostrarse la ventana
		primaryStage.show(); // aqu� hacemos que se muestre la escena despu�s de haber a�adido todo lo que necesit�bamos
		
	}
	
	private void comprobarBotonAction(ActionEvent e) {
		
		String texto = textoSolucion.getText(); // creamos un String para pasar los datos obtenidos del TextField y posteriormente parsearlo a entero, pues nos interesan n�meros para elaborar el programa
		int numero = 0; // la inicializamos a 0 aunque le vayamos a pasar un valor porque el '0' no entra dentro del n�mero aleatorio que se ha generado y para poder usar la variable en las comparaciones posteriores
		
		try {
			numero = Integer.parseInt(texto); // Pasamos el texto obtenido en el textfield a un String y de ah� lo convertimos en un entero
		} catch (NumberFormatException nfe){ // en caso de no ser un entero, recoger� la excepci�n ' NumberFormatException '
		}
		
		Alert correcto = new Alert(AlertType.INFORMATION);
		Alert fallo = new Alert(AlertType.WARNING); // creamos alertas para cuando el n�mero introducido es correcto, incorrecto o error para cuando no se introduzca un n�mero
		Alert error = new Alert(AlertType.ERROR);
		
		if (numero==aleatorio) {
			
			intentos++;
			correcto.setTitle("Adivinapp");
			correcto.setHeaderText("�Has ganado!");
			correcto.setContentText(String.format("S�lo has necesito %d intentos. \n\nVuelve a intentarlo y hazlo mejor.", intentos));
			correcto.showAndWait();
			
		}
		
		else if (numero <= 0 || numero > 100) {
			
			error.setTitle("Adivinapp");
			error.setHeaderText("Error");
			error.setContentText("El n�mero introducido no es v�lido.");
			error.showAndWait();
			
		}
		
		else {
			
			fallo.setTitle("Adivinapp");
			fallo.setHeaderText("�Has fallado!");
			if (numero < aleatorio) {
				fallo.setContentText(String.format("El n�mero a adivinar es mayor que %d \n\nVuelve a intentarlo.", numero));
			}
			else {
				fallo.setContentText(String.format("El n�mero a adivinar es menor que %d \n\nVuelve a intentarlo.", numero));
			}
			
			fallo.showAndWait();
			intentos++;
			
		}
		
	}

	public static void main(String[] args) {
		
		launch(args); // lanzamos los argumentos, los cuales llamamos desde Main y hacemos que se genere la escena

	}

}
