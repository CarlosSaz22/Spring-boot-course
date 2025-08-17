package com.mcsv.shipping;

import com.mcsv.shipping.config.AxonXstreamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonXstreamConfig.class })
public class ShippingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingServiceApplication.class, args);
	}

}
