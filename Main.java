import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {

    static Scanner sc = new Scanner(System.in);

    public Repositorio repositorio;

    public static void main(String[] args)
    {
        Repositorio repo = gitInit();

        mostrar_Repo(repo);

        repo.menu();
    }

    // Funcion que inicializara el repositorio
    // Entrada: no tiene.
    // Salida: no tiene.
    public static Repositorio gitInit()
    {
        Repositorio repo = entrada_Datos();

        ArrayList<Archivo> archivos_ws = new ArrayList<>();
        ArrayList<Archivo> archivos_i = new ArrayList<>();
        ArrayList<Archivo> archivos_commit = new ArrayList<>();

        ArrayList<Commit> commits_lr = new ArrayList<>();
        ArrayList<Commit> commits_rr = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        String vacio = "";
        Commit commit = new Commit(vacio, vacio, vacio, strings, archivos_commit);

        WorkSpace ws = new WorkSpace(archivos_ws);
        Index i = new Index(archivos_i, vacio, vacio, vacio, commit);
        LocalRepo lr = new LocalRepo(commits_lr);
        RemoteRepo rr = new RemoteRepo(commits_rr);

        Zones zona = new Zones(ws, i, lr, rr);

        repo.zona = zona;

        return repo;
    }

    // Funcion que recibe los datos del repositorio y genera este.
    // Entrada: no tiene.
    // Salida: no tiene.
    public static Repositorio entrada_Datos()
    {
        int ver = 0;
        String autor = null;
        String nombre = null;

        //Nombre del repositorio
        while (ver == 0)
        {
            try {
                System.out.println("#####################################################");
                System.out.print("# Ingrese el nombre del repositorio: ");
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
                System.out.print("# Ingrese el nombre del autor: ");
                autor = sc.nextLine();
                System.out.println("#####################################################");
                ver = 2;
            }
            catch (Exception e)
            {
                System.out.println("# Ingrese un nombre de autor valido.");
            }
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = currentDateTime.format(format1);


        Repositorio repo = new Repositorio(nombre, autor, fecha);
        return repo;
    }


    public static void  mostrar_Repo(Repositorio repo)
    {
        System.out.println("Nombre del repo: " + repo.getNombre());
        System.out.println("Nombre del autor: " +  repo.getAutor());
        System.out.println("Fecha de creacion del repo: " + repo.getFecha());

    }


}





















