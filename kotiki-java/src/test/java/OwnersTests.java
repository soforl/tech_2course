import dao.KotikDao;
import dao.OwnerDao;
import entities.Breed;
import entities.Color;
import entities.Kotik;
import entities.Owner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import services.KotikService;
import services.OwnerService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OwnersTests {
    private OwnerDao ownerDao;
    private OwnerService ownerService;
    private KotikDao kotikDao;
    private KotikService kotikService;

    @Before
    public void init() {
        this.ownerDao = Mockito.mock(OwnerDao.class);
        this.kotikDao = Mockito.mock(KotikDao.class);
        this.ownerService = new OwnerService(ownerDao);
        this.kotikService = new KotikService(kotikDao);
    }

    @Test
    public void addOwner() {
        Owner mockOwner = new Owner("Sasha", LocalDate.of(2020, 02, 03));
        when(ownerDao.findById(1)).thenReturn(mockOwner);
        Owner owner = ownerService.findOwner(1);
        verify(ownerDao).findById(Mockito.anyInt());
        assertEquals(mockOwner, owner);
    }

    @Test
    public void addKotik() {
        Breed breed = new Breed("sfinks");
        Owner owner = new Owner("Sasha", LocalDate.of(2020, 02, 03));

        Kotik mockKotik = new Kotik("Haskel", LocalDate.of(2020, 02, 02),
                Color.Grey, breed, owner);

        when(kotikDao.findById(1)).thenReturn(mockKotik);
        Kotik kotik = kotikService.findKotik(1);
        verify(kotikDao).findById(Mockito.anyInt());
        assertEquals(mockKotik, kotik);
    }
}
