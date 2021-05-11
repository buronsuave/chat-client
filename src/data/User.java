package data;

public class User 
{
    private static User instance = null;
    
    private int id;
    private String name;
    private String pass;

    public static User get()
    {
        if (instance == null)
        {
            instance = new User();
        }
        return instance;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getPass() 
    {
        return pass;
    }

    public void setPass(String pass) 
    {
        this.pass = pass;
    }
}
