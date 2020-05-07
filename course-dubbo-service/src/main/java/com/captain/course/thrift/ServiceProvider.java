package com.captain.course.thrift;

import com.captain.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
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

    private enum ServiceType{
        USER,
        MESSAGE,
    }

    public UserService.Client getUserService() {
        return getService(thriftUserIp, thriftUserPort, ServiceType.USER);
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
        }
        return (T) result;
    }
}
