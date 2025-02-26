package io.snyk.reachgoat;

import org.apache.cxf.endpoint.Server;
import org.quartz.Scheduler;

public class Main {
  public static void main(String[] args) throws Exception {
    ServerFactory serverFactory = new ServerFactory("/tmp/file");
    
    Server server = serverFactory.createServer();

    
    Topology.buildTopology();

    JacksonJsonParser.parseJson();

    
    Scheduler s = new JobScheduler().scheduleJobs();
  }
}
