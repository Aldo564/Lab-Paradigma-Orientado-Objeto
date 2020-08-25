import java.util.ArrayList;

public class Index
{
    public ArrayList<Archivo> archivos;
    public String nombre;
    public String mensaje;
    public String fecha;
    public Commit commit;

    public Index(ArrayList<Archivo> archivos, String nombre, String mensaje, String fecha, Commit commit)
    {
        this.archivos = archivos;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.commit = commit;
    }
}