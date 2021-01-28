package com.cg.onlineeyeclinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cg.onlineeyeclinic.exception.ReportIdNotFoundException;
import com.cg.onlineeyeclinic.model.Report;

public interface IReportService {

	Report addReport(Report report);

	Report updateReport(Report report);

	Report deleteReport(Integer id) throws ReportIdNotFoundException;

	Optional<Report> viewReport(Integer id)throws  ReportIdNotFoundException;

	List<Report> viewReportList();
	
	List<Report> viewAllReports(LocalDate date);

}
