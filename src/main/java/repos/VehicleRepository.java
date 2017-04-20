package repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import models.Line;
import models.Vehicle;

public interface VehicleRepository extends BaseRepository<Vehicle, String> {

	@Modifying
	@Transactional
	@Query("update Vehicle v set v.line = ?2 where v.registrationNo = ?1")
	public void updateLine(String registrationNo, Line line);
	
	@Query("SELECT v FROM Vehicle v JOIN FETCH v.logEntries WHERE v.registrationNo = :registrationNo")
    public Vehicle findByRegistrNoAndFetchLogs(@Param("registrationNo") String registrationNo);
}
