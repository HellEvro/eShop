package entity.dao;

import java.sql.SQLException;

public interface DAOInterface<T> {
    public void insert(T ob) throws SQLException;
    public void delete (T ob)throws SQLException;
    public void update(T ob)throws SQLException;
}
