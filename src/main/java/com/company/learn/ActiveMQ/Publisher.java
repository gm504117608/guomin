package com.company.learn.ActiveMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;

/**
 * Created by dell on 2016/9/28.
 */
public class Publisher {

    // 消息中间件的地址
    private static final String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL; // "tcp://localhost:61616";
    // 消息队列的名称
    private static final String SUBJECT = "queue-activeMq-test";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient Session session;
    private transient Destination dest;
    private transient MessageProducer producer;

    public Publisher() throws JMSException {
        //初始化ConnectionFactory
        factory = new ActiveMQConnectionFactory(brokerURL);
        try{
            //创建mq连接
            connection = factory.createConnection();
            //启动连接
            connection.start();
        }catch(JMSException jmse){
            connection.close();
            throw jmse;
        }
        //创建会话
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        dest = session.createQueue(SUBJECT);
        //通过session可以创建消息的生产者
        producer = session.createProducer(dest);
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public void sendMessage() throws JMSException {
        //创建消息进行发送
        for(int i=0; i<5; i++){
            MapMessage message = session.createMapMessage();
            message.setString("stock", "stock" + i);
            message.setDouble("price", 123.01);
            message.setDouble("offer", 456.03);
            message.setBoolean("up", true);
            System.out.println("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + dest);
            producer.send(message);
        }

    }

    public static void main(String[] args) throws JMSException {
        Publisher publisher = new Publisher();
        publisher.sendMessage();
        publisher.close();
    }


}
