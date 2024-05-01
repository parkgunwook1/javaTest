package mygrationTest;

import java.io.FileInputStream;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Logger;

import fi.iki.elonen.NanoHTTPD;
import kr.co.modernwave.log.Logger;
import kr.co.modernwave.nano.NanoServer;

public class Application {

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
        // config.xml 에서 mode를 읽어온다.
        String mode = p.getProperty("mode");

        switch (mode) {
            case "auto":
                AutoMigration(p);
                break;
            case "manual":
                ManualMigration(p);
                break;
            default:
                Logger.Write.info("config.xml 에서 mode를 선택하시오");
                break;

        }
    }

    private static void AutoMigration(Properties p) {

        try {
            Logger.Write.info("Manual Starting migration application...");

//            String originDriver = p.getProperty("origin.Driver");
//            Class.forName(originDriver);
//            Logger.Write.info("Manual 참좋은여행 driver loaded.");
//
//            String originUrl = p.getProperty("origin.Url");
//            String originUser = p.getProperty("origin.User");
//            String originPassword = p.getProperty("origin.Password");
            Connection origin = getConnection(p , "origin");
            Logger.Write.info("Manual 참좋은여행 Connection load.");



//            String targetDriver = p.getProperty("target.Driver");
//            Class.forName(targetDriver);
//            Logger.Write.info("Manual modernwave mysql driver loaded.");
//
//            String targetUrl = p.getProperty("target.Url");
//            String targetUser = p.getProperty("target.User");
//            String targetPassword = p.getProperty("target.Password");
            Connection target = getConnection(p , "target");
            Logger.Write.info("Manual modernwave Connection load.");

            Migrator migrator = new Migrator(origin, target);

            // 자동 insert
            migrator.crontabMigrate();
        } catch (Exception e) {
            Logger.Write.error(e.getMessage(), e);
        }

    }

    private static void ManualMigration(Properties p) {
        int port = Integer.parseInt(p.getProperty("HTTP.PORT"));
        try {
            Logger.Write.info("Manual Starting migration application...");

//            String originDriver = p.getProperty("origin.Driver");
//            Class.forName(originDriver);
//            Logger.Write.info("Manual 참좋은여행 driver loaded.");
//
//            String originUrl = p.getProperty("origin.Url");
//            String originUser = p.getProperty("origin.User");
//            String originPassword = p.getProperty("origin.Password");
            Connection origin = getConnection(p , "origin");
            Logger.Write.info("Manual 참좋은여행 Connection load.");

//            String targetDriver = p.getProperty("target.Driver");
//            Class.forName(targetDriver);
//            Logger.Write.info("Manual modernwave mysql driver loaded.");
//
//            String targetUrl = p.getProperty("target.Url");
//            String targetUser = p.getProperty("target.User");
//            String targetPassword = p.getProperty("target.Password");
            Connection target = getConnection(p , "target");
            Logger.Write.info("Manual modernwave Connection load.");

            Migrator migrator = new Migrator(origin, target);

            // NanoServer 사용하여 NanoHTTPD 웹 서버 생성하고 설정한 포트 번호에서 요청 수신
            NanoServer httpServer = new NanoServer(port);
            httpServer.createNanoHttpServer();
            // 수동 insert
            httpServer.addContext("/empteam/sync", new SyncContext(migrator));
            httpServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            Logger.Write.info("Manual HTTP server start.");
        } catch (Exception e) {
            Logger.Write.error(e.getMessage(), e);
        }
    }

    // Connection 모듈화
    private static Connection getConnection(Properties p, String type) throws Exception {
            String driver = p.getProperty(type,".Driver");
            String url = p.getProperty(type,".Url");
            String user = p.getProperty(type,".User");
            String password = p.getProperty(type,".Password");
            Class.forName(driver);
            Logger.Write.info("Driver loaded for " + type);
            return DriverManager.getConnection(url, user, password);
    }

    public static Properties GetProperties(String path) throws InvalidPropertiesFormatException, IOException {
        FileInputStream fin = new FileInputStream(path);
        Properties p = new Properties();
        p.loadFromXML(fin);
        return p;
    }

}

