import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusquedaPersonaGUI extends Component {
    private JPanel panelGeneral;
    private JPanel panelPersona;
    private JPanel panelBotones;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtBuscar;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblBuscar;
    private JButton btnInterpolada;
    private JButton btnBinaria;
    private JButton btnSecuencial;
    private JButton btnAgregar;
    private JButton btnLimpiar;
    private JTextArea txtResultado;

    public Persona [] personas;
    public BusquedaPersonaGUI() {
        btnSecuencial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBuscar=txtBuscar.getText();
                if(nombreBuscar.isEmpty()){
                    JOptionPane.showMessageDialog(BusquedaPersonaGUI.this,"Ingrese una edad para buscar");
                    return;
                }

                int edadBuscar=Integer.parseInt(nombreBuscar);
                int resultado=busquedaSecuencial(edadBuscar);

                if(resultado==-1){
                    txtResultado.setText("Persona con edad "+edadBuscar+"no encontrada ");
                }
                else {
                    txtResultado.setText("Persona con edad "+edadBuscar+"encontrada  en la posicion "+resultado);
                }
            }
        });
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona(txtNombre.getText(),Integer.parseInt(txtEdad.getText()));
                txtResultado.append("\n"+ txtNombre.getText()+ Integer.parseInt(txtEdad.getText()));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BusquedaPersonaGUI");
        frame.setContentPane(new BusquedaPersonaGUI().panelGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void agregarPersona(String nombre, int edad){
        Persona nuevaPersona= new Persona (nombre,edad);
        if(personas==null){
            personas=new Persona[1];
            personas[0]=nuevaPersona;
        }
        else {
            Persona []temp= new Persona[personas.length+1];
            System.arraycopy(personas,0,temp,0,personas.length);
            temp[temp.length-1]=nuevaPersona;
            personas=temp;
        }
    }

    public int busquedaSecuencial(int edad){
        for (int i=0;i<personas.length;i++){
            if(personas[i].getEdad()==edad){
                return  i;
            }
        }
        return -1;
    }
}
