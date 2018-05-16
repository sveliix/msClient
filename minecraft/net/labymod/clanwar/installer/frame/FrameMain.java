package net.labymod.clanwar.installer.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import net.labymod.clanwar.installer.Main;
import net.labymod.clanwar.installer.Utils;

public class FrameMain extends JFrame
{
    private JTextArea infoArea;

    public FrameMain()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setSize(671, 410);
        this.setTitle("MSClient Installer");
        this.setResizable(false);
        Utils.centerWindow(this, (Component)null);

        try
        {
            BufferedImage bufferedimage = ImageIO.read(Main.class.getResource("images/icon.png"));
            this.setIconImage(bufferedimage);
        }
        catch (IOException var8)
        {
            ;
        }

        JPanel jpanel = new JPanel();
        jpanel.setBackground(Color.WHITE);
        this.setContentPane(jpanel);
        jpanel.setLayout((LayoutManager)null);
        JButton jbutton = new JButton("Install MSClient for Minecraft 1.8.8");
        jbutton.setFont(new Font("Dialog", 0, 15));
        jbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                File file1 = new File(Main.path.getAbsolutePath());

                if (file1.exists() && file1.isDirectory())
                {
                    Main.path = file1;
                    FrameMain.this.dispose();
                    new FrameInstall();
                }
                else
                {
                    Utils.error("The selected directory " + file1.getName() + " doesn\'t exist!");
                }
            }
        });
        jbutton.setBounds(355, 332, 300, 39);
        jpanel.add(jbutton);
        Panel panel = new Panel();
        panel.setBounds(0, 0, 349, 381);
        jpanel.add(panel);
        Panel panel1 = new Panel();
        panel1.setBackground(new Color(250, 250, 250));
        panel1.setBounds(355, 10, 300, 316);
        jpanel.add(panel1);
        panel1.setLayout((LayoutManager)null);
        this.infoArea = new JTextArea();
        this.infoArea.setBounds(10, 78, 280, 238);
        this.infoArea.setEditable(false);
        this.infoArea.setEnabled(true);
        this.infoArea.setFont(new Font("Dialog", 0, 12));
        this.infoArea.setLineWrap(true);
        this.infoArea.setOpaque(false);
        this.infoArea.setPreferredSize(new Dimension(290, 144));
        this.infoArea.setText("This installer will automatically install MSClient with Optifine.\n\nIf you\'ve successfully installed MSClient,\nopen the Minecraft launcher and select the MSClient profile.\n\nMake sure you\'ve closed Minecraft before the\ninstallation and launch the installer from your desktop.");
        this.infoArea.setWrapStyleWord(true);
        panel1.add(this.infoArea);
        JLabel jlabel = new JLabel();
        jlabel.setText("MSClient");
        jlabel.setPreferredSize(new Dimension(385, 42));
        jlabel.setOpaque(false);
        jlabel.setFont(new Font("Dialog", 1, 31));
        jlabel.setHorizontalAlignment(0);
        jlabel.setEnabled(true);
        jlabel.setBounds(10, 11, 280, 39);
        panel1.add(jlabel);
        JLabel jlabel1 = new JLabel();
        jlabel1.setText("for Minecraft 1.8.8");
        jlabel1.setPreferredSize(new Dimension(385, 42));
        jlabel1.setOpaque(false);
        jlabel1.setHorizontalAlignment(0);
        jlabel1.setFont(new Font("Dialog", 1, 19));
        jlabel1.setEnabled(true);
        jlabel1.setBounds(10, 49, 280, 21);
        panel1.add(jlabel1);
        panel.setLayout((LayoutManager)null);
        JLabel jlabel2 = new JLabel(new ImageIcon(Main.class.getResource("images/sideImage.png")));
        jlabel2.setBounds(0, 0, 349, 381);
        panel.add(jlabel2);
        this.show();
    }
}
