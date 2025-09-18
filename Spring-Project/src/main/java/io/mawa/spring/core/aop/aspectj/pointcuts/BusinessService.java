package io.mawa.spring.core.aop.aspectj.pointcuts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

// In a real application, these would be in separate files.
// We are keeping them together here for demo simplicity.

@Repository
class DataRepository {

    public DataRepository() {
        System.out.println("✅ DataRepository Bean Created!");
    }

    public String[] fetchAllData() {
        // Simulate fetching data from a database
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new String[]{"Data1", "Data2", "Data3"};
    }
}


@Service
public class BusinessService {

    private final DataRepository dataRepository;

    @Autowired
    public BusinessService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        System.out.println("✅ BusinessService Bean Created and Injected with DataRepository!");
    }

    public String[] getData() {
        System.out.println(">> Inside BusinessService.getData()");
        return dataRepository.fetchAllData();
    }

    @Loggable
    public int processData(String data) {
        System.out.println(">> Inside BusinessService.processData() with argument: " + data);
        return calculateLength(data);
    }

    private int calculateLength(String data) {
        // This is a private helper method. Proxy-based AOP cannot intercept calls to this method
        // if they are made from within the same object (a self-invocation).
        return data.length();
    }

    public void causeException() {
        System.out.println(">> Inside BusinessService.causeException()");
        throw new RuntimeException("This is a simulated business exception!");
    }
}
