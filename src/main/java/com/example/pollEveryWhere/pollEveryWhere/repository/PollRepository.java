package com.example.pollEveryWhere.pollEveryWhere.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.example.pollEveryWhere.pollEveryWhere.model.Poll;

import antlr.collections.List;

public interface PollRepository extends Repository<Poll, Integer> {
	Poll save(Poll poll);

	Poll findById(int questionId);
	
	@Query(value="SELECT * FROM poll WHERE radio='#1'", nativeQuery = true)
	ArrayList<Poll> findPolls();
	
	// @Query(value="DELETE FROM POLL WHERE pid=?1", nativeQuery = true)
	void deleteById(int pollId);
	
	//@Qeruy("Select  ")
	 @Query(value="SELECT * FROM poll WHERE pid IN(SELECT poll_pid from  poll_track WHERE user_u_id=?1)", nativeQuery = true)
	 ArrayList<Poll> findAllAnsweredQuestion(int answeredUserId);

	@Query(value="SELECT * FROM poll WHERE  user_u_id=?1", nativeQuery = true)
	ArrayList<Poll> getAllCreatedPoll(int answeredUserId);

	//Poll findByPid(int questionId);
}
