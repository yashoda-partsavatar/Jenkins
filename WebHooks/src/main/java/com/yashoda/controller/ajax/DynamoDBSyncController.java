/*
package com.yashoda.controller.ajax;

import com.jcabi.aspects.Loggable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DynamoDBSyncController {

    @SneakyThrows
    @RequestMapping(value = "dynamodb-sync", method = RequestMethod.POST)
    @ResponseBody
    public DynamoDBSyncResponse dynamoDBSync(@RequestBody final DynamoDBSyncRequest request) {
        String tableName = request.getTableName();
        switch (tableName) {
            case "Orders":
                String email = request.getKeys().get("Email").get("S");
                String orderId = request.getKeys().get("OrderId").get("S");
                break;
            case "Shipments":
                String shipmentId = request.getKeys().get("ShipmentId").get("S");
                break;
            case "Transactions":
                String transactionId = request.getKeys().get("TransactionId").get("S");
                break;
        }
        return new DynamoDBSyncResponse(true, "");
    }

    @Data
    @AllArgsConstructor
    public static class DynamoDBSyncResponse {
        private boolean success;
        private String message;
    }

    @Data
    @AllArgsConstructor
    public static class DynamoDBSyncRequest {
        final String tableName;
        final Map<String, Map<String, String>> keys;
    }

}
*/
