import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;

public class metodos 
{
    Scanner sc = new Scanner(System.in);
    public Queue<ObjEmpresa> IngresarArticulo(Queue<ObjEmpresa>c) 
    {
        ObjEmpresa o =new ObjEmpresa();
        String entrada;
        //Valdar vacios 
        String ref = JOptionPane.showInputDialog("Ingrese la referencia: ");
        while (ref == null || ref.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Error: La referencia no puede estar vacío.");
            ref = JOptionPane.showInputDialog("Ingrese la referencia");
        }
        o.setReferencia(ref);
        // Validar vacios
        String marca = JOptionPane.showInputDialog("Ingrese la marca: ");
        while (marca == null || marca.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Error: La marca no puede estar vacía.");
            marca = JOptionPane.showInputDialog("Ingrese la marca:");
        }
        o.setMarca(marca);
        // Validar decimal
        entrada = JOptionPane.showInputDialog("Ingrese el precio: ");
        while (!entrada.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            entrada = JOptionPane.showInputDialog("Ingrese el precio (número válido):");
        }
        o.setPrecio(Double.parseDouble(entrada));
        // Validar int
        entrada = JOptionPane.showInputDialog("Ingrese la cantidad: ");
        while (!entrada.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número entero válido.");
            entrada = JOptionPane.showInputDialog("Ingrese la cantidad (número entero):");
        }
        o.setCantidad(Integer.parseInt(entrada));

        String nom = JOptionPane.showInputDialog("Ingrese el nombre del usuario: ");
        while (nom == null || nom.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Error: El nombre del usuario no puede estar vacío.");
            nom = JOptionPane.showInputDialog("Ingrese el nombre del usuario");
        }
        o.setNombreUsuario(nom);

        String cedula = JOptionPane.showInputDialog("Ingrese la cedula del usuario: ");
        while (cedula == null || cedula.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Error: La cedula no puede estar vacío.");
            cedula = JOptionPane.showInputDialog("Ingrese la cedula del usuario: ");
        }
        o.setCedulaUsuario(cedula);
    
        return Validar(c, o);
    }
    public void Mostrar(Queue<ObjEmpresa> c) 
    {
        for (ObjEmpresa obj : c) 
        {
            System.out.println("Referencia: " + obj.getReferencia());
            System.out.println("Marca: " + obj.getMarca());
            System.out.println("Cedula del Usuario: " + obj.getCedulaUsuario());
            System.out.println("-----------------------");
        }

    }
    public Queue<ObjEmpresa> Validar(Queue<ObjEmpresa> c, ObjEmpresa Empre) 
    {
        boolean encontrado = false;
        for (ObjEmpresa obj : c) 
        {
            if (obj.getReferencia().equalsIgnoreCase(Empre.getReferencia()) ) 
            {
                encontrado = true;
                JOptionPane.showMessageDialog(null, "La referencia ya existe");
                break;
            }
        }
        if (!encontrado) 
        {
            c.offer(Empre);
            JOptionPane.showMessageDialog(null, "Articulo ingresado correctamente");
        }
        Mostrar(c);
        return c;
    }
    public void Vender(Queue<ObjEmpresa> c) 
    {
        while (c.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No hay Articulos ingresados");
            c = IngresarArticulo(c);
        }
        
        String  ced = " ";
        boolean encontrado = false;
        System.out.println("Ingrese la venta promedio: ");
        Double venProm = sc.nextDouble();
        System.out.println("Ingrese la cedula del usuario:");
        ced = sc.next();
        for (ObjEmpresa obj : c) 
        { 
            if (obj.getCedulaUsuario().equalsIgnoreCase(ced)) 
            {
                encontrado = true;
                if (obj.getPrecio() < venProm) 
                {
                    System.out.println("No se le aplico un 10%");
                    return;
                }
                Double Desct = obj.getPrecio() * 10/100;
                obj.setPrecio(obj.getPrecio() - Desct);
                System.out.println("DESCUENTO APLICADO\n" +
                                    "Nombre: " + obj.getNombreUsuario() + "\n" +
                                    "Cedula: " + obj.getCedulaUsuario() + "\n" +
                                    "Descuento: " + Desct + "\n" +
                                    "Precio: " + obj.getPrecio() + "\n");  
            }         
        }
        if (!encontrado) 
        {
            System.out.println("Cedula no encontrada");
            return;
        }
        
    }
    public void Modificar(Queue<ObjEmpresa> c) 
    {
        while (c.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No hay Articulos ingresadas");
            c = IngresarArticulo(c);
        }

        System.out.println("Ingrese la cedula de la persona a modificar");
        String ced = sc.next();
        System.out.println("Ingrese la venta promedio: ");
        Double venProm = sc.nextDouble();
        boolean encontrado = false;
        for (ObjEmpresa obj : c) 
        {
            if (obj.getCedulaUsuario().equalsIgnoreCase(ced)) 
            {
                encontrado = true;
                System.out.println("---MODIFICAR--- \n" + "1.Marca\n" + "2.Referencia\n" + "3.Cantidad\n" + "4.Precio\n");
                int opt = sc.nextInt();
                switch (opt) 
                {
                    case 1:
                        System.out.println("Ingrese la nueva marca");
                        obj.setMarca(sc.next());
                        break;
                    case 2:
                        System.out.println("Ingrese la nueva referencia");
                        obj.setReferencia(sc.next());
                        break;
                    case 3:
                        System.out.println("Ingrese la nueva cantidad");
                        obj.setCantidad(sc.nextInt());
                        break;
                    case 4:
                        System.out.println("Ingrese el nuevo precio");
                        obj.setPrecio(sc.nextDouble());
                        for (ObjEmpresa ob : c) 
                        { 
                            if (ob.getPrecio() < venProm) 
                            {
                                System.out.println("No se le aplico un 10%");
                                return;
                            }
                            Double Desct = ob.getPrecio() * 10/100;
                            ob.setPrecio(ob.getPrecio() - Desct);
                            System.out.println("DESCUENTO APLICADO\n" +
                                                    "Nombre: " + ob.getNombreUsuario() + "\n" +
                                                    "Cedula: " + ob.getCedulaUsuario() + "\n" +
                                                    "Descuento: " + Desct + "\n" +
                                                    "Precio: " + ob.getPrecio() + "\n"); 
                            return; 
                        }         
                        
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }

                System.out.println("ARTICULO MODIFICADO\n" +
                                    "Marca: " + obj.getMarca() + "\n" +
                                    "Referencia: " + obj.getReferencia() + "\n" +
                                    "Cantidad: " + obj.getCantidad() + "\n" +
                                    "Cedula: " + obj.getCedulaUsuario() + "\n");
                return;
            }
        }
        if (!encontrado) 
        {
            System.out.println("Prenda no encontrado");
            return;
        }
    }

    
}
