package repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import models.Line;
import models.Vehicle;

public interface VehicleRepository extends BaseRepository<Vehicle, String> {

	@Modifying
	@Transactional
	@Query("update Vehicle v set v.line = ?2 where v.registrationNo = ?1")
	public void updateLine(String registrationNo, Line line);
}
