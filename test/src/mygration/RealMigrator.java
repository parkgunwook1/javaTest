package mygration;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RealMigrator {

    private Connection origin;
    private Connection target;

    public RealMigrator(Connection origin, Connection target) {
        this.origin = origin;
        this.target = target;
    }

    public void migrate() {
        String selectSql = "SELECT TEAM_CODE AS DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUMBER AS KEY_NUM, ALIMTALK_NUMBER, RIGHT(KEY_NUMBER, 4) AS DN, AUTO_MIGRATION "
                + "FROM EMP_TEAM "
                + "WHERE VIEW_YN = 'Y' AND USE_YN = 'Y'";

        String upsertSql = "REPLACE INTO tb_dept (DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUM, ALIMTALK_NUMBER, DN, AUTO_MIGRATION) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement selectStmt = origin.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement insertStmt = target.prepareStatement(upsertSql)) {

            while (rs.next()) {
                String autoMigration = rs.getString("AUTO_MIGRATION");

                if (autoMigration.equals("Y")) {
                    // "autoMigration"이 "Y"인 경우, 자동으로 처리한다.
                    setParameters(insertStmt, rs);
                    insertStmt.executeUpdate();
                } else if (autoMigration.equals("N")) {
                    // "autoMigration"이 "N"인 경우, 사용자의 개입을 확인하여 처리한다.
                    if (isUserIntervention()) {
                        // 사용자의 개입이 확인되면 수동으로 처리한다.
                        setParameters(insertStmt, rs);
                        insertStmt.executeUpdate();
                    }
                } else {
                    // "autoMigration" 값이 다른 경우에 대한 처리 (예: 예외 처리)
                    // 여기서는 로그를 출력하는 방식으로 예시로 제시함
                    System.err.println("잘못된 autoMigration 값: " + autoMigration);
                }
            }
            System.out.println("마이그레이션 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PreparedStatement에 파라미터 설정
    private void setParameters(PreparedStatement stmt, ResultSet rs) throws SQLException {
        String key = keyOfNull(rs);
        String dn = dnOfNull(rs);

        stmt.setInt(1, rs.getInt("DEPT_ID"));
        stmt.setInt(2, rs.getInt("TEAM_TYPE"));
        stmt.setString(3, rs.getString("TEAM_NAME"));
        stmt.setString(4, key);
        stmt.setString(5, rs.getString("ALIMTALK_NUMBER"));
        stmt.setString(6, dn);
        stmt.setString(7, rs.getString("AUTO_MIGRATION"));
    }

    private String keyOfNull(ResultSet rs) throws SQLException {
        String key = rs.getString("KEY_NUM");
        return key != null ? key : "";
    }

    private String dnOfNull(ResultSet rs) throws SQLException {
        String dn = rs.getString("DN");
        return dn != null ? dn : "";
    }

    // 사용자의 개입 여부를 확인하는 메서드
    private boolean isUserIntervention() {
        try {
            // 사용자의 개입 여부를 확인하기 위한 URL
            String urlToCheck = "http://example.com/userInterventionCheck";

            // URL에 연결하여 상태 코드 확인
            URL url = new URL(urlToCheck);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            // HTTP 상태 코드 200(OK)를 받으면 사용자 개입으로 간주
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            // 예외 발생 시, 사용자 개입으로 간주하지 않음
            return false;
        }
    }
}