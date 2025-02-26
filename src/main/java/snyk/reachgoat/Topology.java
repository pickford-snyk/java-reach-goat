package io.snyk.reachgoat;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;

public class Topology {
  static public void buildTopology() throws Exception {
    TopologyBuilder builder = new TopologyBuilder();
    Config conf = new Config();
    conf.put("port", "9999");
    conf.put("authentication", "kerberos");
    // internally calls vulnerable snakeyaml parser
    StormSubmitter.submitTopology("topology", conf, builder.createTopology()); 
  }
}
