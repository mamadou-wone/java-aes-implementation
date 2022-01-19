package com.wone.sn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author mamadou-wone
 */
// #  ___       __   ________  ________   _______
// # |\  \     |\  \|\   __  \|\   ___  \|\  ___ \
// # \ \  \    \ \  \ \  \|\  \ \  \\ \  \ \   __/|
// #  \ \  \  __\ \  \ \  \\\  \ \  \\ \  \ \  \_|/__
// #   \ \  \|\__\_\  \ \  \\\  \ \  \\ \  \ \  \_|\ \
// #    \ \____________\ \_______\ \__\\ \__\ \_______\
// #     \|____________|\|_______|\|__| \|__|\|_______|

public class CryptoImpl implements ICrypto{

    @Override
    public SecretKey genKey(String algo, int keySize) {
        try {
            KeyGenerator gen = KeyGenerator.getInstance(algo);
            gen.init(keySize);
            return gen.generateKey();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String saveKey(SecretKey key, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream obj = new ObjectOutputStream(fos);
            obj.writeObject(key);
            obj.close();
            fos.close();
            return filePath;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public SecretKey getKey(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (SecretKey) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
