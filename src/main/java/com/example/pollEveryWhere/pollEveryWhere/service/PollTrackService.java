package com.example.pollEveryWhere.pollEveryWhere.service;

import javax.management.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pollEveryWhere.pollEveryWhere.model.Poll;
import com.example.pollEveryWhere.pollEveryWhere.model.PollTrack;
import com.example.pollEveryWhere.pollEveryWhere.model.User;
import com.example.pollEveryWhere.pollEveryWhere.repository.PollTrackRepository;

@Service
public class PollTrackService {
	@Autowired
	PollTrackRepository pollTrackRepository;

	public void saveTrack(PollTrack pollTrack) {
		// TODO Auto-generated method stub
		pollTrackRepository.save(pollTrack);
	}

	public PollTrack findIfAnswered(User user,Poll poll) {
		// TODO Auto-generated method stub
	//	System.out.println("inside findif ansdweeed"+pollTrackRepository.findByUserAndPoll(user,poll));
		return pollTrackRepository.findByUserAndPoll(user,poll);
	}
	@Transactional
	public void deleteByQuestion(Poll poll) {
		// TODO Auto-generated method stub
		 pollTrackRepository.deleteByPoll(poll);
	}

	@Transactional
	public void answeredDelete(int pollId, int uId) {
		// TODO Auto-generated method stub
		  pollTrackRepository.deleteByPollAndUser(pollId,uId);
	}

	

	
	
}
