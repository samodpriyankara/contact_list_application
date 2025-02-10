package com.cantact_list.com.cantact_list.controller;

import com.cantact_list.com.cantact_list.domain.Contact;
import com.cantact_list.com.cantact_list.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.GetExchange;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.cantact_list.com.cantact_list.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("api/v1/contacts")
@CrossOrigin

public class ContactController {

    @Autowired
    private  ContactService contactService;

    @PostMapping("save")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){

        return ResponseEntity.created(URI.create("/api/v1/contacts/save/userId")).body(contactService.createContact(contact));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value = "page",defaultValue = "0") int page,
                                                       @RequestParam(value = "size",defaultValue = "10") int size){

        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable(value = "id") String id){

        return ResponseEntity.ok().body(contactService.getContact(id));
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "id")String id,
                                               @RequestParam(value = "file")MultipartFile file){

        return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));
    }

    @GetMapping(path = "/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException{
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

}
