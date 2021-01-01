package  com.example.xposedbase.hook.KktDatabase;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;



public class DataBaseResourceCrypto {
    public static final byte[] f = {15, 8, 1, 0, 25, 71, 37, -36, 21, -11, 23, -32, -31, 21, 12, 53};
    public static final char[] g = {22, 8, 9, 'o', 2, 23, '+', 8, '!', '!', 10, 16, 3, 3, 7, 6};
    public static final IvParameterSpec h = new IvParameterSpec(f);
    public static final f<String, DataBaseResourceCrypto> i = new f<>(16);
    public byte[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public final Cipher c;
    public int d;
    public long e;

    public static class CipherException extends Exception {
        public CipherException(Throwable th) {
            super(th);
        }
    }

//    public static class b extends DataBaseResourceCrypto {
//        public /* synthetic */ b(long j, a aVar) throws Exception {
//            super(j, -1);
//        }

        public String a(String str) throws CipherException {
            return str;
        }

        public String b(String str) throws CipherException {
            return str;
        }


    public  static String i(String str, String str2) {
        return str + str2;
    }

    public  static String e(int i, String str) {
        return i + str;
    }

    public byte[] base64decode(String str) throws UnsupportedEncodingException {
        return Base64.decode(str.getBytes("UTF-8"), Base64.DEFAULT);
    }

    public DataBaseResourceCrypto(long j, int i2) throws Exception {
        if (j > 0) {
            this.e = j;
            this.d = i2;
            byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            if (j > 0) {
                String valueOf = String.valueOf(j);
                switch (this.d) {
                    case 2:
                        valueOf = e(12, valueOf);
                        break;
                    case 3:
                        valueOf = e(24, valueOf);
                        break;
                    case 4:
                        valueOf = e(18, valueOf);
                        break;
                    case 5:
                        valueOf = e(30, valueOf);
                        break;
                    case 6:
                        valueOf = e(36, valueOf);
                        break;
                    case 7:
                        valueOf = e(12, valueOf);
                        break;
                    case 8:
                        valueOf = e(48, valueOf);
                        break;
                    case 9:
                        valueOf = e(7, valueOf);
                        break;
                    case 10:
                        valueOf = e(35, valueOf);
                        break;
                    case 11:
                        valueOf = e(40, valueOf);
                        break;
                    case 12:
                        valueOf = e(17, valueOf);
                        break;
                    case 13:
                        valueOf = e(23, valueOf);
                        break;
                    case 14:
                        valueOf = e(29, valueOf);
                        break;
                    case 15:
                        valueOf = i("isabel", valueOf);
                        break;
                    case 16:
                        valueOf = i("kale", valueOf);
                        break;
                    case 17:
                        valueOf = i("sulli", valueOf);
                        break;
                    case 18:
                        valueOf = i("van", valueOf);
                        break;
                    case 19:
                        valueOf = i("merry", valueOf);
                        break;
                    case 20:
                        valueOf = i("kyle", valueOf);
                        break;
                    case 21:
                        valueOf =i("james", valueOf);
                        break;
                    case 22:
                        valueOf = i("maddux", valueOf);
                        break;
                    case 23:
                        valueOf = i("tony", valueOf);
                        break;
                    case 24:
                        valueOf = i("hayden", valueOf);
                        break;
                    case 25:
                        valueOf = i("paul", valueOf);
                        break;
                    case 26:
                        valueOf = i("elijah", valueOf);
                        break;
                    case 27:
                        valueOf = i("dorothy", valueOf);
                        break;
                    case 28:
                        valueOf = i("sally", valueOf);
                        break;
                    case 29:
                        valueOf = i("bran", valueOf);
                        break;
                }
                byte[] bytes = valueOf.getBytes("UTF-8");
                int i3 = 0;
                while (i3 < bArr.length && bytes.length > i3) {
                    bArr[i3] = bytes[i3];
                    i3++;
                }
            }
            this.a = bArr;
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC").generateSecret(new PBEKeySpec(g, this.a, 2, 256)).getEncoded(), "AES");
            this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.c.init(Cipher.DECRYPT_MODE, secretKeySpec, h);
            return;
        }
        throw new IllegalStateException();
    }


    public static synchronized DataBaseResourceCrypto a(long j) throws Exception {
        DataBaseResourceCrypto a2;
        synchronized (DataBaseResourceCrypto.class) {
            a2 = GetInstance(j, 29);
        }
        return a2;
    }



    public static synchronized DataBaseResourceCrypto GetInstance(long j, int i2) throws Exception {
        DataBaseResourceCrypto dataBaseResourceCrypto;
        synchronized (DataBaseResourceCrypto.class) {
            String str = i2 + "_" + j;
            DataBaseResourceCrypto dataBaseResourceCrypto2 = i.get(str);
            if (dataBaseResourceCrypto2 != null) {
                return dataBaseResourceCrypto2;
            }
            if (i2 == -1) {
                dataBaseResourceCrypto = new DataBaseResourceCrypto(j, -1);

           } else {
                dataBaseResourceCrypto = new DataBaseResourceCrypto(j, i2);
            }
            i.put(str, dataBaseResourceCrypto);
            return dataBaseResourceCrypto;
        }
    }
   // public static byte[] bbb = new byte[128];


    //解密函数
    public String decrypt(String str) throws CipherException {
        String str2;
//        if (d5.a.a.b.f.a((CharSequence) str) || str.equals(MessageFormatter.DELIM_STR) || str.equals("[]")) {
//            return str;
//        }
        try {
            synchronized (this.c) {
                str2 = new String(this.c.doFinal(base64decode(str)), "UTF-8");
            }
            return str2;
        } catch (UnsupportedEncodingException | GeneralSecurityException e2) {
            synchronized (DataBaseResourceCrypto.class) {
            //    f<String, DataBaseResourceCrypto> fVar = i;
             //   fVar.remove(this.d + u4.a.a.a.o.d.b.ROLL_OVER_FILE_NAME_SEPARATOR + this.e);
                throw new CipherException(e2);
            }
        }
    }

}