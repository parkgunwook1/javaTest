package mygration;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Migrator {

    private Connection origin;
    private Connection target;

    public Migrator(Connection origin, Connection target) {
        this.origin = origin;
        this.target = target;
    }
    // 자동 등록
    public void autoMigrate() {
        String selectSql = "SELECT TEAM_CODE AS DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUMBER AS KEY_NUM, ALIMTALK_NUMBER, RIGHT(KEY_NUMBER, 4) AS DN, AUTO_MIGRATION "
                + "FROM EMP_TEAM "
                + "WHERE VIEW_YN = 'Y' AND USE_YN = 'Y'";

        String upsertSql = "REPLACE INTO tb_dept (DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUM, ALIMTALK_NUMBER, DN, AUTO_MIGRATION) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
                // Origin 데이터베이스로부터 팀 정보를 가져오기 위한 PreparedStatement
                PreparedStatement selectStmt = origin.prepareStatement(selectSql);
                // 대상 데이터베이스에 데이터를 삽입하기 위한 PreparedStatement
                PreparedStatement insertStmt = target.prepareStatement(upsertSql);
                ResultSet rs = selectStmt.executeQuery();
        ) {
            // Origin 데이터베이스에서 팀 정보를 가져와서 대상 데이터베이스에 삽입
            while (rs.next()) {
                // 팀 정보를 PreparedStatement에 설정
                setParameters(insertStmt, rs);
                // 삽입 실행
                insertStmt.executeUpdate();
            }
            // 성공 메시지 출력
            System.out.println("자동 마이그레이션이 완료되었습니다.");
        } catch (SQLException e) {
            // 예외 발생 시 에러 메시지 출력
            e.printStackTrace();
        }
    }


    public void migrate() {
        String selectSql = "SELECT TEAM_CODE AS DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUMBER AS KEY_NUM, ALIMTALK_NUMBER, RIGHT(KEY_NUMBER, 4) AS DN, AUTO_MIGRATION "
                + "FROM EMP_TEAM "
                + "WHERE VIEW_YN = 'Y' AND USE_YN = 'Y'";
        String deleteSql = "DELETE FROM TB_DEPT WHERE AUTO_MIGRATION = 'Y'";
        String upsertSql = "REPLACE INTO tb_dept (DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUM, ALIMTALK_NUMBER, DN, AUTO_MIGRATION) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement selectStmt = origin.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement deleteStmt = target.prepareStatement(deleteSql);
             PreparedStatement insertStmt = target.prepareStatement(upsertSql)) {

            while (rs.next()) {
                String autoMigration = rs.getString("AUTO_MIGRATION");

                // mssql에서 수동 입력 했으면
                if (autoMigration.equals("N")) {
                    // DELETE를 수행한다.
                    deleteStmt.executeUpdate();
                    // DELETE 후 INSERT를 수행한다.
                    setParameters(insertStmt, rs);
                    insertStmt.executeUpdate();
                }
            }
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
}