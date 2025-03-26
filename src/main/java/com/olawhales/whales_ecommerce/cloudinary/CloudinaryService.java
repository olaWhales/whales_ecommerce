package com.olawhales.whales_ecommerce.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "daaxnxo3h",
            "api_key", "613732755444368",
            "api_secret", "ZGmneiX8yJUq6Jh4aDjxXw6WcAs",
            "secure", true));

    public String upload(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString(); // Return the uploaded image URL
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    @Override
    public String toString() {
        return "CloudinaryService{" +
                "cloudinary=" + cloudinary +
                '}';
    }
}
