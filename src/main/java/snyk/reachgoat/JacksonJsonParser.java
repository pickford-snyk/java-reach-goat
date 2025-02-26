package io.snyk.reachgoat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonParser {

  public static Pojo parseJson() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    // on purpose not enabled to not move the intersting transitive matches here behind the all the duplicate jackson vulns.
    // mapper.enableDefaultTyping();
    
    Pojo pojo = mapper.readValue("{\"name\": \"name\"}", Pojo.class);
    return pojo;
  }

}
