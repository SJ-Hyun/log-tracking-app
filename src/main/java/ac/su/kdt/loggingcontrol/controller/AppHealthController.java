package ac.su.kdt.loggingcontrol.controller;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class AppHealthController {
    @GetMapping("/app-health")
    public String healthCheck() {
        return "app_health_status 1.0\n# EOF";
    }

    @RequestMapping("/hash/{input}")
    public String getDigest(@PathVariable("input") String input) throws NoSuchAlgorithmException {
        for(int i = 0; i < 100_000; i++) {
            input = getMD5Digest(input);
        }
        return input;
    }
    
    private String getMD5Digest(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();

        return myHash;
    }
}
