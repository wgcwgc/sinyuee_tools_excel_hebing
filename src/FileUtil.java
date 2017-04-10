/*
 * @(#)FileUtil.class 0.100 2009-2-20
 *
 * Copyright 2007 Smarch.
 * All rights reserved.
 */


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author runasea
 * @version 0.1.0.1, 2009-2-20
 * @since SMARCH0.1
 */
public class FileUtil {

  /**
   * ��ȡĳ���ļ���
   * @param f �ļ�
   * @return ����
   */
  public static byte[] readFile(final File f) {
    byte[] data = new byte[(int) f.length()];
    DataInputStream dis;
    try {
      dis = new DataInputStream(
          new FileInputStream(f));
      dis.readFully(data);
      dis.close();
      return data;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * �����ж�ȡ�ļ���
   * @param is �ļ���
   * @return ����
   */
  public static byte[] readFile(final InputStream is) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataInputStream dis = new DataInputStream(is);
    try {
      while (dis.available() > 0) {
        byte[] data = new byte[dis.available()];
        dis.readFully(data);
        baos.write(data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return baos.toByteArray();
  }

  /**
   * ���ĳ���ļ���
   * @param f �ļ�
   * @param data ����
   */
  public static void writeFile(final File f, final byte[] data) {
    if (f.exists()) f.delete();
    FileOutputStream fos;
    try {
      fos = new FileOutputStream(f);
      fos.write(data);
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * ��ȡĳ���ı��ļ���
   * @param f �ļ�
   * @return �ı�����
   */
  public static String readFileText(final File f) {
    try {
      return new String(readFile(f), "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * �����ж�ȡĳ���ı��ļ���
   * @param is �ļ���
   * @return �ı�����
   */
  public static String readFileText(final InputStream is) {
    return new String(readFile(is));
  }

  /**
   * ���ĳ���ı��ļ���
   * @param f �ļ�
   * @param s �ı�
   */
  public static void writeFileText(final File f, final String s) {
    writeFile(f, s.getBytes());
  }

  /**
   * �����ļ���
   * @param src Դ
   * @param dest Ŀ��
   */
  public static void copyFile(File src, File dest) {
    writeFile(dest, readFile(src));
  }

  /**
   * ��ȡ����ע�͵��ļ���
   * @param f �ļ�
   * @return ȥ��ע�Ͳ��ֵ��ı�
   */
  public static String readCommitFile(final File f) {
    String s = new String(readFile(f));
    String tmp = "";
    boolean commit = false;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '#') {
        commit = true;
      }
      if (commit) {
        if (ch == '\r' || ch == '\n')
          commit = false;
      } else {
        tmp += ch;
      }
    }
    return tmp;
  }

  public static void main(final String[] cmd) {
    System.out.println(readCommitFile(new File("doc/api_trans.txt")));
  }

}
