package de.hawk200014;

import spark.Request;
import spark.Response;

public class RequestProcessor {

    public Response processData(Request request, Response response){

        String body = request.body();
        response.status(200);
        return response;
    }


}
