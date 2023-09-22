/**
 * 
 */
package springboot.brewery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * @author swnayak
 *
 */

@OpenAPIDefinition(servers = {
	    @Server(url = "/", description = "Default Server URL")
	})

@Configuration
public class OpenApiConfig {
	
	
	 @Bean
	  public OpenAPI customOpenAPI() {
	    
		 final String securitySchemeName = "bearerAuth";
		    return new OpenAPI();
		     // .addSecurityItem(new SecurityRequirement()
		     //   .addList(securitySchemeName))
		     // .components(new Components()
		     //   .addSecuritySchemes(securitySchemeName, new SecurityScheme()
		     //     .name(securitySchemeName)
		     //     .type(SecurityScheme.Type.HTTP)
		     //     .scheme("bearer")
		     //     .bearerFormat("JWT")));
	 }
		 
		 /*
		 return new OpenAPI()
	        .components(new Components()
	                .addSecuritySchemes("spring_oauth", new SecurityScheme()
	                        .type(SecurityScheme.Type.OAUTH2)
	                        .description("Oauth2 flow")
	                        .flows(new OAuthFlows()
	                                .authorizationCode(new OAuthFlow()
	                                        .tokenUrl("http://localhost:8000" + "/oauth2/token")
	                                        .scopes(new Scopes()
	                                                .addString("read", "for read operations")
	                                                .addString("write", "for write operations")
	                                        ))))
	        )
	            .security(Arrays.asList(
	                    new SecurityRequirement().addList("spring_oauth")))
	        .info(new Info()
	            .title("Brewery Application API")
	            .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
	            .termsOfService("terms")
	            .contact(new Contact().email("swnayak@deloitte.com").name("Developer: Swapnil Nayak"))
	            .license(new License().name("GNU"))
	            .version("2.0")
	        );
	    
	    
	  }*/
	 

}
