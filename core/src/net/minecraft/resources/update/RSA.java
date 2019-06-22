package net.minecraft.resources.update;

import __google_.util.ByteUnzip;
import __google_.util.ByteZip;
import __google_.util.FileIO;
import net.minecraft.Logger;
import net.minecraft.logging.Log;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.*;

public class RSA {
    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();
    private final static BigInteger publicKey = new BigInteger("65537");

    private BigInteger privateKey;
    private BigInteger modulus;

    public RSA(int N) {
        BigInteger p = BigInteger.probablePrime(N/2, random);
        BigInteger q = BigInteger.probablePrime(N/2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus    = p.multiply(q);
        privateKey = publicKey.modInverse(phi);
    }

    private RSA(BigInteger privateKey, BigInteger modulus){
        this.privateKey = privateKey;
        this.modulus = modulus;
    }

    public static RSA fromPrivate(byte bytes[]){
        ByteUnzip unzip = new ByteUnzip(bytes);
        return new RSA(new BigInteger(unzip.getBytes()), new BigInteger(unzip.getBytes()));
    }

    public static RSA fromPublic(byte bytes[]){
        ByteUnzip unzip = new ByteUnzip(bytes);
        return new RSA(null, new BigInteger(unzip.getBytes()));
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    public byte[] toBytesPrivate(){
        return new ByteZip().add(this.privateKey.toByteArray()).add(this.modulus.toByteArray()).build();
    }

    public byte[] toBytesPublic(){
        return new ByteZip().add(this.modulus.toByteArray()).build();
    }

    public static byte[] hashing(byte array[]){
        try {
            return MessageDigest.getInstance("SHA-256").digest(array);
        }catch (NoSuchAlgorithmException ignored){
            throw new RuntimeException();
        }
    }

    public static final RSA PUBLIC_KEY;

    static {
        byte array[] = new byte[]{
                -4, 0, 0, 5, 0, 104, 106, 54, -119, 122, 22, 115, 66, -54, -102, 85, -26, -56, 61, 95,
                -91, 70, 82, -71, 82, 14, 107, 111, 63, -79, 35, 65, 93, 88, -12, -114, -114, 70, 113,
                32, 55, -103, 88, 95, -128, -84, 82, -114, -69, -90, -72, -11, -127, 27, 105, -22, -38,
                -82, 14, -20, -84, 20, 103, -57, -121, 28, 99, 61, 76, -100, 122, -41, 67, -62, -17,
                -53, 74, 81, -106, -38, 34, -27, 74, 36, -1, 43, -58, -98, -62, -94, 109, -81, -112,
                48, -123, -69, -83, 99, -40, -111, -121, -127, 125, -91, 94, 47, 18, -13, 32, 64, -11,
                -46, -3, -82, -13, 125, 105, 84, -19, -71, -99, -31, 29, -25, 68, 91, 31, 80, -15, -31,
                34, 51, -11, 21, 64, -112, 9, -30, 85, 20, -121, 45, 89, 81, -37, 41, 97, -83, 41, -42,
                -73, 122, -37, -96, -75, 15, 107, 124, 123, 71, 70, 66, 51, 7, -93, -96, -8, 48, 71, -30,
                -79, 6, 101, 34, -114, 8, 119, -118, -2, -31, -79, -120, 59, -97, 83, -113, -34, 75, 87, 56,
                -38, -78, 18, 63, -115, 79, 16, -84, 70, -61, -99, 66, -34, 14, 108, -102, 35, 13, -76, 89,
                -15, 114, 59, -64, 4, 93, 100, 87, -11, 58, 97, -111, -1, 19, -43, -50, 127, -83, 100, 40,
                53, 3, 101, -77, -3, 22, 10, -93, 112, -67, -20, 42, 115, 75, 5, -28, -32, -32, 37, 68, 47,
                -114, 36, 104, -75, 102, -58, -75, -58, 20, -8, -11, -27, 34, 105, 36, -59, -94, 67, 86, -37,
                -103, -43, 43, -81, 97, -97, -94, -118, 66, -115, 120, 23, 64, 111, -84, -77, 112, -36, -26,
                -13, -21, -61, 64, 13, -127, -58, -2, 113, -40, -93, -100, 74, -2, -99, -81, -2, 59, -105,
                -90, 27, -46, 59, 105, -44, 19, -21, 92, 39, 34, -69, -52, -99, -125, 32, 122, -112, 66,
                -27, 63, 44, -124, -125, -109, -77, 4, 109, 114, 120, 77, 116, -104, -21, -31, 48, -63,
                -106, -32, -112, 127, -46, 1, 20, 105, 44, 27, 105, 83, 103, -4, 31, 58, -29, -71, 36, -44,
                -79, 113, -115, -74, 116, 99, 81, -104, -69, 124, -71, 42, -107, -61, -10, 92, -74, -91,
                -95, 23, -127, -12, -70, 13, -88, 20, -118, -101, -20, -72, 96, -32, 32, 44, 31, 10, 3, 1,
                95, 1, -80, 87, -47, 71, 5, -111, 58, -78, -101, -127, -84, -79, 18, -122, -36, 51, -95,
                -65, -119, 117, -100, 24, -11, 37, 7, -88, 123, -108, -11, 75, -5, -35, -80, -127, -122,
                -13, -10, 72, 89, 74, -28, -48, -12, -74, -110, 41, -30, 90, -19, 3, -100, 50, -65, -125,
                -42, -72, 45, -50, -87, 69, -74, 114, -91, -53, -92, -69, 112, 38, 63, 4, -96, 112, -87, 9,
                110, -116, 16, -113, -1, 47, 33, -46, 30, -44, 66, -33, 36, -13, 104, -96, 75, -108, 27, 116,
                -109, 101, -121, -102, 125, 13, 16, 124, 59, 43, -121, -123, -102, 9, -104, -34, -128, -54,
                -124, -82, -54, 74, -29, -72, -126, 105, -46, 18, -86, -119, -18, 22, 79, -15, 115, -34, 66,
                8, 124, -126, -26, 13, -52, 33, -42, -62, 67, -61, -107, -53, 65, 121, -41, -38, -88, 98, -48,
                -31, -113, 85, -112, 36, -84, -76, 8, -71, -5, 20, 42, -69, 120, -109, -96, 39, 80, 82, -59, 66,
                52, -115, 57, 79, -72, 54, 0, -71, 66, 89, -2, -50, -1, 55, -79, 116, 104, -40, -102, -85, -121,
                2, -5, 20, 91, 5, 34, -114, 37, -50, 100, -29, 27, 109, -121, 66, -106, 105, -125, 105, -32, 97,
                -34, -12, -10, -87, 111, -48, -62, -51, 30, 93, -14, 6, -10, -65, -80, -7, 127, 0, 28, 44, -90,
                15, -119, 31, -45, -111, 22, -128, 24, -29, -93, -72, 56, -79, -3, -2, 124, 6, -33, 38, 13, -73,
                -113, -72, -99, -48, 30, 90, 79, 89, 1, -12, 10, 38, -122, 14, -104, -65, -120, -5, 79, 72, -70,
                -26, 100, 45, 84, 125, 36, 102, -114, 94, -10, -15, -26, 48, 66, 126, -96, -32, -102, 80, -31,
                113, -99, 122, -90, 109, -32, 64, -46, 85, 26, -99, -85, -48, -35, -99, 105, 109, 101, 94, -3,
                126, -12, -111, 28, 16, 12, 64, 34, 58, 90, -114, -118, -3, 113, 81, 122, 83, -52, -95, 54, 22,
                56, 61, 33, 74, -18, 102, 71, -99, -51, -103, -52, 22, 65, 8, -116, -25, 0, 72, -99, -64, 68,
                -115, -28, 9, 30, 54, 91, 67, 29, 84, 108, -45, -16, 83, -95, 76, 4, 119, 57, -50, -96, 104,
                -70, -20, -118, -102, 83, 109, -44, -52, 97, -97, -30, -38, -98, -115, -78, 53, -100, 87, -69,
                -107, -120, -22, 55, 29, -46, -43, 62, -38, -71, -85, 22, 24, 101, 59, 19, 50, 56, 85, 59, 59,
                -35, -118, -12, 57, -109, -4, -18, -97, 61, 49, 8, 3, -107, -69, -58, 121, 122, -25, -24, 106,
                -96, -93, -80, 29, -48, -102, 16, 101, -112, -21, -44, 34, -72, -63, -48, 64, 87, 114, 90, -45,
                35, -38, -75, 81, -106, -58, -15, 91, -25, -51, 45, 126, -91, 103, -40, 90, 32, 39, 31, 12, 83,
                -68, -85, 5, -71, -14, 79, 15, 89, 117, 43, -14, 13, -91, 12, 85, -93, -49, 56, -64, 94, -69, -39,
                -101, -69, -74, 29, -75, 11, -109, 69, 60, 4, -9, 30, -69, 122, 66, 109, -67, 68, -85, 80, 53, -48,
                -62, 55, 59, -12, -31, 98, -91, -48, -105, 108, 21, 112, -75, -72, -56, -96, -82, -100, -118, 109,
                71, -7, 65, 66, -107, -13, 115, -66, -1, -123, 63, 112, 25, -94, 91, -28, 42, -52, -71, -96, -61,
                113, 83, 67, -45, -53, -108, 46, -103, -70, 1, -22, -21, 85, -13, -127, -29, -21, -33, -107, -38,
                15, 29, 73, 62, -88, 26, -121, 44, 15, -26, -93, 109, 22, -83, -81, -71, -60, -11, 107, -67, 44, 77,
                84, 14, 3, 66, -9, -26, 98, 115, 49, 69, 98, -90, -41, 62, 37, -2, 18, -57, 82, 21, -127, -23, 12,
                111, 80, 77, 11, 118, 17, 52, 15, -7, -122, 82, -2, 106, -127, -116, 71, -51, 38, 20, 64, -31, -40,
                -107, -62, -97, 65, -119, -3, -89, 102, 35, 68, 83, -17, 80, 38, 78, 70, -127, 31, -121, -57, 112,
                -60, -111, -91, -23, 109, -30, 55, -70, 44, 61, -97, -2, -101, 55, -18, 111, -103, 76, -110, 49, -18,
                42, -8, 102, 0, 93, -115, 39, -90, 71, 6, -61, 88, 54, -93, 40, 7, -91, 20, -126, 30, 6, 109, 126, 109,
                -61, 42, -100, -59, 65, -88, 19, -64, -50, -52, 64, 30, 61, 101, 59, -118, 49, 119, 107, 95, -101, -102,
                -26, -96, -72, 43, 57, -10, -74, 47, 39, -11, -85, -6, 66, -66, -32, 47, 56, -18, 27, 34, 39, 91, 113,
                55, -3, -117, 66, 23, 87, 69, -31, -11, -105, 113, -100, -1, 47, -119, 79, 9, 36, 49, 91, 42, 108, 49,
                22, 50, -61, 8, -96, 32, -121, -115, 108, 118, -91, 47, -53, -120, 62, 100, 71, 97, -102, 6, 34, 45, 18,
                -39, -73, -121, 120, -52, -85, -69, -43, 6, 70, 79, -120, 98, -92, -85, 16, -108, 115, 95, -15, -1, 102,
                89, -112, 113, 11, -114, -41, 96, 111, 55, -128, 27, 50, 93, 91, 121, 115, -61, -83, 87, 118, 49, 15,
                -31, 48, 25, -48, 125, 40, 1, -10, -83, -84, 48, -57, -43, -44, 48, 94, -6, -4, -44, -69, -3, 81, 0, 0, 0
        };
        PUBLIC_KEY = RSA.fromPublic(array);
    }
}