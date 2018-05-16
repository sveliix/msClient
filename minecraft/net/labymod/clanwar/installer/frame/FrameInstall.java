package net.labymod.clanwar.installer.frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import net.labymod.clanwar.installer.Installer;
import net.labymod.clanwar.installer.Main;
import net.labymod.clanwar.installer.Utils;

public class FrameInstall extends JFrame
{
    private JProgressBar progressBar;
    private JTextArea installTitle;
    private JTextArea installSubTitle;

    public JProgressBar getProgressBar()
    {
        return this.progressBar;
    }

    public JTextArea getInstallTitle()
    {
        return this.installTitle;
    }

    public JTextArea getInstallSubTitle()
    {
        return this.installSubTitle;
    }

    public FrameInstall()
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
        this.setSize(452, 145);
        this.setTitle("MSClient Installer");
        this.setResizable(false);
        Utils.centerWindow(this, (Component)null);

        try
        {
            BufferedImage bufferedimage = ImageIO.read(Main.class.getResource("images/icon.png"));
            this.setIconImage(bufferedimage);
        }
        catch (IOException var3)
        {
            ;
        }

        JPanel jpanel = new JPanel();
        jpanel.setBackground(Color.WHITE);
        this.setContentPane(jpanel);
        jpanel.setLayout((LayoutManager)null);
        this.progressBar = new JProgressBar();
        this.progressBar.setForeground(new Color(255, 204, 102));
        this.progressBar.setValue(0);
        this.progressBar.setStringPainted(true);
        this.progressBar.setBounds(10, 36, 423, 39);
        jpanel.add(this.progressBar);
        this.installTitle = new JTextArea();
        this.installTitle.setText("Installing MSClient..");
        this.installTitle.setPreferredSize(new Dimension(290, 144));
        this.installTitle.setOpaque(false);
        this.installTitle.setFont(new Font("Dialog", 0, 12));
        this.installTitle.setEnabled(true);
        this.installTitle.setEditable(false);
        this.installTitle.setBounds(10, 11, 384, 19);
        jpanel.add(this.installTitle);
        JButton jbutton = new JButton("Cancel");
        jbutton.setBounds(344, 79, 89, 23);
        jbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        jpanel.add(jbutton);
        this.installSubTitle = new JTextArea();
        this.installSubTitle.setText("Setup installer..");
        this.installSubTitle.setPreferredSize(new Dimension(290, 144));
        this.installSubTitle.setOpaque(false);
        this.installSubTitle.setFont(new Font("Dialog", 0, 12));
        this.installSubTitle.setEditable(false);
        this.installSubTitle.setBounds(10, 79, 321, 23);
        jpanel.add(this.installSubTitle);
        this.show();
        new Installer(this);
    }
}
