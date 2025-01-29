package com.cantact_list.com.cantact_list.service.Impl;

import com.cantact_list.com.cantact_list.domain.Contact;
import com.cantact_list.com.cantact_list.repository.ContactRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiFunction;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ServiceImpl implements Service {

    private final ContactRepo contactRepo;

    public Page<Contact> getAllContacts(int page, int size){
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Contact getContact(String id){
        return contactRepo.findById(id).orElseThrow(()->new RuntimeException("Contact Not Found"));
    }

    public Contact createContact(Contact contact){
        return contactRepo.save(contact);
    }

    public String uploadPhoto(String id, MultipartFile file){
        Contact contact = getContact(id);
        String photoUrl = null;
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;

    }

    private final BiFunction<String,MultipartFile,String> photoFunction = (id,image) -> {
                try{
                    Path fileStorageLocation = Paths.get("").toAbsolutePath().normalize();
                    if(!Files.exists(fileStorageLocation)){Files.createDirectories(fileStorageLocation);}
                    Files.copy(image.getInputStream(),fileStorageLocation.resolve(id+".png"),REPLACE_EXISTING);
                }catch(Exception e){
                    throw new RuntimeException("Unable to save image");
                }
    };


}
