import java.util.ArrayList;


public class Commit
{
    public String autor; //Nombre del autor del commit
    public String marca_Tiempo; //Fecha del commit
    public String descripcion; //Mensaje
    public ArrayList<String> cambios; //Cambios realizados entre el anterior commit y este
    public ArrayList<Archivo> archivos; //Lista con los archivos actuales

    public Commit(String autor, String marca_Tiempo, String descripcion, ArrayList<String> cambios, ArrayList<Archivo> archivos)
    {
        this.autor = autor;
        this.marca_Tiempo = marca_Tiempo;
        this.descripcion = descripcion;
        this.cambios = cambios;
        this.archivos = archivos;
    }
}