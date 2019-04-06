package admin.dao;

public class UserDAOImpl {
    public void show(String value){
        System.out.println("Trần Đại am: "+value);
    }

    public static void main(String[] args) {
        new UserDAOImpl().show(null);
    }
}
