package com.forum.auth;

import com.forum.auth.infrastructure.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
class AuthApplicationTests {

	@Test
	void contextLoads() {
	}

}
