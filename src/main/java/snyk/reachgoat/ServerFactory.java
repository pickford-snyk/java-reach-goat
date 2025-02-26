package io.snyk.reachgoat;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.endpoint.Server;

import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;

public class ServerFactory {
  private String configFileLocation;

  public ServerFactory(String configFileLocation) {
    this.configFileLocation = configFileLocation;
  }

  public Server createServer() throws MalformedURLException {
    SpringBusFactory bf = new SpringBusFactory();
    if (configFileLocation == null) { throw new RuntimeException("config File expected!"); }
    File configFile = new File(configFileLocation);
    URL url = configFile.toURI().toURL();
    ServerFactoryBean serverFactory = new ServerFactoryBean();
    Bus bus = bf.createBus(url.toString()); 
    serverFactory.setBus(bus);
    return serverFactory.create();
  }
}
