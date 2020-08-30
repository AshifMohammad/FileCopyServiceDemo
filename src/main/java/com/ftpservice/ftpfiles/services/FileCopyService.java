package com.ftpservice.ftpfiles.services;

import com.ftpservice.ftpfiles.helpers.FTPClientHelper;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileCopyService {

  Logger logger;
  //    private final String server;
  //    private final int port;
  //    private final String user;
  //    private final String password;
  private FTPClient ftp;

  public String copyFiles() {

    new FTPClientHelper("demo.wftpserver.com", 21, "demo", "demo");
    try {
      ftp = new FTPClient();
      ftp.connect("demo.wftpserver.com", 21);
      int replyCode = ftp.getReplyCode();

      if (!FTPReply.isPositiveCompletion(replyCode)) {
        ftp.disconnect();
        throw new IOException("Exception in connecting to FTP Server");
      }
      ftp.login("demo", "demo");
      FTPFile[] files = ftp.listFiles("/upload");
      logger.info("files...");

    } catch (Exception e) {

    } finally {
      return "success";
    }
    //    ftpClientHelper.open();
  }
}
