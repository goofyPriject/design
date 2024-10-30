package cn.aireco.platform.sdk.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FtpUploadExample {
    public static void main(String[] args) {
        String server = "192.168.121.101";
        int port = 21;
        String user = "jdds";
        String pass = "Zjmiec_jd18";

        FTPClient ftpClient = new FTPClient();
        try {
            // Connect to FTP server
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);

            // Set file type to binary (important for images or other binary files)
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Set passive mode for connection
            ftpClient.enterLocalPassiveMode();

            // Specify target file path on FTP server
            String remoteFile = "/test/客户报装数据20240910.xlsx";
            String localFile = "D:\\googledownload/文件/客户报装数据20240910.xlsx";

            // Upload file
            try (FileInputStream fis = new FileInputStream(localFile)) {
                boolean result = ftpClient.storeFile(remoteFile, fis);
                if (result) {
                    System.out.println("File has been uploaded successfully.");
                } else {
                    System.out.println("File upload failed.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Logout and disconnect
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
