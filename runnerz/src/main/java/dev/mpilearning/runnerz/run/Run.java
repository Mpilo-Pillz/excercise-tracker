package dev.mpilearning.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

/**
 * Immutable
 * Saves up doing toString, contructior, Getters, Setters, equals etc
 * */
public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
//        @Version
//        Integer version
) {
    public Run {
//        Keeping for Reference if I do not want ti use a library
        if(!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed On must be after Started On");
        }

//        if(startedOn.isBefore(completedOn)) {
//            throw new IllegalArgumentException("Started On Date acannot be before Completed On");
//        }


    }
}
