package udemy.exer.exem1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import udemy.exer.exem1.serialization.converter.YamlJackson2HttpMessageConverter;

import java.util.List;


@Configuration   // essa anotação indica que para o spring boot que quando ele rodar
// a aplicação é necessário ler essa classe pois vai encontrar config sobre o comportamento da aplicação
public class WebConfig implements WebMvcConfigurer {

    private static MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");


    // VIA QUERY PARAM -> http://localhost:8080/api/person/v1/1?mediaType=xml
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//       configurer.favorParameter(true)
//               .parameterName("mediaType").ignoreAcceptHeader(true)
//               .useRegisteredExtensionsOnly(false)
//               .defaultContentType(MediaType.APPLICATION_JSON)
//               .mediaType("json", MediaType.APPLICATION_JSON)
//               .mediaType("xml", MediaType.APPLICATION_XML);


    // VIA HEADER PARAM -> http://localhost:8080/api/person/v1/1

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new YamlJackson2HttpMessageConverter());
    }
}
