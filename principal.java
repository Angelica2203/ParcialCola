import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;

public class principal 
{
    public int Menu()
    {
        String input;
        int vrUsu = 0; 
        boolean validInput = false;
        do 
        {
            input = JOptionPane.showInputDialog("---------- MENU ----------\n" +
                                                "1.Registrar Articulos\n" +
                                                "2.Vender Articulos\n" +
                                                "3.Modificar Articulos\n" +
                                                "4.Eliminar Articulos\n" + 
                                                "5.Salir\n" );
            if (input != null && input.matches("\\d+")) 
            {
                vrUsu = Integer.parseInt(input);
                if (vrUsu >= 1 && vrUsu <= 5) 
                {
                    validInput = true;
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Opción no válida, reintente por favor");
                }
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            }

        } while (!validInput);

        return vrUsu;
    }
    public static void main(String[] args) 
    {
        Queue<ObjEmpresa> c = new LinkedList<>();
        int opc;
        do 
        {
            metodos met = new metodos();
            principal m = new principal();
            opc = m.Menu();
            switch (opc) 
            {
                case 1:
                    c = met.IngresarArticulo(c);
                    break;
                case 2:
                    met.Vender(c);
                    break;
                case 3:
                    met.Modificar(c);
                    break;
                case 4:
                    //met.Eliminar(c);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "El programa ha terminado");
                    break;
            }
            
        } while (opc != 5);
    }

}
