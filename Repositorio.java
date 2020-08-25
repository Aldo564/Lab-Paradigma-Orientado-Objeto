import java.awt.desktop.SystemSleepEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        String respuesta = "";

        while (ver == 0)
        {
            mostrar_menu();

            respuesta = sc.nextLine();

            switch (respuesta)
            {
                case "1":
                    gitAdd();
                    break;

                case "2":
                    gitCommit();
                    break;

                case "3":
                    gitPull();
                    break;

                case "4":
                    gitPush();
                    break;

                case "5":
                    gitStatus();
                    break;

                case "6":
                    nuevoArchivo();
                    break;

                case "7":
                    ver = 1;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Ingrese una opcion valida");
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
    // Entrada: no tiene.
    // Salida: no tiene.
    public void gitAdd()
    {
        //System.out.println("Funcion gitAdd");

        int ver = 0;
        int cantidad = 0;
        String nombre_Aux;
        String cant_Aux;

        // Cantidad de archivos a agregar
        while (ver == 0)
        {

            try {
                System.out.println("#####################################################");
                System.out.print("# Ingrese el numero de archivos que desea agregar: ");
                cant_Aux = sc.nextLine();
                cantidad = Integer.parseInt(cant_Aux);
                System.out.println("#####################################################");
                ver = 1;

            }
            catch (NumberFormatException e)
            {
                System.out.println("# Ingrese un numero valido.");
            }

        }

        // Obteniendo los nombres de esos archivos
        ArrayList<String> nombres = new ArrayList<>();
        int i = 0;
        while (i < cantidad)
        {
            try {
                System.out.println("#####################################################");
                System.out.print("# Ingrese el nombre del archivo que desea agregar (con extension correspondiente): ");
                nombre_Aux = sc.nextLine();
                System.out.println("#####################################################");
                nombres.add(nombre_Aux);
                i++;
            }
            catch (Exception e)
            {
                System.out.println("# Ingrese un nombre valido.");
            }
        }

        // Rescatando los indices que calzen
        ArrayList<Integer> indices = comparar_WS_nombres(nombres);

        for (int indice : indices) {
            this.zona.index.archivos.add(this.zona.workSpace.archivos.get(indice));
        }

        // En caso de que ninguno calce se dara aviso
        if (indices.size() == 0)
        {
            System.out.println("# No se encontro ningun archivo mencionado en los archivos del Work Space.\n# Porfavor asegurese de agregar los archivos en primera instancia.");
        }


    }

    private ArrayList<Integer> comparar_WS_nombres(ArrayList<String> nombres)
    {
        int largo_Nombres = nombres.size();
        int largo_WS = this.zona.workSpace.archivos.size();

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < largo_Nombres; i++)
        {
            for (int k = 0; k < largo_WS; k++)
            {
                if ((this.zona.workSpace.archivos.get(k).nombre.equals(nombres.get(i))))
                {
                    indices.add(k);
                }
            }
        }


        return indices;
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












