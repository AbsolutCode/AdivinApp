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
	private Button botonComprobar; // creamos el botón que usaremos para comprobar los datos
	private TextField textoSolucion; // creamos el textfield en la que introduciremos los datos
	private int aleatorio = (int) Math.floor(Math.random()*(100-1+1)+1); // creamos un numero aleatorio que retorna un entero aleatorio entre min (incluido) y max (excluido)
	private int intentos = 0; // creamos una variable entera que nos indicará la cantidad de enteros usados hasta adivinar el número
	
	public void start(Stage primaryStage) throws Exception {
		
		// Añadir el Label y sus características
		
		etiquetaSolucion = new Label(); // instanciamos la etiqueta
		etiquetaSolucion.setText("Introduce un número del 1 al 100"); // le ponemos el texto que va a mostrar la etiqueta
				
		// Añadir el Botón y sus características
		
		botonComprobar = new Button(); // instanciamos el botón
		botonComprobar.setText("Comprobar"); // le ponemos el texto que va a mostrar el botón
		botonComprobar.setOnAction(e -> comprobarBotonAction(e)); // le decimos al botón que debe realizar la acción en base a lo que se ejecuta dentro de esta función a la que llamamos
		botonComprobar.setDefaultButton(true); // elegimos el botón como botón por defecto
				
		// Añadir el Textfield y sus características
				
		textoSolucion = new TextField(); // instanciamos el textfield
		textoSolucion.setPrefColumnCount(5);
		textoSolucion.setPromptText("Introduce una sentencia");
		textoSolucion.setMaxWidth(150); // el ancho máximo en píxeles que tendrá el textfield
		
		// Creamos el Vertical Box
		
		VBox root = new VBox();  // creamos e instanciamos el Vertical Box para añadirle los elementos creados anteriormente
		root.setSpacing(5); // añadimos espacios entre el VBox y los marcos de la escena
		root.setAlignment(Pos.CENTER); // añadimos la posición que queremos que tenga el VBox dentro de la escena
		root.getChildren().addAll(etiquetaSolucion, textoSolucion, botonComprobar);
		
		Scene escena = new Scene(root, 320, 200); // creamos la escena, la instanciamos, le añadimos el VBox y el ancho y largo que tendrá la ventana al mostrarse
		
		primaryStage.setScene(escena); // le añadimos la escena al primaryStage para que se muestre en la ventana
		primaryStage.setTitle("Adivinapp"); // le ponemos el título que va a tener al mostrarse la ventana
		primaryStage.show(); // aquí hacemos que se muestre la escena después de haber añadido todo lo que necesitábamos
		
	}
	
	private void comprobarBotonAction(ActionEvent e) {
		
		String texto = textoSolucion.getText(); // creamos un String para pasar los datos obtenidos del TextField y posteriormente parsearlo a entero, pues nos interesan números para elaborar el programa
		int numero = 0; // la inicializamos a 0 aunque le vayamos a pasar un valor porque el '0' no entra dentro del número aleatorio que se ha generado y para poder usar la variable en las comparaciones posteriores
		
		try {
			numero = Integer.parseInt(texto); // Pasamos el texto obtenido en el textfield a un String y de ahí lo convertimos en un entero
		} catch (NumberFormatException nfe){ // en caso de no ser un entero, recogerá la excepción ' NumberFormatException '
		}
		
		Alert correcto = new Alert(AlertType.INFORMATION);
		Alert fallo = new Alert(AlertType.WARNING); // creamos alertas para cuando el número introducido es correcto, incorrecto o error para cuando no se introduzca un número
		Alert error = new Alert(AlertType.ERROR);
		
		if (numero==aleatorio) {
			
			intentos++;
			correcto.setTitle("Adivinapp");
			correcto.setHeaderText("¡Has ganado!");
			correcto.setContentText(String.format("Sólo has necesito %d intentos. \n\nVuelve a intentarlo y hazlo mejor.", intentos));
			correcto.showAndWait();
			
		}
		
		else if (numero <= 0 || numero > 100) {
			
			error.setTitle("Adivinapp");
			error.setHeaderText("Error");
			error.setContentText("El número introducido no es válido.");
			error.showAndWait();
			
		}
		
		else {
			
			fallo.setTitle("Adivinapp");
			fallo.setHeaderText("¡Has fallado!");
			if (numero < aleatorio) {
				fallo.setContentText(String.format("El número a adivinar es mayor que %d \n\nVuelve a intentarlo.", numero));
			}
			else {
				fallo.setContentText(String.format("El número a adivinar es menor que %d \n\nVuelve a intentarlo.", numero));
			}
			
			fallo.showAndWait();
			intentos++;
			
		}
		
	}

	public static void main(String[] args) {
		
		launch(args); // lanzamos los argumentos, los cuales llamamos desde Main y hacemos que se genere la escena

	}

}
