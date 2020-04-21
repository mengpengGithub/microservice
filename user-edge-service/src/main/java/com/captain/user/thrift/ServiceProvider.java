package com.captain.user.thrift;

import com.captain.thrift.message.MessageService;
import com.captain.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceProvider
 * @Description TODO
 * @Author mengpeng
 * @Date 2020/3/15 23:19
 * @Version 1.0
 */
@Component
public class ServiceProvider {


    @Value("${thrift.user.ip}")
    private String thriftUserIp;

    @Value("${thrift.user.port}")
    private int thriftUserPort;

    @Value("${thrift.message.ip}")
    private String thriftMessageIp;

    @Value("${thrift.message.port}")
    private int thriftMessagePort;

    private enum ServiceType{
        USER,
        MESSAGE,
    }

    public UserService.Client getUserService() {
        return getService(thriftUserIp, thriftUserPort, ServiceType.USER);
    }

    public MessageService.Client getMessageService() {
        return getService(thriftMessageIp, thriftMessagePort, ServiceType.MESSAGE);
    }

    public <T> T getService(String ip, int port, ServiceType serviceType){
        TSocket socket = new TSocket(ip, port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient result = null;
        switch (serviceType){
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
        }
        return (T) result;
    }
}
