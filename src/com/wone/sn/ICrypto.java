package com.wone.sn;

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

public interface ICrypto {

    /**
     * Cette Méthode permet de générer une clé
     * @param algo : L'algorithme de chiffrement (ex:"AES","DES")
     * @param keySize : La Taille de la clé (ex: 128 ou 256)
     * @return : La clé générée
     */
    public SecretKey genKey(String algo, int keySize);

    /**
     *  Cette Méthode permet de stocké la clé dans un fichier
     * @param key: La clé générée
     * @param filePath: Le chemin choisi pour stocker le fichier contenant la clé
     * @return : Le chemin choisi
     */
    public String saveKey(SecretKey key, String filePath);

    /**
     * Cette Méthode permet de récupérer et reconstituer la clé stockée dans le fichier
     * @param filePath: Le chemin du fichier où est stocké notre clé
     * @return : La clé contenue dans le fichier
     */
    public SecretKey getKey(String filePath);

}
