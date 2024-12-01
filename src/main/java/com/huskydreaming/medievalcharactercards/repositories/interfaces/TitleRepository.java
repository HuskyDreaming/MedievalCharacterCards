package com.huskydreaming.medievalcharactercards.repositories.interfaces;

import com.huskydreaming.huskycore.repositories.Repository;

import java.util.List;

public interface TitleRepository extends Repository {
    List<String> getTitles();
}
