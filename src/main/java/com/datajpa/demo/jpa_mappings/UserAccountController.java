package com.datajpa.demo.jpa_mappings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/accounts")
public class UserAccountController {

    private final AddressRespository addressRespository;
    private final PostRepository postRepository;
    private final UserAccountRespository userAccountRespository; // HAS-A dependency

    @GetMapping
    public String info() {
        return "Greetings";
    }

    @Autowired
    public UserAccountController(AddressRespository addressRespository,
                                 PostRepository postRepository,
                                 UserAccountRespository userAccountRespository) {
        this.addressRespository = addressRespository;
        this.postRepository = postRepository;
        this.userAccountRespository = userAccountRespository;
    }

    @PostMapping
    public UserAccount registerUser(@RequestBody UserAccount userAccount) {
        return this.userAccountRespository.save(userAccount);
    }

    @PostMapping("/{id}/address")
//    @Transactional
    public UserAccount registerUserAddress(@RequestBody Address newAddress, @PathVariable("id") Integer userId) {

        UserAccount foundUserAccount = this.userAccountRespository
                .findById(userId).orElseThrow(() -> new RuntimeException("User id does not exists"));
        // new object of Address is Transient
        Address address = this.addressRespository.save(newAddress); // persist address
        foundUserAccount.setAddress(address); // assign address to user
        return this.userAccountRespository.save(foundUserAccount);
    }

    @PostMapping("{id}/posts")
    public UserAccount addUserPost(@RequestBody Post newPost, @PathVariable Integer id) {

        UserAccount foundUserAccount = this.userAccountRespository
                .findById(id).orElseThrow(() -> new RuntimeException("User id does not exists"));
        Post savedPost = this.postRepository.save(newPost);
        foundUserAccount.getPosts().add(savedPost);
        return this.userAccountRespository.save(foundUserAccount);
    }

    @GetMapping("/{userId}")
    public UserAccount getUserById(@PathVariable("userId") Integer id) {
        return this.userAccountRespository.findById(id).orElseThrow(() -> new RuntimeException("User id does not exists"));
    }

}
