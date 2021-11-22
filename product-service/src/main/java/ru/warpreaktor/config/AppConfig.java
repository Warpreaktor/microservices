
package ru.warpreaktor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ru.warpreaktor.repository", "ru.warpreaktor.service"})
public class AppConfig {
}