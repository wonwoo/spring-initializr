package me.wonwoo.web;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by wonwoo on 2016. 11. 20..
 */
public class Compress {

  public static void unzip(File zippedFile) throws IOException {
    unzip(zippedFile, Charset.defaultCharset().name());
  }

  public static void unzip(File zippedFile, String charsetName) throws IOException {
    unzip(zippedFile, zippedFile.getParentFile(), charsetName);
  }

  public static void unzip(File zippedFile, File destDir) throws IOException {
    unzip(new FileInputStream(zippedFile), destDir, Charset.defaultCharset().name());
  }

  public static void unzip(File zippedFile, File destDir, String charsetName)
    throws IOException {
    unzip(new FileInputStream(zippedFile), destDir, charsetName);
  }

  public static void unzip(InputStream is, File destDir) throws IOException {
    unzip(is, destDir, Charset.defaultCharset().name());
  }

  /**
   * test 라 대충 설정
   * @param is
   * @param destDir
   * @param charsetName
   * @throws IOException
   */

  public static void unzip(InputStream is, File destDir, String charsetName)
    throws IOException {
    ZipArchiveInputStream zis;
    ZipArchiveEntry entry;
    String name;
    File target;
    int nWritten = 0;
    BufferedOutputStream bos;
    byte[] buf = new byte[1024 * 8];

    zis = new ZipArchiveInputStream(is, charsetName, false);
    while ((entry = zis.getNextZipEntry()) != null) {
      name = entry.getName();
      target = new File(destDir, name);
      if (entry.isDirectory()) {
        target.mkdirs(); /*  does it always work? */
      } else {
        target.createNewFile();
        bos = new BufferedOutputStream(new FileOutputStream(target));
        while ((nWritten = zis.read(buf)) >= 0) {
          bos.write(buf, 0, nWritten);
        }
        bos.close();
      }
    }
    zis.close();
  }

  public static void unTar(File zippedFile, File destDir) throws IOException {
    unTar(new FileInputStream(zippedFile), destDir, Charset.defaultCharset().name());
  }

  public static void unTar(InputStream is, File destDir, String charsetName)
    throws IOException {
    TarArchiveInputStream tar;
    GzipCompressorInputStream gzIn;
    TarArchiveEntry entry;
    String name;
    File target;
    int nWritten = 0;
    BufferedOutputStream bos;
    byte[] buf = new byte[1024 * 8];

    gzIn = new GzipCompressorInputStream(is);
    tar = new TarArchiveInputStream(gzIn,charsetName);
    while ((entry = tar.getNextTarEntry()) != null) {
      name = entry.getName();
      target = new File(destDir, name);
      if (entry.isDirectory()) {
        target.mkdirs(); /*  does it always work? */
      } else {
        target.createNewFile();
        bos = new BufferedOutputStream(new FileOutputStream(target));
        while ((nWritten = tar.read(buf)) >= 0) {
          bos.write(buf, 0, nWritten);
        }
        bos.close();
      }
    }
    tar.close();
  }
}
