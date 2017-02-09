package com.company.learn.ActiveMQ;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by dell on 2016/9/28.
 */
public class Listener implements MessageListener {

    public void onMessage(Message message){
        try {
            MapMessage map = (MapMessage)message;
            String stock = map.getString("stock");
            double price = map.getDouble("price");
            double offer = map.getDouble("offer");
            boolean up = map.getBoolean("up");
            System.out.println(stock + "\t" + price + "\t" + offer + "\t" + up);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
