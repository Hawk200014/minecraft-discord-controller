package de.hawk200014;
import de.hawk200014.ServerList.ServerList;
import de.hawk200014.ServerList.ServerModel;
import org.json.JSONObject;
public class RequestProcessor {

    public void processData(String body){
        JSONObject jObject = new JSONObject(body);
        JSONObject dataJson = null;
        try {
            String apisecret = jObject.getJSONObject("auth").getString("apisecret");
            if(!((String)Singletons.getInstance().getSingleton("apisecret")).equals(apisecret)){
            }
            dataJson = jObject.getJSONObject("data");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            String method = dataJson.getString("method");
            switch (method){
                case "newPoint":
                    System.out.println(processNewPoint(dataJson));
                    break;
                default:
                    System.out.println(500);
            }
        }
        catch (Exception e){
            System.out.println(500);
        }
    }

    private int processNewPoint(JSONObject jsonObject){
        try{
            String serverIP = jsonObject.getString("serverip");
            String port = jsonObject.getString("port");
            if(serverIP.trim().isEmpty()) return 500;
            if(port.trim().isEmpty()) port = "4567";

            ((ServerList)Singletons.getInstance().getSingleton("serverlist")).addServer(new ServerModel(serverIP,port));
            return 200;
        }
        catch (Exception e){
            return 500;
        }
    }


}
