package pl.jdomanski.k47.meter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {
	
	// == fields ==
	private final ReadingRepository readingRepository;
	
	// == constructor ==
	@Autowired
	public ReadingService( ReadingRepository readingRepository) {
		this.readingRepository = readingRepository;
	}
	
	// == methods ==
	public Iterable<Reading> findAll(){
		return readingRepository.findAll();
	}
}
