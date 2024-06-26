package com.team5.ACMEFlix.controller;


import com.team5.ACMEFlix.forms.RatingForm;
import com.team5.ACMEFlix.mapper.RatingMapper2;
import com.team5.ACMEFlix.service.RatingService;
import com.team5.ACMEFlix.transfer.ApiResponse;
import com.team5.ACMEFlix.transfer.resource.RatingResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rating")
public class RatingController {
    private final RatingService ratingService;
    private final RatingMapper ratingMapper;
    private final RatingMapper2 ratingMapper2;
    @Autowired
    private RatingController(RatingService ratingService, RatingMapper ratingMapper, RatingMapper2 ratingMapper2) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
        this.ratingMapper2 = ratingMapper2;
    }


    @PostMapping(path = "addRating")
    public ResponseEntity<ApiResponse<RatingResource>> addRating(
            @Valid @RequestBody RatingResource rating
    ){
        return new ResponseEntity<>(ApiResponse.<RatingResource>builder().data(ratingMapper.toResource(ratingService.addRating(ratingMapper.toDomain(rating)))).build(), HttpStatus.CREATED);
    }

    @PostMapping(path = "addRatings")
    public ResponseEntity<ApiResponse<List<RatingResource>>> addRatings(
            @Valid @RequestBody List<RatingResource> ratings
    ){
        return new ResponseEntity<>(ApiResponse.<List<RatingResource>>builder().data(ratingMapper.toResources(ratingService.addRatings(ratingMapper.toDomains(ratings)))).build(), HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "deleteRatingById/{id}")
    public void deleteRatingById(
            @PathVariable("id") Long id
    ){
        ratingService.deleteRatingById(id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "deleteRatingsByIds/{ids}")
    public void deleteRatingsByIds(
            @PathVariable("ids") List<Long> id
    ){
        ratingService.deleteRatingsByIds(id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "deleteRatingByContentIdAndProfileId")
    public void deleteRatingByContentIdAndProfileId(
            @Valid @RequestBody RatingForm ratingForm
    ){
        ratingService.deleteRatingByContentIdAndProfileId(ratingForm);
    }
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping(path = "rate")
    public void rate(
            @Valid @RequestBody RatingForm ratingForm
    ){
        ratingService.rate(ratingForm);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping(path = "updateRatingById/{id}")
    public void updateRatingById(
            @PathVariable("id") Long id,
            @Valid @RequestBody RatingResource rating
    ){
        ratingService.updateRatingById(id, ratingMapper.toDomain(rating));
    }
}
