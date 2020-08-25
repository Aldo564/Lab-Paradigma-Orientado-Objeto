import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Repositorio
{
    Scanner sc = new Scanner(System.in);

    public String nombre;
    public String autor;
    public String fecha;
    public Zones zona;

    // Función constructora de Repositorio.
    public Repositorio(String nombre, String autor, String fecha)
    {
        this.nombre = nombre;
        this.autor = autor;
        this.fecha = fecha;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public String getAutor()
    {
        return this.autor;
    }

    public Object getFecha()
    {
        return this.fecha;
    }

    // Función con la cual navegamos por el programa. 
    // Dependiendo de la opcion elegida puede haber una interacción con el usuario en pantalla.
    // Entrada: no tiene.
    // Salida: no tiene.
    public void menu()
    {

        int ver = 0;
        int respuesta = -1;

        while (ver == 0)
        {
            mostrar_menu();
            respuesta = sc.nextInt();
            switch (respuesta)
            {
                case 1:
                    gitAdd();
                    break;

                case 2:
                    gitCommit();
                    break;

                case 3:
                    gitPull();
                    break;

                case 4:
                    gitPush();
                    break;

                case 5:
                    gitStatus();
                    break;

                case 6:
                    nuevoArchivo();
                    break;

                case 7:
                    ver = 1;
                    System.out.println("Saliendo...");
                    break;
            }

        }
    }


    // Función que muestra el menu principal.
    // Entrada: no tiene.
    // Salida: no tiene.
    private void mostrar_menu()
    {
        System.out.println("#####################################################");
        System.out.println("# Escoja su opcion:");
        System.out.println("# 1) Add");
        System.out.println("# 2) Commit");
        System.out.println("# 3) Pull");
        System.out.println("# 4) Push");
        System.out.println("# 5) Status");
        System.out.println("# 6) Crear nuevo archivo");
        System.out.println("# 7) Salir");
        System.out.println("#####################################################");
    }

    // Función que añade un archivo (o varios) desde el Work Space al Index.
    public void gitAdd()
    {
        System.out.println("Funcion gitAdd");


    }

    // Función que genera un commit desde Index a Local Repository con los archivos en Index y el mensaje pedido.
    public void gitCommit()
    {
        System.out.println("Funcion gitCommit");
    }

    // Función que "sube" al Remote Repository los commits almacenados en el Local Repository.
    public void gitPush()
    {
        System.out.println("Funcion gitPush");
    }

    // Función que "descarga" los commits desde Remote Repository a Work Space.
    public void gitPull()
    {
        System.out.println("Funcion gitPull");
    }

    // Función que nos entrega información del Repositorio tal como:
    //      - Nombre.
    //      - Autor.
    //      - Número de archivos en el Work Space.
    //      - Número de archivos en el Index.
    //      - Número de commits en el Local Repository.
    //      - Estado de actualización del Remote Repository (al dia o no).
    public void gitStatus()
    {
        System.out.println("Funcion gitStatus");
    }

    // Funcion que crea un nuevo archivo tipo Archivo.
    private void nuevoArchivo()
    {
        System.out.println("Funcion nuevoArchivo");
    }

}












