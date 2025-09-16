package io.mawa.spring.core.bfpp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BfppConfig {

    @Bean
    public DataSourceBean dataSource() {
        DataSourceBean dataSourceBean = new DataSourceBean();
        // Set a dummy/original password. Our BFPP will change this!
        dataSourceBean.setPassword("dummy-password-123");
        return dataSourceBean;
    }

    @Bean
    public static ObscenityRemoverBfpp obscenityRemoverBfpp() {
        // IMPORTANT: BeanFactoryPostProcessors should be declared as static
        // to ensure they are initialized before any other beans.
        return new ObscenityRemoverBfpp();
    }
}
