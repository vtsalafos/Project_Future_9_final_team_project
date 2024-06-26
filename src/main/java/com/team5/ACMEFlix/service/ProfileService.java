package com.team5.ACMEFlix.service;

import com.team5.ACMEFlix.domain.Account;
import com.team5.ACMEFlix.domain.Profile;
import com.team5.ACMEFlix.repository.AccountRepository;
import com.team5.ACMEFlix.repository.ProfileRepository;
import com.team5.ACMEFlix.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Transactional(readOnly = true)
    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Profile> findProfileById(Long id) {
        return profileRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public Optional<Profile> findProfileByAccountId(Long id) {
        return profileRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Profile> findAllProfilesByAccountId(Long id) {
        return profileRepository.findProfileByByAccountId(id);
    }

    @Transactional
    public Profile addProfileByAccountId(Long id, Profile profile) {
        Optional<Account> accountExists = accountRepository.findById(id);
        if(!accountExists.isPresent()){
            throw new NoSuchElementException("Account does not exist");
        }
        else{
            profile.setAccount(accountExists.get());
            profileRepository.save(profile);
        }
        return profile;
    }

    @Transactional
    public List<Profile> addProfilesByAccountId(Long id, List<Profile> profiles) {
        Optional<Account> accountExists = accountRepository.findById(id);
        if(!accountExists.isPresent()){
            throw new NoSuchElementException("Account does not exist");
        }
        else {
            for (Profile profile : profiles){
                profile.setAccount(accountExists.get());
                profileRepository.save(profile);
            }
        }
        return profiles;
    }

    @Transactional
    public void deleteProfileById(Long id) {
        boolean exists = profileRepository.existsById(id);
        if(!exists){
            throw new NoSuchElementException("Profile does not exist");
        }
        else{

            ratingRepository.deleteAllByProfileId(id);


            profileRepository.deleteById(id);
        }
    }

    @Transactional
    public void deleteProfilesByIds(List<Long> ids) {
        for(Long id : ids) {
            boolean exists = profileRepository.existsById(id);
            if (!exists) {
                throw new NoSuchElementException("Profile does not exist");
            } else {


                ratingRepository.deleteAllByProfileId(id);




                profileRepository.deleteById(id);
            }
        }
    }
    @Transactional
    public void updateProfileByIdPut(Long id, String firstname, Boolean ageRestricted, String imageUrl) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                "Profile doesnt not exists"
        ));


        if(firstname !=null && firstname.length() >0 &&
                !Objects.equals(profile.getFirstname(), firstname)){
            profile.setFirstname(firstname);
        }

        if(ageRestricted !=null &&
                !Objects.equals(profile.getAgeRestricted(), ageRestricted)){
            profile.setAgeRestricted(ageRestricted);
        }

        if(imageUrl !=null && imageUrl.length() >0 &&
                !Objects.equals(profile.getImageUrl(), imageUrl)){
            profile.setImageUrl(imageUrl);
        }

    }
    @Transactional
    public void updateProfileByIdPatch(Long id, Profile profile) {
        Profile foundProfile = profileRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                "Profile doesnt not exists"
        ));


        if(profile.getFirstname() !=null && profile.getFirstname().length() >0 &&
                !Objects.equals(foundProfile.getFirstname(), profile.getFirstname())){
            foundProfile.setFirstname(profile.getFirstname());
        }

        if(profile.getAgeRestricted() !=null &&
                !Objects.equals(foundProfile.getAgeRestricted(), profile.getAgeRestricted())){
            foundProfile.setAgeRestricted(profile.getAgeRestricted());
        }

        if(profile.getImageUrl() !=null && profile.getImageUrl().length() >0 &&
                !Objects.equals(foundProfile.getImageUrl(), profile.getImageUrl())){
            foundProfile.setImageUrl(profile.getImageUrl());
        }
    }



}
