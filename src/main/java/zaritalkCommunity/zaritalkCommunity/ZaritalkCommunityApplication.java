package zaritalkCommunity.zaritalkCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZaritalkCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaritalkCommunityApplication.class, args);
	}

}
