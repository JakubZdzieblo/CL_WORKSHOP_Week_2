package pl.coderslab.entity;

public class UserGroup {

    private int id;
    private String name;

    //construtor for new elements
    public UserGroup(String name) {
        setName(name);
    }

    //construtor for elements from db
    public UserGroup(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User group id="+getId()+" name="+getName();
    }
}
