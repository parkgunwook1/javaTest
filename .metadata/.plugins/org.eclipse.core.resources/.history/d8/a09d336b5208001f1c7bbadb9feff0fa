package mygration;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


public class Application {
    public static void main(String[] args) {

        /*
        *
        * insert 하는것을 따로 만들어줘야 한다. 근데 그 데이터는 어디있냐?
            =>  mssql db에 접근해서 select 한다.
                => 그리고 insert 하는것이다.
        * */

        Properties p = null;
        try {
            // config.xml 파일 읽어온다.
            p = GetProperties("config.xml");
        } catch (InvalidPropertiesFormatException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }

        // config.xml 포트 가져옴.
        int port = Integer.parseInt(p.getProperty("HTTP.PORT"));

        try {
            String originDriver = p.getProperty("origin.Driver");
            Class.forName(originDriver);

            String originUrl = p.getProperty("origin.Url");
            String originUser = p.getProperty("origin.User");
            String originPassword = p.getProperty("origin.Password");
            Connection origin = DriverManager.getConnection(originUrl, originUser, originPassword);

            String targetDriver = p.getProperty("target.Driver");
            Class.forName(targetDriver);

            String targetUrl = p.getProperty("target.Url");
            String targetUser = p.getProperty("target.User");
            String targetPassword = p.getProperty("target.Password");
            Connection target = DriverManager.getConnection(targetUrl, targetUser, targetPassword);

            // origin, target 커넥션 넘김
            Migrator migrator = new Migrator(origin, target);

            // NanoServer 사용하여 NanoHTTPD 웹 서버 생성하고 설정한 포트 번호에서 요청 수신
            NanoServer httpServer = new NanoServer(port);
            httpServer.createNanoHttpServer();

            // 수동 등록
            httpServer.addContext("/empteam/sync", new SyncContext(migrator));
            // "/empteam/auto-sync" 경로로 들어오는 요청은 AutoSyncContext 클래스로 커넥션을 넘김
//            httpServer.addContext("/empteam/auto-sync", new AutoSyncContext(migrator));

            // 자동 등록
            // migrator 바로 접근되게 해야함.
            httpServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static Properties GetProperties(String path) throws InvalidPropertiesFormatException, IOException {
        FileInputStream fin = new FileInputStream(path);
        Properties p = new Properties();
        p.loadFromXML(fin);
        return p;
    }
}
