package io.mawa.spring.realworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private final DownstreamClient downstreamClient;

    @Autowired
    public BusinessService(DownstreamClient downstreamClient) {
        this.downstreamClient = downstreamClient;
        System.out.println("BusinessService (Singleton) created.");
    }

    public String doBusinessLogic() {
        System.out.println("Doing business logic... note that we don't see the tracking ID here!");
        return downstreamClient.callDownstreamService();
    }
}
