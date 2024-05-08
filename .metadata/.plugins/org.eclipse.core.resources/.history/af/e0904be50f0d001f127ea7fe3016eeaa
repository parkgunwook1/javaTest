package mygrationTest;

import java.io.FileInputStream;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import kr.co.modernwave.log.Logger;

// 자동 크론탭 등록
public class AutoApplication{

    public static void main(String[] args) {

        Properties p = null;
        try {
            // config.xml 파일 읽어온다.
            p = GetProperties("config.xml");
        } catch (InvalidPropertiesFormatException e) {
            Logger.Write.error(e.getMessage(), e);
        } catch (IOException e) {
            Logger.Write.error(e.getMessage(), e);
        }

        // config.xml 포트 가져옴.
        int port = Integer.parseInt(p.getProperty("HTTP.PORT"));

        try {
            Logger.Write.info("Crontab auto migration application...");
            String originDriver = p.getProperty("origin.Driver");
            Class.forName(originDriver);
            Logger.Write.info("Crontab 참좋은여행 driver loaded.");

            String originUrl = p.getProperty("origin.Url");
            String originUser = p.getProperty("origin.User");
            String originPassword = p.getProperty("origin.Password");
            Connection origin = DriverManager.getConnection(originUrl, originUser, originPassword);
            Logger.Write.info("Crontab 참좋은여행 Connection load.");

            String targetDriver = p.getProperty("target.Driver");
            Class.forName(targetDriver);
            Logger.Write.info("Crontab modernwave driver loaded.");

            String targetUrl = p.getProperty("target.Url");
            String targetUser = p.getProperty("target.User");
            String targetPassword = p.getProperty("target.Password");
            Connection target = DriverManager.getConnection(targetUrl, targetUser, targetPassword);
            Logger.Write.info("Crontab modernwave Connection load.");

            // origin, target 커넥션 넘김
            Migrator migrator = new Migrator(origin, target);

            migrator.crontabMigrate();
            Logger.Write.info("Crontab Auto migration start.");
        } catch (Exception e) {
            Logger.Write.error(e.getMessage(), e);
        }
    }

    public static Properties GetProperties(String path) throws InvalidPropertiesFormatException, IOException {
        FileInputStream fin = new FileInputStream(path);
        Properties p = new Properties();
        p.loadFromXML(fin);
        return p;
    }

}

