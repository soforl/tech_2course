import dao.BreedDao;
import dao.FriendsDao;
import dao.KotikDao;
import dao.OwnerDao;
import entities.*;
import services.BreedService;
import services.FriendsService;
import services.KotikService;
import services.OwnerService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Program {
    public static void main(String[] args) throws SQLException {
        KotikService kotikService = new KotikService(new KotikDao());
        OwnerService ownerService = new OwnerService(new OwnerDao());
        BreedService breedService = new BreedService(new BreedDao());
        FriendsService friendsService = new FriendsService(new FriendsDao());

        Breed breed = new Breed("sfinks");
        breedService.saveBreed(breed);

        Owner owner = new Owner("Sasha", LocalDate.of(2020, 02, 03));
        ownerService.saveOwner(owner);
        ownerService.updateOwner(owner);

        Kotik kotik1 = new Kotik("Haskel", LocalDate.of(2020, 02, 02),
                Color.Grey, breed, owner);
        kotikService.saveKotik(kotik1);

        Kotik kotik2 = new Kotik("Kotlin", LocalDate.of(2020, 02, 02),
                Color.Black, breed, owner);
        kotikService.saveKotik(kotik2);

        Friends friends = new Friends(kotik1.getId(), kotik2.getId());
        friendsService.saveFriends(friends);

        kotik1.setOwnerId(owner);
        kotik2.setOwnerId(owner);
        ownerService.updateOwner(owner);
        kotikService.updateKotik(kotik1);
        kotikService.updateKotik(kotik2);
    }
}
