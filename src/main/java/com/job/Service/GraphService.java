package com.job.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.job.Model.Players;
import com.job.Model.Teams;

import jakarta.annotation.PostConstruct;

@Service
public class GraphService {
	
	List<Players> playersList = new ArrayList<>();
	
	AtomicInteger id = new AtomicInteger(0);
	
	public List<Players> findAll(){
		return playersList;
		
	}
	
	public Optional<Players> findOne(int iD){
		return playersList.stream().filter(player->player.id()==iD).findFirst();
		
	}
	public void addPlayer(String name, Teams team) {
		Players player = new Players(id.incrementAndGet(),name,team);
		playersList.add(player);
		
	}
	
	public void deletePlayer(int id) {
		Players player= playersList.stream().filter(c->c.id()==id).findFirst().orElseThrow(()->new IllegalArgumentException());
		playersList.remove(player);
	}
	
	public Players updatePlayer(int id, String name, Teams team) {
		Players updatedPlayer = new Players(id,name,team);
		Optional<Players> player = playersList.stream().filter(c->c.id()==id).findFirst();
		if(player.isPresent()) {
			Players p = player.get();
			int index = playersList.indexOf(p);
			playersList.set(index, updatedPlayer);
		}
		else {
			throw new IllegalArgumentException();
		}
		return updatedPlayer;
		
	}
	
	
@PostConstruct
public void init() {
	playersList.add(new Players(id.incrementAndGet(),"venkat",Teams.CSK));
	playersList.add(new Players(id.incrementAndGet(),"venky",Teams.MI));
	playersList.add(new Players(id.incrementAndGet(),"Nivas",Teams.DC));
	playersList.add(new Players(id.incrementAndGet(),"Ravi",Teams.CSK));
	playersList.add(new Players(id.incrementAndGet(),"Jaggu",Teams.RCB));
}
	

}
