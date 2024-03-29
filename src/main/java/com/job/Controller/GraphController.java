package com.job.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.job.Model.Players;
import com.job.Model.Teams;
import com.job.Service.GraphService;

@Controller
public class GraphController {

	@Autowired
	private GraphService graphService;
	
	@QueryMapping
	public ResponseEntity<List<Players>> findAll(){
		return new ResponseEntity<>(graphService.findAll(),HttpStatus.OK);
	}
	@QueryMapping
	public Optional<Players> findOne(@Argument int id) {
		return graphService.findOne(id);
	}
	
	@MutationMapping
	public void addPlayer(@Argument String name, @Argument Teams team) {
		graphService.addPlayer(name, team);
	}
	
	@MutationMapping
	public void deletePlayer(@Argument int id) {
		graphService.deletePlayer(id);
	}
	
	@MutationMapping
	public Players updatePlayer(@Argument int id, @Argument String name, @Argument Teams team) {
		return graphService.updatePlayer(id, name, team);
	}
}
