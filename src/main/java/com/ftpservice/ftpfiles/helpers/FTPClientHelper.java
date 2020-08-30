package com.ftpservice.ftpfiles.helpers;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class FTPClientHelper {

  private final String server;
  private final int port;
  private final String user;
  private final String password;
  private FTPClient ftp;

  public FTPClientHelper(String server, int port, String user, String password) {
    this.server = server;
    this.port = port;
    this.user = user;
    this.password = password;
  }

  public void open() throws IOException {
    ftp = new FTPClient();

    ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

    ftp.connect(server, port);
    int reply = ftp.getReplyCode();
    if (!FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      throw new IOException("Exception in connecting to FTP Server");
    }

    ftp.login(user, password);
  }

  void close() throws IOException {
    ftp.disconnect();
  }

  Collection<String> listFiles(String path) throws IOException {
    FTPFile[] files = ftp.listFiles(path);

    return Arrays.stream(files).map(FTPFile::getName).collect(Collectors.toList());
  }

  void putFileToPath(File file, String path) throws IOException {
    ftp.storeFile(path, new FileInputStream(file));
  }

  void downloadFile(String source, String destination) throws IOException {
    FileOutputStream out = new FileOutputStream(destination);
    ftp.retrieveFile(source, out);
    out.close();
  }

  //  public String copyFileFromFTP() {
  //    return "i am in the FTP helper and openning FTP";
  //  }
}
