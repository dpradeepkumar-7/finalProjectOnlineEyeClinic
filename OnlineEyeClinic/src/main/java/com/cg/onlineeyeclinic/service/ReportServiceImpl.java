package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.exception.ReportIdNotFoundException;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.repository.IReportRepository;

/**
 * ReportServiceImpl class provides methods to perform CRUD operations on Report Entity, View reports.
 * 
 * @author D,Pradeep kumar 
 */



@Service
public class ReportServiceImpl implements IReportService {

	@Autowired
	private IReportRepository repository;
	
	//Setters and Getters
		public IReportRepository getReportRepository() {
			return repository;
		}

		public void setPatientRepository(IReportRepository repository) {
			this.repository = repository;
		}

		
		/**
		 * This method Adds Report by calling the save method
		 * 
		 * @param report - report entity details
		 * @return report - report entity details
		 * 
		 */
	@Override
	public Report addReport(Report report) {
		report = repository.save(report);
		return report;

	}

	/**
	 * This method Updates Report by calling the save method
	 * 
	 * @param report - report entity details
	 * @return report - report entity details
	 * 
	 */
	@Override
	public Report updateReport(Report report) {
		report = repository.save(report);
		return report;

	}

	/**
	 * This method Deletes Report by calling the deleteById method
	 * 
	 * @param reportId - Report Id
	 * @return null
	 * 
	 */
	@Override
	public Report deleteReport(Integer reportId)  {
		Optional<Report> rep = repository.findById(reportId);

		if (rep == null)
			throw new ReportIdNotFoundException("Report ID Not Found");
		else
			repository.deleteById(reportId);

		return null;
	}
	
	/**
	 * This method takes Report Id and returns the Report based on Id
	 * 
	 * @param reportId - integer value to display report based on patientId
	 * @return report - report entity details
	 */

	@Override
	public Optional<Report> viewReport(Integer reportId)  {
		Optional<Report> rep = repository.findById(reportId);
		if (rep == null)
			throw new ReportIdNotFoundException("Report ID Not Found");
		return rep;

	}
	
	/**
	 * This method returns the Report List present in the Report Table
	 * 
	 * If Report list is null, returns null
	 * 
	 * @return list of reports
	 * 
	 */

	@Override
	public List<Report> viewReportList() {
		List<Report> allReport = repository.findAll();
		return allReport;
	}

	/**
	 * This method returns the Report List present in the Report Table
	 * 
	 * If Report list is null, returns null
	 * 
	 * @return list of reports in date
	 * 
	 */
	@Override
	public List<Report> viewAllReports(LocalDate date) {
		List<Report> allReports = repository.viewAllReports(date);
		return allReports;
	}

}
