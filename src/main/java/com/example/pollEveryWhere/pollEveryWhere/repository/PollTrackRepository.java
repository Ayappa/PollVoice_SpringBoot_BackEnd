package com.example.pollEveryWhere.pollEveryWhere.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.example.pollEveryWhere.pollEveryWhere.model.Poll;
import com.example.pollEveryWhere.pollEveryWhere.model.PollTrack;
import com.example.pollEveryWhere.pollEveryWhere.model.User;

public interface PollTrackRepository extends Repository<PollTrack,Integer>  {

	
	void save(PollTrack pollTrack);

	PollTrack findByUserAndPoll(User user, Poll poll);
	
	 //@Query(value="DELETE FROM poll_track WHERE poll_pid=?1", nativeQuery = true)
//	@Transactional
	
	void deleteByPoll(Poll poll);
	
	@Modifying
	@Query(value="Delete from poll_track WHERE poll_pid=?1 and user_u_id=?2", nativeQuery = true)
	void deleteByPollAndUser(int pollId, int uId);
	
	

}
