import java.util.ArrayList;


public class Commit
{
    public String autor;
    public String marca_Tiempo;
    public String descripcion;
    public String representacion;
    public ArrayList<Archivo> archivos;

    public Commit(String autor, String marca_Tiempo, String descripcion, String representacion, ArrayList<Archivo> archivos)
    {
        this.autor = autor;
        this.marca_Tiempo = marca_Tiempo;
        this.descripcion = descripcion;
        this.representacion = representacion;
        this.archivos = archivos;
    }
}