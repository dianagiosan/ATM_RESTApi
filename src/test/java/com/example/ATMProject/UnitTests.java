package com.example.ATMProject;

import com.example.ATMProject.Application.DTO.ATMdto;
import com.example.ATMProject.Application.Service.ATMService;
import com.example.ATMProject.Application.Service.ATMServiceImpl;
import com.example.ATMProject.Config.MyFeatures;
import com.example.ATMProject.Controllers.WithdrawalController;
import com.example.ATMProject.Domain.BillEntry;
import com.example.ATMProject.FeignClient.AdelinaClient;
import com.example.ATMProject.Infrastructure.NotEnoughCashLeftException;
import com.example.ATMProject.Infrastructure.TransactionNotPossibleException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.togglz.junit.TogglzRule;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UnitTests {
	@InjectMocks
	WithdrawalController withdrawalController;
	@Mock
	AdelinaClient MockAdelinaClient;
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	@Rule
	public TogglzRule togglzRule = TogglzRule.allDisabled(MyFeatures.class);
	@Test
	public void getAvailableBills() {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(100, 50));
		expectedBills.add(new BillEntry(50, 50));
		expectedBills.add(new BillEntry(10, 100));
		expectedBills.add(new BillEntry(5, 100));
		expectedBills.add(new BillEntry(1, 100));
		assertArrayEquals(ATMServiceImplTest.getAvailableBills().toArray(), expectedBills.toArray());
	}
	
	@Test
	public void splitIntoBills_151() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(100, 1));
		expectedBills.add(new BillEntry(50, 1));
		expectedBills.add(new BillEntry(1, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(151);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_103() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		ATMdto actual = ATMServiceImplTest.splitIntoBills(103);
		expectedBills.add(new BillEntry(100, 1));
		expectedBills.add(new BillEntry(1, 3));
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_50() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(50, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(50);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_7510() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(100, 50));
		expectedBills.add(new BillEntry(50, 50));
		expectedBills.add(new BillEntry(10, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(7510);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_60() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(50, 1));
		expectedBills.add(new BillEntry(10, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(60);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_4() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(1, 4));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(4);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_99() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(50, 1));
		expectedBills.add(new BillEntry(10, 4));
		expectedBills.add(new BillEntry(5, 1));
		expectedBills.add(new BillEntry(1, 4));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(99);//
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBillsOverflow() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(0, 0));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(10000);
		ATMdto expected = new ATMdto(expectedBills, "Transaction denied");
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void splitIntoBillsRepeatedlyTest1() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(100, 50));
		expectedBills.add(new BillEntry(50, 50));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(7500);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		expectedBills.clear();
		expectedBills.add(new BillEntry(0, 0));
		actual = ATMServiceImplTest.splitIntoBills(7500);
		expected = new ATMdto(expectedBills, "Transaction denied");
		assertEquals(expected, actual);
		
		
	}
	@Test
	public void splitIntoBillsRepeatedlyTest2() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(100, 50));
		expectedBills.add(new BillEntry(50, 50));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(7500);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		expectedBills.clear();
		expectedBills.add(new BillEntry(10, 15));
		actual = ATMServiceImplTest.splitIntoBills(150);
		expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
		
	}
	@Test
	public void splitIntoBillsRepeatedlyTest3() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List <BillEntry> newATMServiceImplConfiguration = new ArrayList<>();
		newATMServiceImplConfiguration.add(new BillEntry(100, 1));
		newATMServiceImplConfiguration.add(new BillEntry(50, 1));
		newATMServiceImplConfiguration.add(new BillEntry(10, 5));
		newATMServiceImplConfiguration.add(new BillEntry(5, 9));
		newATMServiceImplConfiguration.add(new BillEntry(1, 10));
		ATMServiceImplTest.changeInitialATMConfiguration(newATMServiceImplConfiguration);
		
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(100, 1));
		expectedBills.add(new BillEntry(50, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(150);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
		expectedBills.clear();
		expectedBills.add(new BillEntry(10, 5));
		actual = ATMServiceImplTest.splitIntoBills(50);
		expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
		expectedBills.clear();
		expectedBills.add(new BillEntry(5, 9));
		expectedBills.add(new BillEntry(1, 5));
		actual = ATMServiceImplTest.splitIntoBills(50);
		expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
		
		
	}
	@Test
	public void withdrawFromAdelina() throws NotEnoughCashLeftException, TransactionNotPossibleException {
		
		ATMService ATM = new ATMServiceImpl();
		togglzRule.enable(MyFeatures.WITHDRAW_ADELINA);
		ResponseEntity<ATMdto> actual = withdrawalController.transaction(5000);
		ResponseEntity<ATMdto> expected = new ResponseEntity<>(ATM.splitIntoBills(5000), HttpStatus.OK);
		Assert.assertEquals(actual, expected);
		actual = withdrawalController.transaction(1000);
		ATMService ATM2 = new ATMServiceImpl();
		expected = new ResponseEntity<>(ATM2.splitIntoBills(5000), HttpStatus.OK);
		Assert.assertEquals(expected, actual);
	}
	
}