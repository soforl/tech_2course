package com.example.kotiki4.wrappers;


import com.example.kotiki4.entities.Kotik;
import com.example.kotiki4.entities.Owner;

public class WrapperCreator {
    public static KotikWrapper createKotikWrapper(Kotik kotik){
        return new KotikWrapper(kotik.getId(),
                kotik.getName(),
                kotik.getBirthday(),
                kotik.getBreedId(),
                kotik.getColorId(),
                kotik.getOwnerId().getId());
    }

    public static OwnerWrapper createOwnerWrapper(Owner owner){
        return new OwnerWrapper(owner.getId(),
                owner.getName(),
                owner.getBirthday());
    }
}
