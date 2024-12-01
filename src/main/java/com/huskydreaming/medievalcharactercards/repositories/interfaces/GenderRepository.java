package com.huskydreaming.medievalcharactercards.repositories.interfaces;

import com.huskydreaming.huskycore.repositories.Repository;

import java.util.List;

public interface GenderRepository extends Repository {
    List<String> getGenders();
}
