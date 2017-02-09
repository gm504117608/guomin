package com.company.learn.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by dell on 2016/9/28.
 */
public class Consumer {
   // private static String brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String brokerURL = "tcp://localhost:61616";
    private static final String SUBJECT = "queue-activeMq-test";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    protected transient Destination dest;
    private transient Session session;
    protected transient MessageConsumer messageConsumer;

    public Consumer() throws JMSException {
        //初始化ConnectionFactory
        factory = new ActiveMQConnectionFactory(brokerURL);
        //创建mq连接
        connection = factory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        dest = session.createQueue(SUBJECT);
        //通过session可以创建消息的消费者
        messageConsumer = session.createConsumer(dest);
    }

    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws JMSException {
        Consumer consumer = new Consumer();
        consumer.messageConsumer.setMessageListener(new Listener());
    }
}
