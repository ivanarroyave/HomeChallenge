package com.paloit.utils;

import com.github.javafaker.Faker;
import com.paloit.models.pet.Category;
import com.paloit.models.pet.Tag;
import com.paloit.models.pet.request.PetModelRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PetRequestBody {
    public static PetModelRequest defaultPetModelRequestObject() {
        Faker faker = new Faker();

        PetModelRequest petModelRequest = new PetModelRequest();
        petModelRequest.setId(String.valueOf(ThreadLocalRandom.current().nextInt(1000000000)));
        petModelRequest.setName(faker.dog().name());

        Category category = new Category();
        category.setId(1);
        category.setName("Dogs");

        petModelRequest.setCategory(category);

        petModelRequest.setPhotoUrls(List.of("/pet/photo/profile.jpg"));

        Tag tagOne = new Tag();
        tagOne.setId(1);
        tagOne.setName("Dog");

        Tag tagTwo = new Tag();
        tagTwo.setId(2);
        tagTwo.setName("Friendly");

        petModelRequest.setTags(Arrays.asList(tagOne, tagTwo));
        petModelRequest.setStatus("available");

        return petModelRequest;
    }

    public static PetModelRequest createPetModelRequest(String tag) {
        PetModelRequest petModelRequest = defaultPetModelRequestObject();

        Tag newTag = new Tag();
        newTag.setId(new Random().nextInt(100000));
        newTag.setName(tag);

        List<Tag> defaultTags = new ArrayList<>(petModelRequest.getTags());
        defaultTags.add(newTag);

        petModelRequest.setTags(defaultTags);

        return petModelRequest;
    }
}
