package kr.or.connect.ROOT.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.ROOT.serviceimpl", "kr.or.connect.ROOT.service", "kr.or.connect.ROOT.dao"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}
