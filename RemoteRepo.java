import java.util.ArrayList;

public class RemoteRepo
{
    public ArrayList<Commit> commits;
    public boolean estado_actualizacion = false;

    public RemoteRepo(ArrayList<Commit> commits)
    {
        this.commits = commits;
    }
}