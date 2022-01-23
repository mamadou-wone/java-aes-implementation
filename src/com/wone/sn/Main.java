package com.wone.sn;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author mamadou-wone
 */
public class Main {

    public static void main(String[] args) {
        initOperation();
    }

    public static void initOperation() {
        int inputCount = 0;
        boolean start = true;
        while (start) {
            System.out.println("Hello que souhaitez vous faire ? ");
            System.out.println("*********************************** ");
            System.out.println("1 - Générer une clé ? ");
            System.out.println("2 - Chiffrer un fichier  ? ");
            System.out.println("3 - Déchiffrer un fichier ? ");
            try {
                Scanner sc = new Scanner(System.in);
                int input = sc.nextInt();
                switch (input) {
                    case 1 -> {
                        genKey();
                        start = false;
                    }
                    case 2 -> {
                        enCrypt();
                        start = false;
                    }
                    case 3 -> {
                        deCrypt();
                        start = false;
                    }
                    default -> {
                        System.out.println("Nous ne comprenons pas  votre choix :(");
                        start = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Cette action n'est pas permise :( . Suivez les insctruction SVP !");
                inputCount++;
                if (inputCount == 3) {
                    start = false;
                    System.out.println("Désolez nous ne pouvons pas traiter votre demande :(");
                }
            }
        }
    }

    public static void genKey() {
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

    public static void enCrypt() {
        try {
            CryptoImpl crypto = new CryptoImpl();
            SecretKey key = null;
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Choisissez la clé de chiffrement");
            int option = jfc.showOpenDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = jfc.getSelectedFile().getAbsolutePath();
                key = crypto.getKey(filePath);
            }

            CipherImpl cipher = new CipherImpl();
            Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 1);
            jfc.setDialogTitle("Choisissez le fichier à chiffrer");
            option = jfc.showOpenDialog(jfc);
            String originalFilePath = "";
            String encryptedFilePath = "";

            if (option == JFileChooser.APPROVE_OPTION) {
                originalFilePath = jfc.getSelectedFile().getAbsolutePath();
            }

            jfc.setDialogTitle("Choisissez le chemin du fichier chiffrer pour l'enregistrer");
            option = jfc.showSaveDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                encryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }
            cipher.encryptFile(c, originalFilePath, encryptedFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deCrypt() {
        try {
            CryptoImpl crypto = new CryptoImpl();
            SecretKey key = null;
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Choisissez la clé de déchiffrement");
            int option = jfc.showOpenDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filePath = jfc.getSelectedFile().getAbsolutePath();
                key = crypto.getKey(filePath);
            }
            CipherImpl cipher = new CipherImpl();
            Cipher c = cipher.getCipher(ICipher.cipherAlgorithm, ICipher.iv, key, 2);

            jfc.setDialogTitle("Choisissez le fichier à déchiffrer");
            option = jfc.showOpenDialog(jfc);
            String encryptedFilePath = "";
            String decryptedFilePath = "";

            if (option == JFileChooser.APPROVE_OPTION) {
                encryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }

            jfc.setDialogTitle("Choisissez le chemin du fichier déchiffrer pour l'enregistré");
            option = jfc.showSaveDialog(jfc);
            if (option == JFileChooser.APPROVE_OPTION) {
                decryptedFilePath = jfc.getSelectedFile().getAbsolutePath();
            }

            cipher.decryptFile(c, encryptedFilePath, decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
