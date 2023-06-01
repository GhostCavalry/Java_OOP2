package proejct3;

import java.awt.EventQueue;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FileTransfer5 extends JFrame {

    private JPanel icerikpanel; //Kullanacağımız özellikler ilk başta burada tanımlanır.
    private File ilkDizin;
    private File ikinciDizin;
    private JCheckBox checkPng;
    private JCheckBox checkPdf;
    private JCheckBox checkTxt;
    private JCheckBox checkDoc;
    private JCheckBox checkSifrele;
    private JCheckBox checkGizliDosya;
    private JCheckBox checkSikistir;

    public static void main(String[] args) {
    
    	//Main fonksiyonumuzda ilgili class'ı çağırarak işlemi başlatıyoruz.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FileTransfer5 frame = new FileTransfer5();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FileTransfer5() { // Panel özellikleri verilerek ilgili özelliklerle oluşturulur.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        icerikpanel = new JPanel();
        icerikpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(icerikpanel);
        icerikpanel.setLayout(null);

        JButton btnKaynakBul = new JButton("Kaynak Bul"); // Seçeceğimiz kaynak dizininde dosyanın seçilmesi işlemi burada kodlanır. 
        btnKaynakBul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    ilkDizin = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Kaynak dizin seçildi: " + ilkDizin.getAbsolutePath());
                }
            }
        });
        btnKaynakBul.setBounds(50, 40, 150, 30); // Buton konumlaması
        icerikpanel.add(btnKaynakBul);

        JButton btnHedefBul = new JButton("Hedef Bul"); // Seçeceğimiz hedef dizininde dosyanın seçilmesi işlemi burada kodlanır. 
        btnHedefBul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    ikinciDizin = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Hedef dizin seçildi: " + ikinciDizin.getAbsolutePath());
                }
            }
        });
        btnHedefBul.setBounds(250, 40, 150, 30); // Buton konumlaması
        icerikpanel.add(btnHedefBul);

        checkPng = new JCheckBox("PNG"); //İlgili checkbox'ların konumları burada belirlenip ne yazacağı belirtilir. Panele eklenir.
        checkPng.setBounds(50, 100, 70, 30);
        icerikpanel.add(checkPng);

        checkPdf = new JCheckBox("PDF");
        checkPdf.setBounds(150, 100, 70, 30);
        icerikpanel.add(checkPdf);

        checkTxt = new JCheckBox("TXT");
        checkTxt.setBounds(250, 100, 70, 30);
        icerikpanel.add(checkTxt);
        
        checkDoc = new JCheckBox("DOC");
        checkDoc.setBounds(350, 100, 70, 30);
        icerikpanel.add(checkDoc);

        checkSifrele = new JCheckBox("Şifrele");
        checkSifrele.setBounds(80, 160, 70, 30);
        icerikpanel.add(checkSifrele);

        checkGizliDosya = new JCheckBox("Gizli Dosya");
        checkGizliDosya.setBounds(180, 160, 100, 30);
        icerikpanel.add(checkGizliDosya);

        checkSikistir = new JCheckBox("Sıkıştır");
        checkSikistir.setBounds(300, 160, 70, 30);
        icerikpanel.add(checkSikistir);

        JButton btnDosyaTasima = new JButton("Dosya Taşı"); // Dosya taşıma işlemi burada kodlanır tıklanması ve tıklamaya göre işlem yapılması durumları vardır.
        btnDosyaTasima.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ilkDizin == null || ikinciDizin == null) {
                    JOptionPane.showMessageDialog(null, "Kaynak veya hedef dizin seçilmedi!");
                } else {
                    boolean pngSecildi = checkPng.isSelected(); // Checkbox'larımızın işaretlenmesi durumuna göre boolean'a atanır ki buna göre kodların devreye girmesi söz konusu olsun.
                    boolean pdfSecildi = checkPdf.isSelected();
                    boolean txtSecildi = checkTxt.isSelected();
                    boolean docSecildi = checkDoc.isSelected();
                    boolean sifreleSecildi = checkSifrele.isSelected();
                    boolean gizliDosyaSecildi = checkGizliDosya.isSelected();
                    boolean sikistirSecildi = checkSikistir.isSelected();

                    File[] dosyalar = ilkDizin.listFiles();
                    if (dosyalar != null) { // Seçilen dizinin null (değer yok) olmaması durumunda checkboxların işaretlenmesine göre yapılacak işlemler dizisi buradadır.
                        for (File dosya : dosyalar) {
                            String dosyaAdi = dosya.getName();
                            if (dosyaAdi.endsWith(".png") && pngSecildi) {
                                dosyaTasi(dosya, ".png", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            } else if (dosyaAdi.endsWith(".pdf") && pdfSecildi) {
                                dosyaTasi(dosya, ".pdf", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            } else if (dosyaAdi.endsWith(".txt") && txtSecildi) {
                                dosyaTasi(dosya, ".txt", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            } else if(dosyaAdi.endsWith(".docx") && docSecildi) {
                            	dosyaTasi(dosya,".docx", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Dosyalar taşındı!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Kaynak dizin boş!"); // Dosya null durumunda ise verilecek uyarı burada işlenir.
                    }
                }
            }
        });
        btnDosyaTasima.setBounds(170, 220, 100, 30);
        icerikpanel.add(btnDosyaTasima);
    }

    private void dosyaTasi(File dosya, String uzanti, boolean sifreleSecildi, boolean gizliDosyaSecildi, boolean sikistirSecildi) { // Dosyanın taşınması işlemi burada kodlanır şifreleme gizleme ve sıkıştırma burada barınır.
        File hedefDosya = new File(ikinciDizin.getAbsolutePath() + File.separator + dosya.getName());
        if (sifreleSecildi) { // şifreleme esnasında işlem
            hedefDosya = sifrele(hedefDosya);
        }
        
        try {
        	Files.copy(dosya.toPath(), hedefDosya.toPath(), StandardCopyOption.REPLACE_EXISTING); // gizleme esnasında işlem
        	if (gizliDosyaSecildi) {
        		gizliDosyaYap(hedefDosya);
        }
        	}
        catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            Files.copy(dosya.toPath(), hedefDosya.toPath(), StandardCopyOption.REPLACE_EXISTING); // sıkıştırma esnasında işlem
            if (sikistirSecildi) {
                sikistir(hedefDosya);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File sifrele(File dosya) {  // şifreleme asıl içerik

    	
        return dosya;
    }

    private void gizliDosyaYap(File dosya) { // gizleme asıl içerik

    	String hedefDosyaAdi = dosya.getAbsolutePath();
    	String gizlenmisDosyaAdi = ".gizli" + hedefDosyaAdi; 
    	
    	try {
    		JOptionPane.showMessageDialog(null, "Dosya gizlendi: " + gizlenmisDosyaAdi);
    		
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }

    private void sikistir(File dosya) { // sıkıştırma asıl içerik
        String hedefDosyaAdi = dosya.getAbsolutePath();
        String sikistirilmisDosyaAdi = hedefDosyaAdi + ".zip"; // Sıkıştırılma durumundan sonra olacak ismin belirlenmesi için yazılan koddur.
        try (FileOutputStream fos = new FileOutputStream(sikistirilmisDosyaAdi);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(hedefDosyaAdi)) {

            zos.putNextEntry(new ZipEntry(dosya.getName()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) { // Buffer'dan okunacak bilgilerin uzunluğuna göre zip'e çevrilen dosyanın yazma işlemi ve kuralları.
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            zos.flush(); // Kullanılan bilgilerin önbellekte ne kadar saklanacağı bilgisi tutulur.
            zos.finish();

            dosya.delete(); // Orjinal dosyayı silinmek istenirse burası kullanılır

            JOptionPane.showMessageDialog(null, "Dosya sıkıştırıldı: " + sikistirilmisDosyaAdi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


