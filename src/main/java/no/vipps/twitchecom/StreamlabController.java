package no.vipps.twitchecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class StreamlabController {

    @Autowired
    StreamlabService streamlabService;

    @GetMapping("/testing")
    public String auth() throws IOException {
        streamlabService.notifyStreamlabOfDonation("Franz", "Great stuff!", "100");
        return "";
    }

}
