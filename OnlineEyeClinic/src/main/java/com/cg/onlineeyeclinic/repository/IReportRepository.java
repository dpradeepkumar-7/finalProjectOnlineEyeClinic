package com.cg.onlineeyeclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlineeyeclinic.model.Report;



/** IReportRepository interface provides abstract methods to perform CRUD operations on report entity, view Reports.
 * 
 * @author D,Pradeep Kumar
 * 
 */

@Repository(value="reportRepository")
public interface IReportRepository extends JpaRepository<Report, Integer>{
	
	
	@Query("select rep from Report rep where rep.dateOfReport like ?1")
	List<Report> viewAllReports(LocalDate date);



}
