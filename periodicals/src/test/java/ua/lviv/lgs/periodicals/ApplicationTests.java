package ua.lviv.lgs.periodicals;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.lviv.lgs.periodicals.dao.BucketRepository;
import ua.lviv.lgs.periodicals.dao.PeriodicalRepository;
import ua.lviv.lgs.periodicals.dao.UserRepository;
import ua.lviv.lgs.periodicals.domain.Bucket;
import ua.lviv.lgs.periodicals.domain.Periodical;
import ua.lviv.lgs.periodicals.domain.User;
import ua.lviv.lgs.periodicals.domain.UserRole;
import ua.lviv.lgs.periodicals.service.BucketService;
import ua.lviv.lgs.periodicals.service.PeriodicalsService;
import ua.lviv.lgs.periodicals.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private PeriodicalsService periodicalService;

	@Autowired
	private BucketService bucketService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PeriodicalRepository periodicalRepository;

	@Autowired
	private BucketRepository bucketRepository;

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDB = users.get(0);
		assertTrue(userFromDB.getEmail().equals(user.getEmail()));
		assertTrue(userFromDB.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDB.getLastName().equals(user.getLastName()));
		assertTrue(userFromDB.getRole().equals(user.getRole()));
	}

	@Test
	public void testFindByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("myCustomEmail@gmail.com");
		user.setFirstName("2");
		user.setLastName("2");
		user.setPassword("2");
		user.setPasswordConfirm("2");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User findByEmail = userService.findByEmail(user.getEmail());

		User userFromDB = users.get(0);
		assertTrue(findByEmail.getEmail().equals(user.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(user.getLastName()));
		assertTrue(findByEmail.getRole().equals(user.getRole()));
	}

	@Test
	public void testSavePeriodical() {
		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setPrice(1d);
		periodical.setEncodedImage("1");

		periodicalService.save(periodical);

		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(1));

		Periodical periodicalFromDB = periodicals.get(0);
		assertTrue(periodicalFromDB.getName().equals(periodical.getName()));
		assertTrue(periodicalFromDB.getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromDB.getPrice().equals(periodical.getPrice()));
		assertTrue(periodicalFromDB.getEncodedImage().equals(periodical.getEncodedImage()));
	}

	@Test
	public void testFindById() {
		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		periodicalRepository.save(periodical);

		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(1));

		Periodical periodicalFromDb = periodicalService.findById(periodicals.get(0).getId());

		assertTrue(periodicalFromDb.getName().equals(periodical.getName()));
		assertTrue(periodicalFromDb.getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromDb.getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromDb.getPrice().equals(periodical.getPrice()));
	}

	@Test
	public void testGetAllPeriodicals() {
		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		Periodical periodical2 = new Periodical();
		periodical2.setName("12");
		periodical2.setDescription("12");
		periodical2.setEncodedImage("12");
		periodical2.setPrice(12d);

		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2));

		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(2));

		List<Periodical> periodicalsFromDb = periodicalService.getAllPeriodicals();

		assertTrue(periodicalsFromDb.get(0).getName().equals(periodical.getName()));
		assertTrue(periodicalsFromDb.get(0).getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalsFromDb.get(0).getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalsFromDb.get(0).getPrice().equals(periodical.getPrice()));

		assertTrue(periodicalsFromDb.get(1).getName().equals(periodical2.getName()));
		assertTrue(periodicalsFromDb.get(1).getDescription().equals(periodical2.getDescription()));
		assertTrue(periodicalsFromDb.get(1).getEncodedImage().equals(periodical2.getEncodedImage()));
		assertTrue(periodicalsFromDb.get(1).getPrice().equals(periodical2.getPrice()));
	}

	@Test
	public void testAddToBucket() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDB = userRepository.findAll().stream().findFirst().get();

		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setPrice(1d);
		periodical.setEncodedImage("1");

		periodicalService.save(periodical);

		Periodical periodicalFromDB = periodicalRepository.findAll().stream().findFirst().get();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setPeriodical(periodicalFromDB);
		bucket.setUser(userFromDB);
		bucket.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketService.add(bucket);

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));

		Bucket bucketFromDB = buckets.get(0);
		assertTrue(bucketFromDB.getPeriodical().equals(periodicalFromDB));
		assertTrue(bucketFromDB.getUser().equals(userFromDB));
		assertTrue(bucketFromDB.getPurchaseDate().equals(now));
	}

	@Test
	public void testDeleteFromBucket() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDB = userRepository.findAll().stream().findFirst().get();

		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setPrice(1d);
		periodical.setEncodedImage("1");

		Periodical periodical2 = new Periodical();
		periodical.setName("12");
		periodical.setDescription("12");
		periodical.setPrice(12d);
		periodical.setEncodedImage("12");

		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2));

		List<Periodical> periodicalsFromDB = periodicalRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setPeriodical(periodicalsFromDB.get(0));
		bucket.setUser(userFromDB);
		bucket.setPurchaseDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setPeriodical(periodicalsFromDB.get(1));
		bucket2.setUser(userFromDB);
		bucket2.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(2));

		bucketService.delete(buckets.get(0));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));
	}

	@Test
	public void testGetAll() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDB = userRepository.findAll().stream().findFirst().get();

		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setPrice(1d);
		periodical.setEncodedImage("1");

		Periodical periodical2 = new Periodical();
		periodical.setName("12");
		periodical.setDescription("12");
		periodical.setPrice(12d);
		periodical.setEncodedImage("12");

		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2));

		List<Periodical> periodicalsFromDB = periodicalRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setPeriodical(periodicalsFromDB.get(0));
		bucket.setUser(userFromDB);
		bucket.setPurchaseDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setPeriodical(periodicalsFromDB.get(1));
		bucket2.setUser(userFromDB);
		bucket2.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		List<Bucket> bucketFromDB = bucketService.getAll();
		assertThat(bucketFromDB, hasSize(2));
	}
}
