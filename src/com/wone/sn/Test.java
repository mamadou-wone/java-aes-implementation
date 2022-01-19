package com.wone.sn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author mamadou-wone
 */

public class Test {

/*
    public static void encryptFolder(final File folder, SecretKey key) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                encryptFolder(fileEntry, key);
            } else {
                CipherImpl cipher = new CipherImpl();
                Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 1);
                File encrypt = new File(fileEntry.getAbsolutePath());
                cipher.encryptFile(c, fileEntry.getAbsolutePath(), encrypt.getAbsolutePath() + ".enc");
                fileEntry.delete();
            }
        }
    }

    public static void decryptFolder(final File folder, SecretKey key) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                decryptFolder(fileEntry, key);
            } else {
                CipherImpl cipher = new CipherImpl();
                Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 2);
                String[] name = fileEntry.getName().split(".enc");
                File decrypt = new File(fileEntry.getParent() + "/" + name[0]);
                cipher.decryptFile(c, fileEntry.getAbsolutePath(), decrypt.getAbsolutePath());
                fileEntry.delete();
            }
        }
    }
*/

    public static void GenKey() {
        CryptoImpl crypto = new CryptoImpl();
        SecretKey key = crypto.genKey(ICipher.keyAlgorithm, ICipher.keySize);
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Choisissez le chemin pour enregistrer la clé");
        int option = jfc.showSaveDialog(jfc);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            crypto.saveKey(key, filePath);
            JOptionPane.showMessageDialog(null, "Votre clé a bien été générer", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
