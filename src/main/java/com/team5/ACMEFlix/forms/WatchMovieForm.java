package com.team5.ACMEFlix.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class WatchMovieForm {
    @NotNull(message = "Profile's id cannot be null")
    private Long profile_id;
    @NotNull(message = "Movie's id cannot be null")
    private Long movie_id;
    @NotNull(message = "Time watched cannot be null")
    @Min(1)
    private Float timeWatchedInMinutes;
}
