import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DevConsole extends JFrame {
    private JButton clearKeysButton;
    private JPanel DevConsole;
    private JButton dumpUserData;
    private JPanel CloseMSG;
    private JPanel Default;
    private JButton openOutputLogButton;
    private MUI mui;


    public DevConsole(MUI mui) {
        this.mui = mui;
        setContentPane(DevConsole);
        setTitle("Encryption Software");
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        CloseMSG.setVisible(false);
        Default.setVisible(true);

        try {
            setIconImage(ImageIO.read(new File("src/images/icon.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clearKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(DevConsole, "Are You Sure?");
                DBHelperKeys.clearKeys();
                mui.EncryptionSelect().removeAllItems();
                DBHelperKeys.seedFromData();
                mui.reloadKeys();
                Util.print("Database cleared!");


            }
        });
        dumpUserData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(DevConsole,"Are You Sure?");
                DBHelperLTDS.dumpLTDS();
                DBHelperLTDS.intVars();
                Default.setVisible(false);
                CloseMSG.setVisible(true);


            }
        });
        openOutputLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DevConsole,"Under Construction");
                //TODO Make Console And Receive All Outputs form all Sources
            }
        });
    }

    public static void main(String[] args) {

    }
}
