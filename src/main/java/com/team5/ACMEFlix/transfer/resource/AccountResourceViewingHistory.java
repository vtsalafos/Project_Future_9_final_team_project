package com.team5.ACMEFlix.transfer.resource;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.team5.ACMEFlix.domain.enumeration.SubscriptionType;
import com.team5.ACMEFlix.transfer.BaseResource;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResourceViewingHistory extends BaseResource {
    @NotNull(message = "Account's email cannot be null")
    @Column(length = 50, nullable = false, unique = true)
    @Email
    private String email;

    @NotNull(message = "Account's username cannot be null")
    @Column(length = 50, nullable = false, unique = false)
    @Pattern(regexp = "^[A-Za-z0-9]*$", message="Account's username can only contain alphanumeric symbols")
    private String username;

    @NotNull(message = "Account's firstname cannot be null")
    @Column(length = 30, nullable = false)
    @Pattern(regexp = "^[A-Za-z ]+$", message="Account's firstname can only contain alphabetical symbols")
    private String firstname;

    @NotNull(message = "Account's lastname cannot be null")
    @Column(length = 30, nullable = false)
    @Pattern(regexp = "^[A-Za-z ]+$", message="Account's lastname can only contain alphabetical symbols")
    private String lastname;

    @Column(length = 14)
    @Pattern(regexp = "^[0-9]*$", message="Account's phone Number can only contain numeric symbols")
    private String phoneNo;

    @NotNull(message = "Subscription type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private SubscriptionType subscriptionType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private Date creationDate = new Date();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private Date subscriptionDate;


    private List<com.team5.ACMEFlix.transfer.resource.ProfileResourceViewingHistory> profiles;

}


