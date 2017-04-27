package repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import models.Vehicle;

public interface VehicleRepository extends BaseRepository<Vehicle, String> {
	
	@Query("SELECT v FROM Vehicle v JOIN FETCH v.logEntries WHERE v.registrationNo = :registrationNo")
    public Vehicle findByRegistrNoAndFetchLogs(@Param("registrationNo") String registrationNo);
	
}
