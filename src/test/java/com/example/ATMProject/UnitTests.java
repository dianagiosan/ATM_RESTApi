package com.example.ATMProject;

import com.example.ATMProject.Application.DTO.ATMdto;
import com.example.ATMProject.Application.Service.ATMServiceImpl;
import com.example.ATMProject.Domain.BillEntry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UnitTests {
	
	
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
	public void splitIntoBills_151() {
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
	public void splitIntoBills_103() {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		ATMdto actual = ATMServiceImplTest.splitIntoBills(103);
		expectedBills.add(new BillEntry(100, 1));
		expectedBills.add(new BillEntry(1, 3));
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_50() {
		
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(50, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(50);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_7510() {
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
	public void splitIntoBills_60() {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(50, 1));
		expectedBills.add(new BillEntry(10, 1));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(60);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_4() {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(1, 4));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(4);
		ATMdto expected = new ATMdto(expectedBills, "Transaction approved");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_99() {
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
	public void splitIntoBillsOverflow() {
		ATMServiceImpl ATMServiceImplTest = new ATMServiceImpl();
		List<BillEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new BillEntry(0, 0));
		ATMdto actual = ATMServiceImplTest.splitIntoBills(10000);
		ATMdto expected = new ATMdto(expectedBills, "Transaction denied");
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void splitIntoBillsRepeatedlyTest1() {
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
	public void splitIntoBillsRepeatedlyTest2() {
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
	public void splitIntoBillsRepeatedlyTest3() {
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
	
}