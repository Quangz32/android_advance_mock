package com.example.ojtaadaassignment12.data.mapper;

import com.example.ojtaadaassignment12.data.local.entity.ReminderEntity;
import com.example.ojtaadaassignment12.domain.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderMapper {
    public static ReminderEntity mapToEntity(Reminder reminder) {
        return new ReminderEntity(
                reminder.getId(),
                reminder.getMovieTitle(),
                reminder.getMovieReleaseDate(),
                reminder.getMovieRating(),
                reminder.getMoviePoster(),
                reminder.getTimestamp()
        );
    }

    public static Reminder mapToDomain(ReminderEntity entity) {
        return new Reminder(
                entity.getId(),
                entity.getMovieTitle(),
                entity.getMovieReleaseDate(),
                entity.getMovieRating(),
                entity.getMoviePoster(),
                entity.getTimestamp()
        );
    }

    public static List<Reminder> mapToDomainList(List<ReminderEntity> entities) {
        List<Reminder> reminders = new ArrayList<>();
        for (ReminderEntity entity : entities) {
            reminders.add(mapToDomain(entity));
        }
        return reminders;
    }


}
