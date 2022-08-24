package de.hybris.platform.testingstorefront.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


public class FileUploadForm {

    private int age;
    private String name;
    private MediaType mediaTypeFile;
    private MultipartFile multipartFile;
    private String originalParameters;

    public FileUploadForm() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }
    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public MediaType getMediaTypeFile() { return mediaTypeFile; }
    public void setMediaTypeFile(MediaType mediaTypeFile) { this.mediaTypeFile = mediaTypeFile; }

    public String getOriginalParameters() {
        return originalParameters;
    }
    public void setOriginalParameters(String originalParameters) {
        this.originalParameters = originalParameters;
    }

    // resolver BEAN
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }

    @Override
    public String toString() {
        return "FileUploadHelperClass{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", mediaTypeFile=" + mediaTypeFile +
                ", multipartFile=" + multipartFile +
                ", originalParameters='" + originalParameters + '\'' +
                '}';
    }
}
