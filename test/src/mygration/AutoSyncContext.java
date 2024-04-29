package mygration;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


// 자동
// /empteam/auto-sync
public class AutoSyncContext extends DefaultMethodContext {

    private Migrator migrator;

    public AutoSyncContext(Migrator migrator) {
        this.migrator = migrator;
    }

    public Response POST(IHTTPSession session) {

        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();

        try {
            migrator.autoMigrate();
            // fail 떠도 status ok 반환
        }catch (Exception e) {
            map.put("result","fail");
            map.put("reason", "");
            return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", gson.toJson(map));
        }
        map.put("result","success");
        Response response = NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", gson.toJson(map));
        return response;
    }

}