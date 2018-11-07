package com.skilltrace.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrace.model.Skill;
import com.skilltrace.service.SkillService;

@RestController
@CrossOrigin
@RequestMapping(value="/skills")
public class SkillAPI {

	@Autowired
	private SkillService skillService;
	
	@GetMapping
	public ResponseEntity<List<Skill>> getAllSkills(){
		
		ResponseEntity<List<Skill>> resp = null;
		List<Skill> skills = skillService.getAllSkills();
		if(skills!=null)
			resp = new ResponseEntity<List<Skill>>(skills, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Skill>>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@GetMapping("/{skillId}")
	public ResponseEntity<Skill> getSkillById(@PathVariable("skillId") long skillId){
		
		ResponseEntity<Skill> resp = null;
		Skill skillRequest = skillService.getSkillById(skillId);
		if(skillRequest!=null)
			resp = new ResponseEntity<Skill>(skillRequest, HttpStatus.OK);
		else
			resp = new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@GetMapping("/{field}/{srchValue}")
	public ResponseEntity<List<Skill>> getAllConnections(
			@PathVariable("field") String fieldBy,
			@PathVariable("srchValue") String searchValue){
		
		ResponseEntity<List<Skill>> resp = null;
		
		switch(fieldBy) {
			case "skillName":
				List<Skill> resultsBySkillName = skillService.findBySkillName(searchValue);
				if(resultsBySkillName!=null) {
					resp = new ResponseEntity<>(resultsBySkillName, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;
			case "score":
				List<Skill> resultsByScore = skillService.findByScore(Integer.parseInt(searchValue));
				if(resultsByScore!=null && resultsByScore.size()!=0) {
					resp = new ResponseEntity<>(resultsByScore, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}				
				break;
			case "specialization":
				List<Skill> resultsBySpecialization = skillService.findBySpecialization(searchValue);
				if(resultsBySpecialization!=null) {
					resp = new ResponseEntity<>(resultsBySpecialization, HttpStatus.OK);
				}
				else {
					resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				break;	
			default:
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	
	@PostMapping
	public ResponseEntity<Skill> addSkill(@RequestBody Skill skill){
		
		ResponseEntity<Skill> resp = null;
		if(!skillService.existsBySkillId(skill.getSkillId())) {
			resp = new ResponseEntity<Skill>(skillService.addSkill(skill), HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return resp;
	}
	
	@PutMapping
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill){
		
		ResponseEntity<Skill> resp = null;
		if(skillService.existsBySkillId(skill.getSkillId()))
			resp = new ResponseEntity<Skill>(skillService.updateSkill(skill), HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}
	
	@DeleteMapping("/{skillId}")
	public ResponseEntity<Void> deleteSkill(@PathVariable("skillId") long skillId){
		
		ResponseEntity<Void> resp = null;
		if(skillService.existsBySkillId(skillId)) {
			skillService.deleteSkillById(skillId);
			resp = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
			resp = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);		
		return resp;
	}
	
	
}
