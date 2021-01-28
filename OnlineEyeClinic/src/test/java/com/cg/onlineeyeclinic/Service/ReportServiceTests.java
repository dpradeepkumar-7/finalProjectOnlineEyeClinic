package com.cg.onlineeyeclinic.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.onlineeyeclinic.exception.ReportIdNotFoundException;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.repository.IReportRepository;
import com.cg.onlineeyeclinic.service.ReportServiceImpl;

//@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ReportServiceTests {

	@InjectMocks
	private ReportServiceImpl reportService;

	@Mock
	private IReportRepository reportRepository;

	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addReportTest() {
		Report report = new Report(LocalDate.of(2016, 8, 07), "VisionProblem", "5Feet", "LongRange", "shortRange");
		when(reportRepository.save(report)).thenReturn(report);

		report = reportService.addReport(report);

		assertEquals("VisionProblem", report.getDescriptionOfReport());
		verify(reportRepository, times(1)).save(report);
	}

	@Test
	public void updateReportTest() {
		Report report = new Report(LocalDate.of(2018, 11, 12), "NoVision", "Blur", "NoRange", "short");
		Integer reportId = report.getReportId();
		Optional<Report> reports = Optional.of(report);
		when(reportRepository.findById(reportId)).thenReturn(reports);
		when(reportRepository.save(report)).thenReturn(report);

		report.setVisualAcuity("Blur");

		report = reportService.updateReport(report);

		assertEquals("Blur", report.getVisualAcuity());
		verify(reportRepository, times(1)).save(report);
	}

	@Test
	public void deleteReportTest() {
		Report report = new Report(LocalDate.of(2017, 05, 03), "DryEyes", "Watering", "LessRange", "NotVisible");
		Integer reportId = report.getReportId();
		Optional<Report> reports = Optional.of(report);
		when(reportRepository.findById(reportId)).thenReturn(reports);
		report = reportService.deleteReport(reportId);
		assertNull(report);
		verify(reportRepository, times(1)).deleteById(reportId);
	}

	@Test
	public void viewReportTest()  {
		Report report = new Report(LocalDate.of(2013, 02, 01), "EyeInfection", "Mirror", "NoRange", "NotClear");
		Integer reportId = report.getReportId();
		Optional<Report> reports = Optional.of(report);

		when(reportRepository.findById(reportId)).thenReturn(reports);

		Optional<Report> newReports = reportService.viewReport(reportId);

		assertEquals("EyeInfection", newReports.get().getDescriptionOfReport());

	}

	@Test
	public void viewReportListTest() {

		List<Report> reportList = new ArrayList<Report>();
		Report reportOne = new Report(LocalDate.of(2019, 12, 01), "Floaters", "NotMention", "EightFeet", "SixFeet");
		Report reportTwo = new Report(LocalDate.of(2016, 12, 12), "Floaters", "NotMention", "EightFeet", "SixFeet");
		Report reportThree = new Report(LocalDate.of(2012, 10, 10), "Floaters", "NotMention", "EightFeet", "SixFeet");

		reportList.add(reportOne);
		reportList.add(reportTwo);
		reportList.add(reportThree);

		when(reportRepository.findAll()).thenReturn(reportList);

		List<Report> newReportList = reportService.viewReportList();

		assertEquals(3, newReportList.size());
		verify(reportRepository, times(1)).findAll();
	}

	@Test
	public void viewAllReportsTest() {
		Report report = new Report(LocalDate.of(2020, 10, 11), "EyePain", "NotClear", "TwoFeet", "SixFeet");
		LocalDate date = report.getDateOfReport();
		List<Report> reportList = new ArrayList<Report>();
		Report reportOne = new Report(LocalDate.of(2019, 12, 01), "Floaters", "NotMention", "EightFeet", "SixFeet");
		Report reportTwo = new Report(LocalDate.of(2016, 12, 12), "Floaters", "NotMention", "EightFeet", "SixFeet");
		Report reportThree = new Report(LocalDate.of(2012, 10, 10), "Floaters", "NotMention", "EightFeet", "SixFeet");

		reportList.add(reportOne);
		reportList.add(reportTwo);
		reportList.add(reportThree);

		when(reportRepository.viewAllReports(date)).thenReturn(reportList);
		List<Report> newReportList = reportService.viewAllReports(date);

		assertEquals(LocalDate.of(2020, 10, 11), report.getDateOfReport());

	}
}
