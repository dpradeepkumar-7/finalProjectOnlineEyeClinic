package com.cg.onlineeyeclinic.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineeyeclinic.exception.ReportIdNotFoundException;
import com.cg.onlineeyeclinic.model.Report;
import com.cg.onlineeyeclinic.service.IReportService;

@RestController
//@RequestMapping("/api/Report")
@Validated
@CrossOrigin("*")

	public class ReportController {
		
		@Autowired
		private IReportService service;
		
		//ADDING A REPORT
		@PostMapping("/addReport")
		public ResponseEntity<Report> addReport(@Valid @RequestBody Report rep){
			rep = service.addReport(rep);
			return new ResponseEntity<>(rep,new HttpHeaders(), HttpStatus.OK);
		}
		
		//UPDATING A REPORT
		@PutMapping("/updateReport")
		public ResponseEntity<Report> updateReport(@Valid @RequestBody Report report){
			report= service.updateReport(report);
			return new ResponseEntity<>(report,new HttpHeaders(), HttpStatus.OK); 
		}
		
		//DELETING A REPORT
		@DeleteMapping("/deleteReportById/{id}")
		public ResponseEntity<String> deleteReportById(@PathVariable("id") Integer id) {
			service.deleteReport(id);
			return new ResponseEntity<String>("Report Deleted Successfully", new HttpHeaders(),HttpStatus.OK);
		}
		
		//VIEW REPORT BY ID
		@GetMapping("/viewReportById/{id}")
		public ResponseEntity<Optional<Report>> viewReportById(@PathVariable("id") Integer id)  {
			Optional<Report> rep = service.viewReport(id);
			return new ResponseEntity<Optional<Report>>(rep, new HttpHeaders(),HttpStatus.OK);
		}
	
		//VIEW ALL REPORT LIST
		@GetMapping("/viewReportList")
		public ResponseEntity<List<Report>> viewAllReport(){
			List<Report> reportList=service.viewReportList();
			return new ResponseEntity<>(reportList,new HttpHeaders(), HttpStatus.OK);
		} 
		
		//VIEW REPORT BY DATE
		@GetMapping("/viewAllReports/{date}")
		public ResponseEntity<List<Report>> viewAllReports(@Valid @PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) throws ReportIdNotFoundException{
			List<Report> rep = service.viewAllReports(date);
			return new ResponseEntity<List<Report>>(rep,new HttpHeaders(),HttpStatus.OK);
			
		}	
}
