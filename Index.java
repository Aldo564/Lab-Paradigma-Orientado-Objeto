import java.util.ArrayList;

public class Index
{
    public ArrayList<Archivo> archivos;
    public String mensaje;
    public String fecha;

    public Index(ArrayList<Archivo> archivos, String mensaje, String fecha)
    {
        this.archivos = archivos;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }
}