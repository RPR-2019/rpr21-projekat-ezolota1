package ba.unsa.etf.rpr;

public abstract class User {
    private int id;
    private String name, lastName;
    String username, password;
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        this.id = id;
        this.name = ime;
        this.lastName = prezime;
        this.role =uloga;
        this.username =korisnickoIme;
        this.password =lozinka;
    }

    public User(String ime, String prezime, String korisnickoIme, String lozinka, Role uloga) {
        this.name = ime;
        this.lastName = prezime;
        this.role =uloga;
        this.username =korisnickoIme;
        this.password =lozinka;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return id + ", " + name + " " + lastName;
    }
}
