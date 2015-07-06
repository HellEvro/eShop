package entity;

public class Test {
    private int id;
    private String login;
    private int pass;
    private int delete_status;
    
    public Test() {
    }

    public Test(int id, String login, int pass, int delete_status) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.delete_status = delete_status;
    }

    public int getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(int delete_status) {
        this.delete_status = delete_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }
    
    
}
