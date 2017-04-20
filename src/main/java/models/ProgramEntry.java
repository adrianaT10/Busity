package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.AUTO;

// TODO later
public class ProgramEntry {
	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	
	Line line;
	
	
}
