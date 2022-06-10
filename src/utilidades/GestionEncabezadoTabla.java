package utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class GestionEncabezadoTabla  implements TableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JComponent jcomponent = null;
        
        if( value instanceof String ) {
            jcomponent = new JLabel((String) value);
            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.CENTER );
            ((JLabel)jcomponent).setSize( 40, jcomponent.getWidth() );   
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth())  );
        }         
   
        
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        jcomponent.setOpaque(true);

        //Color Azul 1
        //jcomponent.setBackground( new Color(27,92,105) );
        
        //Color Azul oscuro
        jcomponent.setBackground( new Color(39,60,100) );
        
        jcomponent.setForeground(Color.white);
        
        return jcomponent;
    }


}
