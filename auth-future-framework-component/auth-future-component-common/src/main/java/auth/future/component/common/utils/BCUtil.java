package auth.future.component.common.utils;

import cn.hutool.core.util.StrUtil;
import  org.apache.commons.codec.binary.Base64;

import java.io.*;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public class BCUtil {
    private static final String[] hex = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"};
    private static final byte[] val = new byte[]{63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63};

    public BCUtil() {
    }

    public static String escape(String s) {
        if (s == null) {
            return "";
        } else {
            StringBuffer sbuf = new StringBuffer();
            int len = s.length();

            for(int i = 0; i < len; ++i) {
                int ch = s.charAt(i);
                if ('A' <= ch && ch <= 'Z') {
                    sbuf.append((char)ch);
                } else if ('a' <= ch && ch <= 'z') {
                    sbuf.append((char)ch);
                } else if ('0' <= ch && ch <= '9') {
                    sbuf.append((char)ch);
                } else if (ch != '-' && ch != '_' && ch != '.' && ch != '!' && ch != '~' && ch != '*' && ch != '/' && ch != '(' && ch != ')') {
                    if (ch <= 127) {
                        sbuf.append('%');
                        sbuf.append(hex[ch]);
                    } else {
                        sbuf.append('%');
                        sbuf.append('u');
                        sbuf.append(hex[ch >>> 8]);
                        sbuf.append(hex[255 & ch]);
                    }
                } else {
                    sbuf.append((char)ch);
                }
            }

            return sbuf.toString();
        }
    }

    public static String unescape(String s) {
        if (s == null) {
            return "";
        } else {
            StringBuffer sbuf = new StringBuffer();
            int i = 0;

            for(int len = s.length(); i < len; ++i) {
                int ch = s.charAt(i);
                if ('A' <= ch && ch <= 'Z') {
                    sbuf.append((char)ch);
                } else if ('a' <= ch && ch <= 'z') {
                    sbuf.append((char)ch);
                } else if ('0' <= ch && ch <= '9') {
                    sbuf.append((char)ch);
                } else if (ch != '-' && ch != '_' && ch != '.' && ch != '!' && ch != '~' && ch != '*' && ch != '/' && ch != '(' && ch != ')') {
                    if (ch != '%') {
                        sbuf.append((char)ch);
                    } else {
                        int cint = 0;
                        if (len > i + 2 && 'u' != s.charAt(i + 1) && isHexChar(s.charAt(i + 1)) && isHexChar(s.charAt(i + 2))) {
                            cint = cint << 4 | val[s.charAt(i + 1)];
                            cint = cint << 4 | val[s.charAt(i + 2)];
                            i += 2;
                        } else if (len > i + 5 && 'u' == s.charAt(i + 1) && isHexChar(s.charAt(i + 2)) && isHexChar(s.charAt(i + 3)) && isHexChar(s.charAt(i + 4)) && isHexChar(s.charAt(i + 5))) {
                            cint = cint << 4 | val[s.charAt(i + 2)];
                            cint = cint << 4 | val[s.charAt(i + 3)];
                            cint = cint << 4 | val[s.charAt(i + 4)];
                            cint = cint << 4 | val[s.charAt(i + 5)];
                            i += 5;
                        } else {
                            cint = ch;
                        }

                        sbuf.append((char)cint);
                    }
                } else {
                    sbuf.append((char)ch);
                }
            }

            return sbuf.toString();
        }
    }

    public static String encode(byte[] data) {
        try {
            return new String(Base64.encodeBase64(data));
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static String encode(String data, String charset) {
        try {
            return StrUtil.isNotEmpty(charset) ? encode(data.getBytes(charset)) : encode(data.getBytes());
        } catch (RuntimeException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String encode(ByteArrayOutputStream data) {
        return encode(data.toByteArray());
    }

    public static String encode(String data) {
        return encode(data.getBytes());
    }

    public static byte[] decode(byte[] data) {
        try {
            return Base64.decodeBase64(data);
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static String decode(String data, String charset) {
        try {
            return StrUtil.isNotEmpty(charset) ? byteArrayToStr(decode(data.getBytes(charset))) : byteArrayToStr(decode(data.getBytes()));
        } catch (RuntimeException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String decode(String data) {
        return byteArrayToStr(decode(data.getBytes()));
    }

    public static String byteArrayToHexStr(byte[] data) {
        if (data == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < data.length; ++i) {
                String hex = Integer.toHexString(data[i] & 255);
                if (hex.length() == 1) {
                    sb.append('0');
                }

                sb.append(hex);
            }

            return sb.toString().toUpperCase();
        }
    }

    public static byte[] hexStrToByteArray(String data) {
        if (data == null) {
            return null;
        } else if (data.length() < 1) {
            return new byte[0];
        } else {
            byte[] result = new byte[data.length() / 2];

            for(int i = 0; i < data.length() / 2; ++i) {
                int high = Integer.parseInt(data.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(data.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }

    public static String byteArrayToStr(byte[] data) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            String var2;
            try {
                os.write(data);
                var2 = os.toString().trim();
            } finally {
                os.close();
            }

            return var2;
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static boolean isHexChar(char c) {
        return c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F' || c >= '0' && c <= '9';
    }

    public static byte[] objToByteArray(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;

        byte[] var3;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            var3 = bos.toByteArray();
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        } finally {
            try {
                bos.close();
            } catch (IOException var11) {
            }

        }

        return var3;
    }

    public static Object byteArrayToObj(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInput in = null;

        Object var3;
        try {
            in = new ObjectInputStream(bis);
            var3 = in.readObject();
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var11) {
            }

        }

        return var3;
    }

    public static String encodeBase64(byte[] data) {
        return Base64.encodeBase64String(data);
    }

    public static byte[] decodeBase64(String data) {
        return Base64.decodeBase64(data);
    }

    public static byte[] copyByteArray(byte[] data) {
        return copyByteArray(data, 0, data.length);
    }

    public static byte[] copyByteArray(byte[] data, int pos, int len) {
        byte[] ret = new byte[len];
        System.arraycopy(data, pos, ret, 0, len);
        return ret;
    }
}
