package com.avenuecode.template;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.hibernate.SessionFactory;

public class TemplateApplication extends Application<TemplateConfiguration> {

    private final ScanningHibernateBundle<TemplateConfiguration> hibernate =
            new ScanningHibernateBundle<TemplateConfiguration>(getClass().getPackage().getName()) {
        @Override public DataSourceFactory getDataSourceFactory(TemplateConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new TemplateApplication().run(args);
    }

    @Override
    public String getName() {
        return "template";
    }

    @Override
    public void initialize(Bootstrap<TemplateConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(TemplateConfiguration configuration, Environment environment) {
        environment.jersey().register(new AbstractBinder() {
            @Override protected void configure() {
                bind(hibernate.getSessionFactory()).to(SessionFactory.class);
            }
        });
        environment.jersey().packages(getClass().getPackage().getName());
    }
}
