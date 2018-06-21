/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author LN710Q
 */
import conexion.conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.filtro;

public class filtroDao implements metodos<filtro> {
    private static final String SQL_INSERT = "INSERT INTO productos (nombre, codigo, tipo, cantidad, precio, disponibilidad) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET nombre = ?, tipo = ?, cantidad = ?, precio=?, disponibilidad=? WHERE codigo = ?";
    private static final String SQL_DELETE= "Delete from productos where codigo=?";
    private static final String SQL_READ = "Select * from productos where codigo=?";
    private static final String SQL_READALL="Select * from productos";
    private static final conexion con = conexion.conectar();
    
    @Override
    public boolean create(filtro g) {
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getCodigo());
            ps.setString(3, g.getTipo());
            ps.setInt(4, g.getCantidad());
            ps.setDouble(5, g.getPrecio());
            ps.setBoolean(6, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(filtroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(filtroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(filtro c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTipo());
            ps.setInt(3, c.getCantidad());
            ps.setDouble(4, c.getPrecio());
            ps.setBoolean(5, c.getDispo());
            ps.setString(6, c.getCodigo());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(filtroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public ArrayList<filtro> readAll() {
        ArrayList<filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                //int, String, String,String,int, double, boolea
                all.add(new filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getBoolean(7)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(filtroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    @Override
    public filtro read(Object key) {
        filtro f = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                f = new filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getBoolean(7));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(filtroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return f;
    }
}
