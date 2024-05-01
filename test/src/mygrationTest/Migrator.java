package mygrationTest;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.modernwave.log.Logger;

public class Migrator {

    private Connection origin;
    private Connection target;

    public Migrator(Connection origin, Connection target) {
        this.origin = origin;
        this.target = target;
    }

    // 자동 => 데이터 무엇이 select 되고, insert 되는지 확인 해봐야할 필요 있음.
    public void crontabMigrate() {
        String selectSql = "SELECT TEAM_CODE AS DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUMBER AS KEY_NUM, ALIMTALK_NUMBER, RIGHT(KEY_NUMBER, 4) AS DN, AUTO_MIGRATION "
                + "FROM EMP_TEAM "
                + "WHERE VIEW_YN = 'Y' AND USE_YN = 'Y'";

        String deleteSql = "DELETE FROM TB_DEPT WHERE AUTO_MIGRATION = 'Y'";

        String insertSql = "INSERT INTO tb_dept (DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUM, ALIMTALK_NUMBER, DN, AUTO_MIGRATION)"
                + "VALUES (?, ?, ?, ?, ?, ?, 'Y')";

        List<String> selectDataList = new ArrayList<>();
        List<String> insertDataList = new ArrayList<>();

        try (PreparedStatement selectStmt = origin.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement deleteStmt = target.prepareStatement(deleteSql);
             PreparedStatement insertStmt = target.prepareStatement(insertSql)) {

            int deleteData = deleteStmt.executeUpdate();
            Logger.Write.info("Crontab Delete records count: " + deleteData);

            while (rs.next()) {
                // 데이터를 받고
                String selectData = rs.getString("DEPT_ID") + "," +
                        rs.getString("TEAM_TYPE") + "," +
                        rs.getString("TEAM_NAME") + "," +
                        rs.getString("KEY_NUM") + "," +
                        rs.getString("ALIMTALK_NUMBER") + "," +
                        rs.getString("DN");

                selectDataList.add(selectData);

                setParameters(insertStmt, rs);
                insertStmt.executeUpdate();

                String insertData = rs.getString("DEPT_ID") + "," +
                        rs.getString("TEAM_TYPE") + "," +
                        rs.getString("TEAM_NAME") + "," +
                        rs.getString("KEY_NUM") + "," +
                        rs.getString("ALIMTALK_NUMBER") + "," +
                        rs.getString("DN");

                insertDataList.add(insertData);
            }
            Logger.Write.info("Select Data:");
            for (String data : selectDataList) {
                Logger.Write.info(data);
            }

            Logger.Write.info("Insert Data:");
            for (String data : insertDataList) {
                Logger.Write.info(data);
            }

            Logger.Write.info("Crontab insert Success...");
        } catch (SQLException e) {
            Logger.Write.error(e.getMessage(), e);
        }
    }
    // 수동 => 백업 데이터용
    public void migrate() {
        String selectSql = "SELECT TEAM_CODE AS DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUMBER AS KEY_NUM, ALIMTALK_NUMBER, RIGHT(KEY_NUMBER, 4) AS DN, AUTO_MIGRATION "
                + "FROM EMP_TEAM "
                + "WHERE VIEW_YN = 'Y' AND USE_YN = 'Y'";

        String deleteSql = "DELETE FROM TB_DEPT WHERE AUTO_MIGRATION = 'N'";

        String insertSql = "INSERT INTO tb_dept (DEPT_ID, TEAM_TYPE, TEAM_NAME, KEY_NUM, ALIMTALK_NUMBER, DN, AUTO_MIGRATION) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'N')";

        try (PreparedStatement selectStmt = origin.prepareStatement(selectSql);
             ResultSet rs = selectStmt.executeQuery();
             PreparedStatement deleteStmt = target.prepareStatement(deleteSql);
             PreparedStatement insertStmt = target.prepareStatement(insertSql)) {

            // 삭제
            int deletedRows = deleteStmt.executeUpdate();
            Logger.Write.info("Manual Deleted records count: " + deletedRows);


            while (rs.next()) {
                if (rs != null) {
                    setParameters(insertStmt, rs);
                    insertStmt.executeUpdate();
                }
            }
            Logger.Write.info("Manual insert Success");
        } catch (SQLException e) {
            Logger.Write.error(e.getMessage(), e);
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
