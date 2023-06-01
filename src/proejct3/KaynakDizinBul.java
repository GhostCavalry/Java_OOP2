package proejct3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class KaynakDizinBul extends JFrame {

    private JPanel contentPane;
    private File kaynakDizin;
    private File hedefDizin;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	KaynakDizinBul frame = new KaynakDizinBul();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public KaynakDizinBul() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnKaynakBul = new JButton("Kaynak Bul");
        btnKaynakBul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    kaynakDizin = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Kaynak dizin seçildi: " + kaynakDizin.getAbsolutePath());
                }
            }
        });
        btnKaynakBul.setBounds(50, 120, 150, 30);
        contentPane.add(btnKaynakBul);

        JButton btnHedefBul = new JButton("Hedef Bul");
        btnHedefBul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    hedefDizin = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Hedef dizin seçildi: " + hedefDizin.getAbsolutePath());
                }
            }
        });
        btnHedefBul.setBounds(250, 120, 150, 30);
        contentPane.add(btnHedefBul);

        JButton btnDosyaAktar = new JButton("Dosya Aktar");
        btnDosyaAktar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kaynakDizin == null || hedefDizin == null) {
                    JOptionPane.showMessageDialog(null, "Kaynak veya hedef dizin seçilmedi!");
                } else {
                    try {
                        File[] dosyalar = kaynakDizin.listFiles();
                        if (dosyalar != null) {
                            for (File dosya : dosyalar) {
                                File hedefDosya = new File(hedefDizin.getAbsolutePath() + File.separator + dosya.getName());
                                Files.copy(dosya.toPath(), hedefDosya.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            }
                            JOptionPane.showMessageDialog(null, "Dosyalar aktarıldı!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Kaynak dizin boş!");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnDosyaAktar.setBounds(170, 180, 100, 30);
        contentPane.add(btnDosyaAktar);
    }
}
