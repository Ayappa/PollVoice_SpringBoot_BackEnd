package com.example.pollEveryWhere.pollEveryWhere.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pollEveryWhere.pollEveryWhere.model.Poll;
import com.example.pollEveryWhere.pollEveryWhere.model.User;
import com.example.pollEveryWhere.pollEveryWhere.repository.PollRepository;

import antlr.collections.List;

@Service
public class PollService {
	@Autowired
	private PollRepository pollRepository;
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("Poll");

	public Poll savePoll(Poll poll) {
		System.out.println("in Poll service");
		// TODO Auto-generated method stub
		return pollRepository.save(poll);	
	}


	public Poll findById(int questionId) {
		// TODO Auto-generated method stub
		return pollRepository.findById(questionId);
	}


	public Poll findByIdAndUpdate(int questionId, int response) {
		
		Poll poll=pollRepository.findById(questionId);
		if(response>0) {
			poll.setYes(poll.getYes()+1);
		}else {
			poll.setNo(poll.getNo()+1);
		}
		 int yes=poll.getYes();
		 int no=poll.getNo();
		 Double total=(double) (yes+no);
		 int yesPercentage=(int) ((yes/total)*100);
		 int noPercentage=(int) ((no/total)*100);
		 poll.setNoPercent(String.valueOf(noPercentage)+"%");
		 poll.setYesPercent(String.valueOf(yesPercentage)+"%");
		// TODO Auto-generated method stub
		return pollRepository.save(poll);
	}


	public ArrayList<Poll> getAll() {
		return pollRepository.findPolls();
	}


	public ArrayList<Poll> getAllAnswered(int answeredUserId) {
		// TODO Auto-generated method stub
//		EntityManager entityManager=emf.createEntityManager();
//		entityManager.getTransaction().begin();
//		java.util.List results=entityManager.createQuery("SELECT * FROM POLL WHERE pid IN(SELECT poll_pid from  POLL_TRACK WHERE user_u_id=:ID)").getResultList();
//		entityManager.getTransaction().commit();
//		entityManager.close();
		
				return pollRepository.findAllAnsweredQuestion(answeredUserId);
	}
	
	public ArrayList<Poll> getAllCreatedPoll(int answeredUserId) {
		// TODO Auto-generated method stub
//		EntityManager entityManager=emf.createEntityManager();
//		entityManager.getTransaction().begin();
//		java.util.List results=entityManager.createQuery("SELECT * FROM POLL WHERE pid IN(SELECT poll_pid from  POLL_TRACK WHERE user_u_id=:ID)").getResultList();
//		entityManager.getTransaction().commit();
//		entityManager.close();
		
				return pollRepository.getAllCreatedPoll(answeredUserId);
	}


	public void deleteById(int pollId) {
		// TODO Auto-generated method stub
		 pollRepository.deleteById(pollId);
	}


	


	
}
