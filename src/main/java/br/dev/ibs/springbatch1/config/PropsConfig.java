package br.dev.ibs.springbatch1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class PropsConfig {
	
	private static final Logger log = LoggerFactory.getLogger(PropsConfig.class);
	
	@Bean
	public PropertySourcesPlaceholderConfigurer config() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		Path propertiesPath = Paths.get("/etc/config/springbatch1/application.properties");
		if (Files.exists(propertiesPath)) {
			configurer.setLocation(new FileSystemResource(propertiesPath));
		} else {
			log.warn("Não foi possivel encontrar o arquivo de propriedades: {}", propertiesPath);
		}
		return configurer;
	}
}
