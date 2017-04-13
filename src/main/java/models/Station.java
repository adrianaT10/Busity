package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "STATIONS")
public class Station {
	
	@Id
	@GeneratedValue(strategy = AUTO)
	Long id;
	
	String stationName;
	
	Float latitude;
	
	Float longitude;
	
//	@ManyToOne
//	@JoinColumn(name = "LINES_LINE_NO")
//	// TODO research many to many
//	Line line;
}
