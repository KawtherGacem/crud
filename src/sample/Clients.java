package sample;

public class Clients {
    private String name;
    private Integer id;
    private Integer nbachats;


    public Clients(String name, Integer id, Integer nbachats ) {
        this.name = name;
        this.id = id;
        this.nbachats = nbachats;

    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNbachats() {
        return nbachats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNbachats(Integer nbachats) {
        this.nbachats = nbachats;
    }
}
