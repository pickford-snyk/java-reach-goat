
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

import java.io.IOException;
import java.lang.System;
import java.lang.Runtime;
import java.util.Map;

@RestController
public class MatrixController {

    @GetMapping(value = "/greet/{input}",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String greet(@MatrixVariable("input") String input) throws IOException, InterruptedException { 
        return String.format("Hello '%s'", input);
    }

}
