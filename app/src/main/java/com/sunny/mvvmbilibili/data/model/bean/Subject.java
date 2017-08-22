package com.sunny.mvvmbilibili.data.model.bean;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.RowMapper;
import com.sunny.mvvmbilibili.data.model.pojo.Image;
import com.sunny.mvvmbilibili.data.model.pojo.Person;
import com.sunny.mvvmbilibili.data.model.pojo.Rating;
import com.sunny.mvvmbilibili.utils.factory.MyGsonTypeAdapterFactory;
import com.sunny.sql.SubjectModel;

import java.util.List;

/**
 * Created by Zhou Zejin on 2016/9/21.
 */

@AutoValue
public abstract class Subject implements SubjectModel, Parcelable, Comparable<Subject> {

    private static final Gson gson = MyGsonTypeAdapterFactory.getRegisterTypeGson();

    private static final ColumnAdapter RATING_ADAPTER = new ColumnAdapter<Rating, String>() {
        @NonNull
        @Override
        public Rating decode(String databaseValue) {
            return gson.fromJson(databaseValue, Rating.class);
        }

        @Override
        public String encode(@NonNull Rating value) {
            return gson.toJson(value);
        }
    };

    private static final ColumnAdapter GENRES_ADAPTER = new ColumnAdapter<List<String>, String>() {
        @NonNull
        @Override
        public List<String> decode(String databaseValue) {
            return gson.fromJson(databaseValue, new TypeToken<List<String>>() {}.getType());
        }

        @Override
        public String encode(@NonNull List<String> value) {
            return gson.toJson(value);
        }
    };

    private static final ColumnAdapter PERSONS_ADAPTER = new ColumnAdapter<List<Person>, String>() {
        @NonNull
        @Override
        public List<Person> decode(String databaseValue) {
            return gson.fromJson(databaseValue, new TypeToken<List<Person>>() {}.getType());
        }

        @Override
        public String encode(@NonNull List<Person> value) {
            return  gson.toJson(value);
        }
    };

    private static final ColumnAdapter IMAGE_ADAPTER = new ColumnAdapter<Image, String>() {
        @NonNull
        @Override
        public Image decode(String databaseValue) {
            return gson.fromJson(databaseValue, Image.class);
        }

        @Override
        public String encode(@NonNull Image value) {
            return gson.toJson(value);
        }
    };

    public static final Factory<Subject> FACTORY = new Factory<>(
            new SubjectModel.Creator<Subject>() {
                @Override
                public Subject create(@NonNull String id, @NonNull Rating rating,
                                      @NonNull List<String> genres, @NonNull String title,
                                      @NonNull List<Person> casts, int collect_count,
                                      @NonNull String original_title, @NonNull String subtype,
                                      @NonNull List<Person> directors, @NonNull String year,
                                      @NonNull Image images, @NonNull String alt) {
                    return new AutoValue_Subject(id, rating, genres, title, casts, collect_count,
                            original_title, subtype, directors, year, images, alt);
                }
            },
            RATING_ADAPTER,
            GENRES_ADAPTER,
            PERSONS_ADAPTER,
            PERSONS_ADAPTER,
            IMAGE_ADAPTER);

    public static final RowMapper<Subject> MAPPER = FACTORY.select_allMapper();

    public static Builder builder() {
        return new AutoValue_Subject.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder rating(Rating rating);

        public abstract Builder genres(List<String> genres);

        public abstract Builder title(String title);

        public abstract Builder casts(List<Person> casts);

        public abstract Builder collect_count(int collect_count);

        public abstract Builder original_title(String original_title);

        public abstract Builder subtype(String subtype);

        public abstract Builder directors(List<Person> directors);

        public abstract Builder year(String year);

        public abstract Builder images(Image images);

        public abstract Builder alt(String alt);

        public abstract Subject build();
    }

    public static TypeAdapter<Subject> typeAdapter(Gson gson) {
        return new AutoValue_Subject.GsonTypeAdapter(gson);
    }

    @Override
    public int compareTo(@NonNull Subject another) {
        return title().compareTo(another.title());
    }

}
