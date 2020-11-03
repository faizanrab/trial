package in.ecgc.erp.pebPolicy;



import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.ecgc.erp.pebPolicy.controller.recovery.RecoveryController;


@SpringBootApplication
public class PebPolicyTestApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(PebPolicyTestApplication.class);
	public static void main(String[] args) {
	
new File(RecoveryController.uploadDirectory).mkdir();
		SpringApplication.run(PebPolicyTestApplication.class, args);
		LOGGER.info("Simple log statement with inputs {}, {} and {}", 1,2,3);
	}

}
