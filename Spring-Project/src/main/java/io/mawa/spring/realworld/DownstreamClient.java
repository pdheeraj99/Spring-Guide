package io.mawa.spring.realworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DownstreamClient {

    private final TrackingIdBean trackingIdBean;

    @Autowired
    public DownstreamClient(TrackingIdBean trackingIdBean) {
        this.trackingIdBean = trackingIdBean;
        System.out.println("DownstreamClient (Singleton) created and injected with proxy for TrackingIdBean.");
    }

    public String callDownstreamService() {
        // In a real app, we would use a RestClient or WebClient here.
        // We would get the tracking ID and add it to the outbound request header.
        String id = trackingIdBean.getTrackingId();
        System.out.println("📞 Calling downstream service... Attaching Tracking ID: " + id);

        // Simulate a response from the downstream service.
        return "Response from Service B (handled with Tracking ID: " + id + ")";
    }
}
