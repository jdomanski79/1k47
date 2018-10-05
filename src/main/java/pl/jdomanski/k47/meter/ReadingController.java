package pl.jdomanski.k47.meter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import pl.jdomanski.k47.util.Mappings;
import pl.jdomanski.k47.util.ViewNames;

@Controller
@Slf4j
public class ReadingController {
	
	// == fields ==
	private final ReadingService readingService;
	
	// == constructors ==
	@Autowired
	public ReadingController(ReadingService readingService) {
		this.readingService = readingService;
	}
	
	// == methods ==
	@GetMapping(Mappings.READING_LIST)
	public String readingList(Model model) {
		model.addAttribute("readings", readingService.findAll());
		return ViewNames.READINGS_LIST;
	}
}
