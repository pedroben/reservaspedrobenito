package net.daw.data;

/**
 * Clase Mysql para acceder a bases de datos Mysql. 
 * Nivel data.
 * @author rafael aznar
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.daw.connection.DataSourceConnection;
import net.daw.connection.DriverManagerConnection;
import net.daw.connection.GenericConnection;
import net.daw.helper.Enum;


public class Mysql implements GenericData {

    private Connection oConexionMySQL;

    @Override
    public void conexion(Enum.Connection tipo) throws Exception {
        try {
            GenericConnection oConexion;
            if (tipo == Enum.Connection.DataSource) {
                oConexion = new DataSourceConnection();

            } else {
                oConexion = new DriverManagerConnection();
            }
            oConexionMySQL = oConexion.crearConexion();
        } catch (Exception e) {
            throw new Exception("Mysql.conexion: Error al abrir la conexion:" + e.getMessage());
        }
    }

    @Override
    public void desconexion() throws Exception {
        try {
            if (oConexionMySQL != null) {
                oConexionMySQL.close();
            }
        } catch (SQLException e) {
            throw new Exception("MySQL.CerrarConexion: Error al cerrar la conexion" + e.getMessage());
        }
    }

    @Override
    public void initTrans() throws Exception {
        try {
            oConexionMySQL.setAutoCommit(false);
        } catch (SQLException e) {
            throw new Exception("Mysql.initTrans: Error al iniciar transacci�n: " + e.getMessage());
        }
    }

    @Override
    public void commitTrans() throws Exception {
        try {
            oConexionMySQL.commit();
        } catch (SQLException e) {
            throw new Exception("Mysql.commitTrans: Error en commit: " + e.getMessage());
        }
    }

    @Override
    public void rollbackTrans() throws Exception {
        try {
            oConexionMySQL.rollback();
        } catch (SQLException e) {
            throw new Exception("Mysql.rollbackTrans: Error en rollback: " + e.getMessage());
        }
    }

    @Override
    public void removeOne(int id, String strTabla) throws Exception {
        Statement s;
        try {
            s = (Statement) oConexionMySQL.createStatement();
            s.executeUpdate("DELETE FROM " + strTabla + " WHERE id = " + id);
        } catch (SQLException e) {
            throw new Exception("mysql.deleteOne: Error al eliminar el registro: " + e.getMessage());
        }
    }

    @Override
    public int insertOne(String strTabla) throws Exception {
        ResultSet rs;
        java.sql.PreparedStatement stmt;
        int id = 0;
        try {
            String sql = "INSERT INTO " + strTabla + " (id) VALUES (null) ";
            // stmt.setString(1, "null");
            stmt = oConexionMySQL.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int returnLastInsertId = stmt.executeUpdate();
            if (returnLastInsertId != -1) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                id = rs.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new Exception("mysql.insertOne: Error al insertar el registro: " + e.getMessage());
        }
    }

    @Override
    public void updateOne(int id, String strTabla, String strCampo, String strValor) throws Exception {
        Statement oStatement;
        try {
            oStatement = (Statement) oConexionMySQL.createStatement();
            String sql = "UPDATE " + strTabla + " SET " + strCampo + " = '" + strValor + "' WHERE id = " + Integer.toString(id);
            oStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new Exception("mysql.removeOne: Error al modificar el registro: " + e.getMessage());
        }
    }

    @Override
    public String getId(String strTabla, String strCampo, String strValor) throws Exception {
        Statement oStatement;
        ResultSet oResultSet;
        try {
            oStatement = (Statement) oConexionMySQL.createStatement();
            String sql = "SELECT id FROM " + strTabla + " WHERE " + strCampo + "='" + strValor + "'";
            oResultSet = oStatement.executeQuery(sql);
            oResultSet.first();
            return oResultSet.getString("id");
        } catch (SQLException ex) {
            throw new Exception("mysql.getId: No se ha podido realizar la consulta: " + ex.getMessage());
        }
    }

    @Override
    public String getOne(String strTabla, String strCampo, int id) throws Exception {
        Statement oStatement;
        ResultSet oResultSet;
        try {
            oStatement = (Statement) oConexionMySQL.createStatement();
            String sql = "SELECT " + strCampo + " FROM " + strTabla + " WHERE id=" + Integer.toString(id);
            oResultSet = oStatement.executeQuery(sql);
            oResultSet.first();
            return oResultSet.getString(strCampo);
        } catch (SQLException ex) {
            throw new Exception("mysql.getOne: No se ha podido realizar la consulta: " + ex.getMessage());
        }
    }

    @Override
    public Boolean existsOne(String strTabla, int id) throws Exception {
        int result = 0;
        Statement oStatement;
        try {
            oStatement = (Statement) oConexionMySQL.createStatement();
            ResultSet rs = oStatement.executeQuery("SELECT count(*) FROM " + strTabla + " WHERE 1=1");
            while (rs.next()) {
                result = rs.getInt("COUNT(*)");
            }
            return (result > 0);
        } catch (SQLException e) {
            throw new Exception("mysql.existsOne: Error en la consulta: " + e.getMessage());
        }
    }

    @Override
    public int getPages(String strTabla, int rpp) throws Exception {
        int result = 0;
        Statement oStatement;
        try {
            oStatement = (Statement) oConexionMySQL.createStatement();
            ResultSet rs = oStatement.executeQuery("SELECT count(*) FROM " + strTabla + " WHERE 1=1");
            while (rs.next()) {
                result = rs.getInt("COUNT(*)") / rpp;
                if ((rs.getInt("COUNT(*)") % rpp) > 0) {
                    result++;
                }
            }
            return result;
        } catch (SQLException e) {
            throw new Exception("mysql.getPages: Error en la consulta: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Integer> getPage(String strTabla, int intRegsPerPage, int intPagina, String strOrden) throws Exception {
        try {
            ArrayList<Integer> vector = new ArrayList<>();
            int intOffset;
            Statement stmt;
            stmt = (Statement) oConexionMySQL.createStatement();
            intOffset = Math.max(((intPagina - 1) * intRegsPerPage), 0);
            String sql = "SELECT id FROM " + strTabla + " WHERE 1=1 ";
            if (!strOrden.equals("")) {
                sql += " ORDER BY " + strOrden;
            }
            sql += " LIMIT " + intOffset + " , " + intRegsPerPage;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                vector.add(rs.getInt("id"));
            }
            return vector;
        } catch (SQLException e) {
            throw new Exception("mysql.getPage: Error en la consulta: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        ArrayList<String> vector = new ArrayList<>();

        if (intPageNumber > 1) {
            vector.add("<li>" + strLink + Integer.toString(intPageNumber - 1) + "\">prev</a></li>");
        }
        if (intPageNumber > intNeighborhood + 1) {
            vector.add("<li>" + strLink + "1\">1</a></li>");
        }
        if (intPageNumber > intNeighborhood + 2) {
            vector.add("<li>" + "<a href=\"#\">...</a>" + "</li>");
        }
        for (int i = intPageNumber - intNeighborhood; i <= intPageNumber + intNeighborhood; i++) {
            if (i >= 1 && i <= intTotalPages) {
                if (intPageNumber == i) {
                    vector.add("<li class=\"active\">" + strLink + Integer.toString(i) + "\">" + i + "</a></li>");
                } else {
                    vector.add("<li>" + strLink + Integer.toString(i) + "\">" + i + "</a></li>");
                }
            }
        }
        if (intPageNumber < intTotalPages - (intNeighborhood + 1)) {
            vector.add("<li>" + "<a href=\"#\">...</a>" + "</li>");
        }
        if (intPageNumber < intTotalPages - (intNeighborhood)) {
            vector.add("<li>" + strLink + Integer.toString(intTotalPages) + "\">" + Integer.toString(intTotalPages) + "</a></li>");
        }
        if (intPageNumber < intTotalPages) {
            vector.add("<li>" + strLink + Integer.toString(intPageNumber + 1) + "\">next</a></li>");
        }

        String temp = vector.get(0);
        temp = "<div class=\"pagination\"><ul>" + temp;
        vector.remove(0);
        vector.add(0, temp);

        temp = vector.get(vector.size() - 1);
        temp += "</ul></div>";
        vector.remove(vector.size() - 1);
        vector.add(temp);
        return vector;
    }
}
