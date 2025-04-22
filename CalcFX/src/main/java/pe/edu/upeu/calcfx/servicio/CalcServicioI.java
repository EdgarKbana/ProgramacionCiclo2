package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.CalCTO;

import java.util.List;

public interface CalcServicioI {
    //C
    public void save(CalCTO calCTO);
    //R
    public List<CalCTO> findAll();
    public CalCTO findById(int index);
    //U
    public void update(CalCTO calCTO, int index);
    //O
    public void delete(CalCTO calCTO);
    public void deleteById(int index);
}
