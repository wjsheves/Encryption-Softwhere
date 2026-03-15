import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DevConsole extends JFrame {
    private JButton clearKeysButton;
    private JPanel DevConsole;
    private MUI mui;

    public DevConsole() {
        this.mui = mui;
        setContentPane(DevConsole);
        setTitle("Encryption Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            setIconImage(ImageIO.read(new File("src/images/icon.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clearKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHelper.clearKeys();
                mui.getEncryptionSelect().removeAllItems();
                Util.print("Database cleared!");
            }
        });
    }

    public static void main(String[] args) {
        new DevConsole();

    }
}
