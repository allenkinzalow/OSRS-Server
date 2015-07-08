package com.kinztech.os.utilities;

/**
 * Created by Allen Kinzalow on 6/4/2015.
 */
public class StringUtils {

    static final char[] aCharArray723 = new char[]{'\u20ac', '\u0000', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020', '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\u0000', '\u017d', '\u0000', '\u0000', '\u2018', '\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\u0000', '\u017e', '\u0178'};

    public static String createString(byte[] textBytes, int var1, int textLength, int var3) {
        try {
            char[] textCharacters = new char[textLength];
            int characterCount = 0;

            for(int var6 = 0; var6 < textLength; ++var6) {
                int textChar = textBytes[var1 + var6] & 255;
                if(0 != textChar) {
                    if(textChar >= 128 && textChar < 160) {
                        char var8 = aCharArray723[textChar - 128];
                        if(0 == var8) {
                            var8 = 63;
                        }
                        textChar = var8;
                    }
                    textCharacters[characterCount++] = (char)textChar;
                }
            }

            return new String(textCharacters, 0, characterCount);
        } catch (RuntimeException var9) {
            return "";
        }
    }

    /*public static byte[] getByteArray(CharSequence var0, byte var1) {
            int var2 = var0.length();
            byte[] var3 = new byte[var2];
            for(int var4 = 0; var4 < var2; ++var4) {
                char var5;
                label224: {
                    var5 = var0.charAt(var4);
                    if(var5 > 0) {
                        if(var5 < 128) {
                            break label224;
                        }
                    }

                    if(var5 >= 160) {

                        if(var5 <= 255) {
                            break label224;
                        }
                    }

                    if(var5 == 8364) {
                        var3[var4] = -128;
                    } else if(var5 == 8218) {

                        var3[var4] = -126;
                    } else if(402 == var5) {

                        var3[var4] = -125;
                    } else if(var5 == 8222) {

                        var3[var4] = -124;
                    } else if(8230 == var5) {

                        var3[var4] = -123;
                    } else if(var5 == 8224) {

                        var3[var4] = -122;
                    } else if(8225 == var5) {

                        var3[var4] = -121;
                    } else if(var5 == 710) {

                        var3[var4] = -120;
                    } else if(8240 == var5) {

                        var3[var4] = -119;
                    } else if(var5 == 352) {
                        var3[var4] = -118;
                    } else if(8249 == var5) {

                        var3[var4] = -117;
                    } else if(338 == var5) {

                        var3[var4] = -116;
                    } else if(var5 == 381) {

                        var3[var4] = -114;
                    } else if(var5 == 8216) {
                        var3[var4] = -111;
                    } else if(8217 == var5) {
                        var3[var4] = -110;
                    } else if(var5 == 8220) {
                        var3[var4] = -109;
                    } else if(var5 == 8221) {
                        var3[var4] = -108;
                    } else if(8226 == var5) {
                        var3[var4] = -107;
                    } else if(var5 == 8211) {
                        var3[var4] = -106;
                    } else if(8212 == var5) {
                        var3[var4] = -105;
                    } else if(732 == var5) {
                        var3[var4] = -104;
                    } else if(var5 == 8482) {
                        var3[var4] = -103;
                    } else if(var5 == 353) {
                        if(var1 != 0) {
                        var3[var4] = -102;
                    } else if(8250 == var5) {
                        var3[var4] = -101;
                    } else if(var5 == 339) {
                        var3[var4] = -100;
                    } else if(var5 == 382) {
                        var3[var4] = -98;
                    } else if(376 == var5) {
                        var3[var4] = -97;
                    } else {
                        var3[var4] = 63;
                    }
                    continue;
                }

                var3[var4] = (byte)var5;
            }

            return var3;
    }*/

}
