package ru.itmo.kotiki;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.itmo.kotiki.entities.Kotik;
import ru.itmo.kotiki.entities.Owner;
import ru.itmo.kotiki.repositories.KotikRepository;
import ru.itmo.kotiki.repositories.OwnerRepository;
import ru.itmo.kotiki.services.KotikServiceImpl;
import ru.itmo.kotiki.services.OwnerServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class KotikiApplicationTests {
	@MockBean
	private OwnerRepository ownerRepository;
	@Autowired
	private OwnerServiceImpl ownerService;

	@Test
	public void addOwner(){
		when(ownerRepository.getById(1)).thenReturn(new Owner("Evgenia",
						LocalDate.of(1978, 2, 16)));
		Owner owner = ownerService.findOwner(1);
		Assert.assertEquals("Evgenia", owner.getName());
		Assert.assertEquals(LocalDate.of(1978, 2, 16), owner.getBirthday());
	}
}
