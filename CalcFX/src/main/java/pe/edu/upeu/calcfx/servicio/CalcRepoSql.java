package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.conn.Conn;
import pe.edu.upeu.calcfx.modelo.CalCTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class CalcRepoSql {
    Connection connection = new Conn().connectSQLite();
    PreparedStatement ps;
    ResultSet rs = null;
    public List<CalCTO> listarEntidad() {
        System.out.println("Hola SQL Nativo Lista");
        List<CalCTO> lista = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * from calculadora ");
            rs = ps.executeQuery();
            while (rs.next()) {
                CalCTO calCTO = new CalCTO();
                calCTO.setNum1(rs.getString("num1"));
                calCTO.setNum2(rs.getString("num2"));
                calCTO.setOperador(String.valueOf(rs.getString("operador").charAt(0)));
                calCTO.setResultado(rs.getString("resultado"));
                calCTO.setId((long) rs.getInt("id"));
                lista.add(calCTO);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public long maxId() {
        int i=0;
        try {
            ps = connection.prepareStatement("SELECT (max(id)+1) as idx from calculadora ");
            rs = ps.executeQuery();
            if (rs.next()) {
                i= rs.getInt("idx");
            }
            return i;
        } catch (Exception e) {
            return i;
        }
    }
    public int guardarCliente(CalCTO c) {
        int result=0;
        c.setId(maxId());
        try {
            ps = connection.prepareStatement("INSERT INTO calculadora(id, num1, num2, operador, resultado)"
                    + " VALUES("+c.getId()+", '"+c.getNum1()+"', '"+c.getNum2()+"', '"+c.getOperador()+"', '"+c.getResultado()+"')");
            result= ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return result;
    }

    public boolean eliminarEntidad(CalCTO c) {
        int result=0;
        try {
            ps=connection.prepareStatement("DELETE FROM calculadora WHERE id="+c.getId());
            result= ps.executeUpdate();

        }catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return result==1;
    }
    public boolean actualizarEntidad(CalCTO c, int id) {
        int result=0;
        try {
            ps=connection.prepareStatement("update calculadora set num1="+c.getNum1()+
                    ", num2="+c.getNum2()+",operador='"+c.getOperador()+
                    "',resultado="+c.getResultado()+" where id="+id);
                    result=ps.executeUpdate();
        }catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return result==1;
    }
}
