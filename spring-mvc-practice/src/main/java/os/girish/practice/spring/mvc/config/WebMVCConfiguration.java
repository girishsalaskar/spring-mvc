package os.girish.practice.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "os.girish.practice.spring" })
public class WebMVCConfiguration implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver res = new InternalResourceViewResolver();
		res.setViewClass(JstlView.class);
		res.setPrefix("/WEB-INF/views");
		res.setSuffix(".jsp");
		return res;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources");
	}

}
