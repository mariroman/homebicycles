/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolefilestat;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Рома
 */
public class Persistence {

    private static final String DB_NAME = "LinesAnalizator";

    static {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Persistence() throws SQLException {
        DBup();
    }

    public void saveLineStat(LineStat ls) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into Lines(LONGEST,SHORTEST,LEHGTH,AVGWORDLEN) values(?,?,?,?)");
            ps.setString(1, ls.getLongest());
            ps.setString(2, ls.getShortest());
            ps.setInt(3, ls.getLehgth());
            ps.setDouble(4, ls.getAvgWordLen());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            closeConnection(con);
        }
    }

    public void saveLineStats(Collection<LineStat> col) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into Lines(LONGEST,SHORTEST,LEHGTH,AVGWORDLEN) values(?,?,?,?)");
            for (LineStat ls : col) {
                ps.setString(1, ls.getLongest());
                ps.setString(2, ls.getShortest());
                ps.setInt(3, ls.getLehgth());
                ps.setDouble(4, ls.getAvgWordLen());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            closeConnection(con);
        }
    }

    public List<LineStat> selectLineStat() throws SQLException {
        Connection con = null;
        List<LineStat> res = new ArrayList<>();
        try {
            con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Lines");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String l = rs.getString("LONGEST");
                String s = rs.getString("SHORTEST");
                int len = rs.getInt("LEHGTH");
                double a = rs.getDouble("AVGWORDLEN");
                res.add(new LineStat(l, s, len, a));
            }
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            closeConnection(con);
        }
        return res;
    }

    private void DBup() throws SQLException {
        String createLines = "create table Lines  "
                + "(LineId INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT Line_PK PRIMARY KEY, "
                + " Longest VARCHAR(300), "
                + " Shortest VARCHAR(300), "
                + " LEHGTH INT NOT NULL, "
                + " avgWordLen DOUBLE NOT NULL)";
        Connection con = null;
        try {
            con = getConnection();
            System.out.println("Connected to database: " + DB_NAME);
            Statement vStm = con.createStatement();
            vStm.execute("drop table Lines");
            if (!checkTable(con)) {
                System.out.println(" . . . . creating table Lines");
                vStm.execute(createLines);
            }
        } catch (SQLException vEx) {
            throw vEx;
        } finally {
            closeConnection(con);
        }

    }

    public static void DBdown() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException vEx) {
            if (vEx.getSQLState().equals("XJ015")) {
                System.out.println("Database shut down normally");
            } else {
                System.out.println("Database did not shut down normally");
            }
        }
    }

    private boolean checkTable(Connection con) throws SQLException {
        try {
            Statement vStm = con.createStatement();
            vStm.execute("update Lines set Longest = 'check' where 1=3");
        } catch (SQLException vEx) {
            String vError = vEx.getSQLState();
            /**
             * If table exists will get - WARNING 02000: No row was found *
             */
            if (vError.equals("42X05")) {
                // Table does not exist
                return false;
            } else if (vError.equals("42X14") || vError.equals("42821")) {
                System.out.println("Incorrect table definition");
                throw vEx;
            } else {
                System.out.println("Unhandled SQLException");
                throw vEx;
            }
        }
        System.out.println("Just got the warning - table exists OK ");
        return true;
    }

    private Connection getConnection() throws SQLException {
        String vConnectionURL = "jdbc:derby:" + DB_NAME + ";create=true";
        return DriverManager.getConnection(vConnectionURL);
    }

    private void closeConnection(Connection c) throws SQLException {
        if (c != null) {
            c.close();
        }
    }
}
