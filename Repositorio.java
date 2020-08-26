import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Repositorio
{
    public Scanner sc = new Scanner(System.in);

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
        String respuesta;

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

        // En caso de que ninguno calce se dara aviso
        if (indices.size() == 0)
        {
            System.out.println("# No se encontro ningun archivo mencionado en los archivos del Work Space.\n# Porfavor asegurese de agregar los archivos en primera instancia.");
        }
        else
        {
            for (int indice : indices) {
                this.zona.index.archivos.add(this.zona.workSpace.archivos.get(indice));
            }
        }
    }

    // Funcion que compara los nombres de archivos ingresados con los archivos en Work Space y guarda el indice de aquellos que si existan.
    // Entrada: un ArrayList de tipo String con los nombres de los archivos que se desean agregar.
    // Salida: un ArrayList de tipo Integer con los indices de los archivos que coinciden (indice en Work Space).
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
    // Entrada: no tiene.
    // Salida: no tiene.
    public void gitCommit()
    {
        //System.out.println("Funcion gitCommit");

        int ver = 0;
        String autor = "\0";
        String mensaje= "\0";

        if (this.zona.index.archivos.size() == 0)
        {
            System.out.println("No se puede realizar un commit si no se han agregado archivos");
        }
        else
        {
            //Nombre del Commit
            while (ver == 0)
            {
                try {
                    System.out.println("#####################################################");
                    System.out.print("# Ingrese el autor del commit: ");
                    autor = sc.nextLine();
                    System.out.println("#####################################################");
                    ver = 1;
                }
                catch (Exception e)
                {
                    System.out.println("# Ingrese un nombre valido.");
                }
            }

            //Mensaje del Commit
            while (ver == 1)
            {
                try {
                    System.out.println("#####################################################");
                    System.out.print("# Ingrese el mensaje descriptivo del commit: ");
                    mensaje = sc.nextLine();
                    System.out.println("#####################################################");
                    ver = 2;
                }
                catch (Exception e)
                {
                    System.out.println("# Ingrese un mensaje valido.");
                }
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = currentDateTime.format(format1);

        // La represetanción de la lista cambios, será un ArrayList de tipo String donde se pondra una comparación del ultimo commit de
        // Local Repository con este.
        // Se usara un prefijo antes de cada nombre de archivo para saber que sucedio con el:
        //      + para indicar que se agregó este archivo.
        //      - para indicar que se eliminó este archivo.
        //      ~ para indicar que se modificó este archivo.

        ArrayList<String> cambios = comparar_LR_I();

        this.zona.index.commit = new Commit(autor, fecha, mensaje, cambios, this.zona.index.archivos);

        this.zona.localRepo.commits.add(this.zona.index.commit);
        this.zona.remoteRepo.estado_actualizacion = false;

    }

    private ArrayList<String> comparar_LR_I()
    {
        ArrayList<String> cambios = new ArrayList<>();

        String aux;

        String agregar = "+";
        String eliminar = "-";
        //String modificar = "~";

        ArrayList<Commit> local_Commits = this.zona.localRepo.commits;

        ArrayList<Archivo> index_Archivos = this.zona.index.archivos;
        ArrayList<Archivo> index_Archivos_copy = this.zona.index.archivos;

        // Si no existen commits en el local repo, significa que todos los archivos son nuevos
        if (local_Commits.size() == 0)
        {
            for (Archivo archivo : index_Archivos)
            {
                aux = "\0"; // reset para que no exista basura dentro.
                aux = agregar + archivo.nombre;
                cambios.add(aux);
            }
        }
        // Aqui iria else if para los archivos que se han modificado.
        else
        {
            // Se generan copias de los archivos de index y los archivos del commit que comparamos para poder modificarlos sin repercusiones.
            // Borrando los repetidos en ambas listas nos quedaran los archivos agregados (+) en Index_Archivos y los archivos eliminados (-) en
            // ultimo_Commit_Archivos.

            ArrayList<Archivo> ultimo_Commit_Archivos = local_Commits.get(local_Commits.size()-1).archivos;
            ArrayList<Archivo> ultimo_Commit_Archivos_copy = ultimo_Commit_Archivos;

            for (int i = 0; i < index_Archivos.size() ; i++)
            {
                for (int k = 0; k < ultimo_Commit_Archivos.size(); k++)
                {
                    if (index_Archivos_copy.get(i).nombre.equals(ultimo_Commit_Archivos_copy.get(k).nombre))
                    {
                        index_Archivos.remove(index_Archivos_copy.get(i).nombre);
                        ultimo_Commit_Archivos.remove(index_Archivos_copy.get(i).nombre);
                    }
                }
            }

            for (Archivo archivo : index_Archivos)
            {
                aux = "\0"; // reset para que no exista basura dentro.
                aux = agregar + archivo.nombre;
                cambios.add(aux);
            }

            for (Archivo archivo : ultimo_Commit_Archivos)
            {
                aux = "\0"; // reset para que no exista basura dentro.
                aux = eliminar + archivo.nombre;
                cambios.add(aux);
            }

        }

        return cambios;
    }

    // Función que "sube" al Remote Repository los commits almacenados en el Local Repository.
    // Entrada: no tiene.
    // Salida: no tiene.
    public void gitPush()
    {
        //System.out.println("Funcion gitPush");

        this.zona.remoteRepo.commits = this.zona.localRepo.commits;
        this.zona.remoteRepo.estado_actualizacion = true;
    }

    // Función que "descarga" los commits desde Remote Repository a Work Space.
    // Entrada: no tiene.
    // Salida: no tiene.
    public void gitPull()
    {
        //System.out.println("Funcion gitPull");

        this.zona.workSpace.archivos = union(this.zona.workSpace.archivos, this.zona.remoteRepo.commits.get(this.zona.remoteRepo.commits.size()-1).archivos);
    }

    // Funcion que genera una union sin elementos repetidos de dos ArrayList
    // Entrada: Dos ArrayList de tipo T, este tipo hace referencia a cualquier tipo, pero deben ser iguales.
    // Salida: Un ArrayList con la union de las entradas sin elementos repetidos.
    // Funcion recuperada de:
    //      https://stackoverflow.com/questions/5283047/intersection-and-union-of-arraylists-in-java

    public <T> ArrayList<T> union(ArrayList<T> list1, ArrayList<T> list2)
    {
        Set<T> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
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
        //System.out.println("Funcion gitStatus");

        System.out.println("#####################################################");
        System.out.println("# Estatus del Repositorio");
        System.out.println("# Nombre del repositorio: " + this.nombre);
        System.out.println("# Autor del repositorio: " + this.autor);
        System.out.println("# Numero de archivos en el Work Space: " + this.zona.workSpace.archivos.size());
        System.out.println("# Numero de archivos en el Index: " + this.zona.index.archivos.size());
        System.out.println("# Numero de commits en el Local Repository: " + this.zona.localRepo.commits.size());

        if (this.zona.remoteRepo.estado_actualizacion)
        {
            System.out.println("# Estado de actualizacion del Remote Repository: Al dia");
        }
        else
        {
            System.out.println("# Estado de actualización del Remote Repository: No al dia");
        }

        System.out.println("#####################################################");

    }

    // Funcion que crea un nuevo archivo tipo Archivo y lo agrega a Work Space.
    // Entrada: no tiene.
    // Salida: no tiene.
    private void nuevoArchivo()
    {
        //System.out.println("Funcion nuevoArchivo");

        int ver = 0;
        String nombre = "\0";
        String contenido = "\0";

        while (ver == 0)
        {
            try {
                System.out.println("#####################################################");
                System.out.print("# Ingrese el nombre del archivo (con extension): ");
                nombre = sc.nextLine();
                System.out.println("#####################################################");
                ver = 1;
            }
            catch (Exception e)
            {
                System.out.println("# Ingrese un nombre valido.");
            }
        }

        while (ver == 1)
        {
            try {
                System.out.println("#####################################################");
                System.out.print("# Ingrese el contenido del archivo: ");
                contenido = sc.nextLine();
                System.out.println("#####################################################");
                ver = 2;
            }
            catch (Exception e)
            {
                System.out.println("# Ingrese contenido valido.");
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = currentDateTime.format(format1);

        Archivo archivo = new Archivo(nombre, fecha, contenido);


        this.zona.workSpace.archivos.add(archivo);


    }

}












