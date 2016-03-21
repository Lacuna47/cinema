import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lacuna on 21.03.2016.
 */
public class CinemaModel {
    public void addHall(int nrows, int seats) throws SQLException {
        Connection connection = DConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO HALLS(NROWS,SEATS)" +
                "VALUES(?,?)");
        preparedStatement.setInt(1, nrows);
        preparedStatement.setInt(2, seats);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Halls> listHalls() throws SQLException {
        List<Halls> list = new ArrayList<Halls>();
        Connection connection = DConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NROWS, SEATS FROM HALLS");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(new Halls(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }


    List<Seances> find(String str) throws SQLException {
        Connection c = DConnection.getConnection();
        PreparedStatement find = c.prepareStatement("SELECT ID, STIME FROM SEANCES WHERE FILM=?");
        find.setString(1, str);
        ResultSet rs = find.executeQuery();
        List<Seances> result = new ArrayList<Seances>();
        while (rs.next()) {
            int id = rs.getInt(1);

            result.add(new Seances(0, null, null, 0, 0, 0));
        }
        return result;
    }
}
