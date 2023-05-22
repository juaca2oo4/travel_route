package ui;

import java.util.Scanner;
// importamos el controller
import model.*;
import java.io.File;


public class Main {

    //definimos el tipo de dato del lector
    private Scanner reader;
    // definimos el dato de la clase controller
    private Controller controller;


    public Main() {
        reader = new Scanner(System.in);
        //definimos nombre del controller
        controller = new Controller();


    }
    //returns the class, that is, the class becomes visible to the main method.
    public Scanner getReader(){
        return reader;
    }

    //hacemos visible el controller
    public Controller getController(){
        return controller;
    }

    public static void main(String[] args) {

        // creación del objeto.
        Main main = new Main();
        // llamdo a uno de los metodos de la clase.
        int option = 0;

        do{

            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);

        main.getReader().close();

    }

    public int getOptionShowMenu(){
        int option = 0;
        System.out.println("\n<<<<<AeroLinea >>>>>");
        System.out.println(
                "1. Cargar de informacion \n" +
                        "2.   \n" +
                        "3. xddd \n" +
                        "0.Exit.");

        option =  validateIntegerInput();
        return option;
    }

    public void executeOption(int option){

        switch(option){
            case 1:

                String rutaProyecto = System.getProperty("user.dir");
                String rutaCarpetaData = rutaProyecto + File.separator + "data";
                String nombreArchivo = "cities.txt";
                File archivo = new File(rutaCarpetaData, nombreArchivo);
                String rutaAbsoluta = archivo.getAbsolutePath();
                controller.addDataVertex(rutaAbsoluta);


                String nombreArchivoEdge = "connections.txt";
                File archivo2 = new File(rutaCarpetaData, nombreArchivoEdge);
                String rutaAbsoluta2 = archivo2.getAbsolutePath();
                controller.addDataEdgeList(rutaAbsoluta2);
                controller.addDataEdgeMatriz(rutaAbsoluta2);
                System.out.println("se cargo la base de datos de las ciudadeas y sus conexiones de forma exitosa");



                break;

            case 2:



                break;
            case 3:


                break;

            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }
    /**
     * validateIntegerInput: this method validates that the option entered by the user is actually an integer data type
     * @return option: is a int option.
     */

    public int validateIntegerInput(){
        int option = 0;

        if(reader.hasNextInt()){
            option = reader.nextInt();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }
    /**
     * validateDoubleInput: this method validates that the option entered by the user is actually an double data type
     * @return option: is a double or int  option.
     */

    public double validateDoubleInput(){
        double option = 0;

        if(reader.hasNextDouble()){
            option = reader.nextDouble();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

}