package mygrationTest;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import kr.co.modernwave.log.Logger;
import kr.co.modernwave.nano.DefaultMethodContext;

public class SyncContext extends DefaultMethodContext {

    private Migrator migrator;

    public SyncContext(Migrator migrator) {
        this.migrator = migrator;
    }

    public Response POST(IHTTPSession session) {

        Logger.Write.info("Manual POST SyncContext data...");

        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();

        try {
            Logger.Write.info("Manual Starting migration process...");
            migrator.migrate();
            // fail 떠도 status ok 반환
        }catch (Exception e) {
            map.put("result","fail");
            map.put("reason", "");
            return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", gson.toJson(map));
        }
        // 성공
        Logger.Write.info("Manual migration process success...");
        map.put("result","success");
        Response response = NanoHTTPD.newFixedLengthResponse(Response.Status.OK, "application/json", gson.toJson(map));
        return response;
    }

}
