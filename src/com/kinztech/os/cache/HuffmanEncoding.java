package com.kinztech.os.cache;


import nl.bartpelle.dawnguard.DataStore;

/**
 * Created by Allen Kinzalow on 6/4/2015.
 */
public class HuffmanEncoding {

    byte[] chatBitSizes;
    int[] chatDecryptKeys;
    int[] chatMask;

    public static HuffmanEncoding huffmanEncoding;

    public static void initialize(DataStore fileStore) {
        huffmanEncoding = new HuffmanEncoding(fileStore.getIndex(10).getContainerByName("huffman").getFileData(0));
    }

    public int encrypt(byte[] var1, int var2, int var3, byte[] var4, int var5, int var6) {
        try {
            int var7 = 0;
            int var10 = var5 << 3;

            for(var3 += var2; var2 < var3; ++var2) {
                int textByte = var1[var2] & 255;
                int mask = this.chatMask[textByte];
                byte size = this.chatBitSizes[textByte];
                if(size == 0) {
                    throw new RuntimeException("");
                }

                int var8 = var10 >> 3;
                int var13 = var10 & 7;
                var7 &= -var13 >> 31;
                int var9 = var8 + (size + var13 - 1 >> 3);
                var13 += 24;
                var4[var8] = (byte)(var7 |= mask >>> var13);
                if(var8 >= var9) {
                    if(var6 >= 1902557504) {
                        throw new IllegalStateException();
                    }
                } else {
                    ++var8;
                    var13 -= 8;
                    var4[var8] = (byte)(var7 = mask >>> var13);
                    if(var8 < var9) {
                        ++var8;
                        var13 -= 8;
                        var4[var8] = (byte)(var7 = mask >>> var13);
                        if(var8 >= var9) {
                            if(var6 >= 1902557504) {
                                throw new IllegalStateException();
                            }
                        } else {
                            ++var8;
                            var13 -= 8;
                            var4[var8] = (byte)(var7 = mask >>> var13);
                            if(var8 >= var9) {
                                if(var6 >= 1902557504) {
                                    throw new IllegalStateException();
                                }
                            } else {
                                ++var8;
                                var13 -= 8;
                                var4[var8] = (byte)(var7 = mask << -var13);
                            }
                        }
                    }
                }

                var10 += size;
            }

            return (7 + var10 >> 3) - var5;
        } catch (RuntimeException var15) {
            var15.printStackTrace();
            return 0;
        }
    }

    public int decrypt(byte[] buffer, int bufferStartPos, byte[] decryptedArray, int offset, int textSize, byte var6) {
        try {
            if(textSize == 0) {
                if(var6 >= 104) {
                    throw new IllegalStateException();
                } else {
                    return 0;
                }
            } else {
                int var8 = 0;
                textSize += offset;
                int var10 = bufferStartPos;

                while(true) {
                    byte textByte = buffer[var10];
                    if(textByte < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    int var7;
                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            if(var6 >= 104) {
                                throw new IllegalStateException();
                            }
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 64)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 32)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            if(var6 >= 104) {
                                throw new IllegalStateException();
                            }
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 16)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 8)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 4)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 2)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            if(var6 >= 104) {
                                throw new IllegalStateException();
                            }
                            break;
                        }

                        var8 = 0;
                    }

                    if(0 != (textByte & 1)) {
                        if(var6 >= 104) {
                            throw new IllegalStateException();
                        }

                        var8 = this.chatDecryptKeys[var8];
                    } else {
                        ++var8;
                    }

                    if((var7 = this.chatDecryptKeys[var8]) < 0) {
                        decryptedArray[offset++] = (byte)(~var7);
                        if(offset >= textSize) {
                            if(var6 >= 104) {
                                throw new IllegalStateException();
                            }
                            break;
                        }

                        var8 = 0;
                    }

                    ++var10;
                }

                return var10 + 1 - bufferStartPos;
            }
        } catch (RuntimeException var11) {
            var11.printStackTrace();
            return 0;
        }
    }

    public static byte[] getByteArray(CharSequence var0, byte var1) {
        try {
            int var2 = var0.length();
            byte[] var3 = new byte[var2];

            for(int var4 = 0; var4 < var2; ++var4) {
                if(var1 != 0) {
                    throw new IllegalStateException();
                }

                char var5;
                label224: {
                    var5 = var0.charAt(var4);
                    if(var5 > 0) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

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
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -120;
                    } else if(8240 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -119;
                    } else if(var5 == 352) {
                        var3[var4] = -118;
                    } else if(8249 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -117;
                    } else if(338 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -116;
                    } else if(var5 == 381) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -114;
                    } else if(var5 == 8216) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -111;
                    } else if(8217 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -110;
                    } else if(var5 == 8220) {
                        var3[var4] = -109;
                    } else if(var5 == 8221) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -108;
                    } else if(8226 == var5) {
                        var3[var4] = -107;
                    } else if(var5 == 8211) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -106;
                    } else if(8212 == var5) {
                        var3[var4] = -105;
                    } else if(732 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -104;
                    } else if(var5 == 8482) {
                        var3[var4] = -103;
                    } else if(var5 == 353) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -102;
                    } else if(8250 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -101;
                    } else if(var5 == 339) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -100;
                    } else if(var5 == 382) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -98;
                    } else if(376 == var5) {
                        if(var1 != 0) {
                            throw new IllegalStateException();
                        }

                        var3[var4] = -97;
                    } else {
                        var3[var4] = 63;
                    }
                    continue;
                }

                var3[var4] = (byte)var5;
            }

            return var3;
        } catch (RuntimeException var6) {
            var6.printStackTrace();
            return new byte[1];
        }
    }

    public HuffmanEncoding(byte[] var1) {
        try {
            int var2 = var1.length;
            this.chatMask = new int[var2];
            this.chatBitSizes = var1;
            int[] var5 = new int[33];
            this.chatDecryptKeys = new int[8];
            int var6 = 0;

            for(int var3 = 0; var3 < var2; ++var3) {
                byte var8 = var1[var3];
                if(0 != var8) {
                    int var11 = 1 << 32 - var8;
                    int var10 = var5[var8];
                    this.chatMask[var3] = var10;
                    int var4;
                    int var7;
                    int var9;
                    int var13;
                    if((var10 & var11) != 0) {
                        var9 = var5[var8 - 1];
                    } else {
                        var9 = var10 | var11;

                        for(var4 = var8 - 1; var4 >= 1; --var4) {
                            var7 = var5[var4];
                            if(var7 != var10) {
                                break;
                            }

                            var13 = 1 << 32 - var4;
                            if((var7 & var13) != 0) {
                                var5[var4] = var5[var4 - 1];
                                break;
                            }

                            var5[var4] = var7 | var13;
                        }
                    }

                    var5[var8] = var9;

                    for(var4 = 1 + var8; var4 <= 32; ++var4) {
                        if(var5[var4] == var10) {
                            var5[var4] = var9;
                        }
                    }

                    var4 = 0;

                    for(var7 = 0; var7 < var8; ++var7) {
                        var13 = Integer.MIN_VALUE >>> var7;
                        if((var10 & var13) != 0) {
                            if(this.chatDecryptKeys[var4] == 0) {
                                this.chatDecryptKeys[var4] = var6;
                            }

                            var4 = this.chatDecryptKeys[var4];
                        } else {
                            ++var4;
                        }

                        if(var4 >= this.chatDecryptKeys.length) {
                            int[] var12 = new int[2 * this.chatDecryptKeys.length];

                            for(int var14 = 0; var14 < this.chatDecryptKeys.length; ++var14) {
                                var12[var14] = this.chatDecryptKeys[var14];
                            }

                            this.chatDecryptKeys = var12;
                        }

                        var13 >>>= 1;
                    }

                    this.chatDecryptKeys[var4] = ~var3;
                    if(var4 >= var6) {
                        var6 = var4 + 1;
                    }
                }
            }

        } catch (RuntimeException var15) {

        }
    }
}
