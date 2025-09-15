package io.mawa.spring.realworld;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TrackingIdBean {

    private final String trackingId;

    @Autowired
    public TrackingIdBean(HttpServletRequest request) {
        String idFromHeader = request.getHeader("X-Tracking-ID");
        if (idFromHeader != null) {
            this.trackingId = idFromHeader;
        } else {
            this.trackingId = UUID.randomUUID().toString();
        }
        System.out.println("✅ TrackingIdBean created for this request. ID: " + this.trackingId);
    }

    public String getTrackingId() {
        return trackingId;
    }
}
