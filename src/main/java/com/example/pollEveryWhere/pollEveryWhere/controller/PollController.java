package com.example.pollEveryWhere.pollEveryWhere.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.pollEveryWhere.pollEveryWhere.Bean.PollResponseBean;
import com.example.pollEveryWhere.pollEveryWhere.Bean.ResponseBean;
import com.example.pollEveryWhere.pollEveryWhere.model.Poll;
import com.example.pollEveryWhere.pollEveryWhere.model.PollTrack;
import com.example.pollEveryWhere.pollEveryWhere.model.User;
import com.example.pollEveryWhere.pollEveryWhere.repository.PollRepository;
import com.example.pollEveryWhere.pollEveryWhere.service.PollService;
import com.example.pollEveryWhere.pollEveryWhere.service.PollTrackService;
import com.example.pollEveryWhere.pollEveryWhere.service.UserService;

import antlr.collections.List;

@CrossOrigin(origins = { "http://localhost:3000","https://pollvoicefrontend.herokuapp.com"})
@RestController
public class PollController {
	
	@Autowired
	PollService pollService;
	@Autowired
	UserService userService;
	@Autowired
	PollTrackService pollTrackService;

	
	@PostMapping("/postQuestion")
	public Poll saveQuestion(@RequestBody Poll poll) {
		System.out.println(poll.toString());
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userService.findUserByEmail(email);
		user.setPassword("");
		poll.setUser(user);
		poll.setNoPercent("0%");
		poll.setYesPercent("0%");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateString=String.valueOf(date);
		poll.setDate(dateString);
		return pollService.savePoll(poll);
	}
	

	@GetMapping("/postQuestion/{pollId}/getOne")
	public Poll getOnePoll(@PathVariable int pollId) {
		System.out.println(pollId);
		Poll poll=pollService.findById(pollId);
		if(poll==null) {
			return null;
		}
		return poll;
	}
	
	
	@GetMapping("/getAll")
	public ArrayList getAll() {
		ArrayList<Poll> pollList=pollService.getAll();
		return pollList;
	}
	
	@GetMapping("/answeredQuestion/getAll")
	public ArrayList<Poll> getAllAnsweredPoll() {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userService.findUserByEmail(email);
		ArrayList<Poll> pollList=pollService.getAllAnswered(user.getuId());
		return pollList;
	}
	
	@GetMapping("/createdQuestion/getAll")
	public ArrayList<Poll> getAllCreatedPoll() {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userService.findUserByEmail(email);
		ArrayList<Poll> pollList=pollService.getAllCreatedPoll(user.getuId());
		return pollList;
	}
	
	@PostMapping("/updateSecret")
	public Poll updateSecret(@RequestHeader("pollId") String pollId,@RequestHeader("secret") String secret) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userService.findUserByEmail(email);
		//ArrayList<Poll> pollList=pollService.getAllCreatedPoll(user.getuId());
		Poll poll=pollService.findById(Integer.parseInt(pollId));
		poll.setSecret(secret);
		// ;
		 return pollService.savePoll(poll);
	}
	
	@PostMapping("/answered/delete")
	public String answeredDelete(@RequestHeader("pollId") int pollId) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userService.findUserByEmail(email);
		pollTrackService.answeredDelete(pollId,user.getuId());
		return "deleted";
	}
	
	
	@PostMapping("/postQuestion/delete")
	public String deletePoll(@RequestHeader("pollId") String pollId) {
		System.out.println("pollId="+pollId);
		Poll poll=pollService.findById(Integer.parseInt(pollId));
		if(poll==null) {
			return "Question No Found";
		}else {
			pollTrackService.deleteByQuestion(poll);
			pollService.deleteById(Integer.parseInt(pollId));
				return "deleted";
			}
		
		//return "Something went wrong";
	}
	
	@PostMapping("/postQuestion/updateYes")
	public ResponseBean updateYes(@RequestBody PollResponseBean pollResponseBean) {
		 String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userService.findUserByEmail(email);
		Poll pollForTrack=new Poll();
		User userForTrack=new User();
		userForTrack.setuId(user.getuId());
		pollForTrack.setPid(pollResponseBean.getQuestionId());
		
		ResponseBean responseBean=new ResponseBean();
		
		PollTrack checkPrevious=pollTrackService.findIfAnswered(userForTrack,pollForTrack);
		Poll poll;
		if(checkPrevious!=null) {
			poll=pollService.findById(pollResponseBean.getQuestionId());
			responseBean=new ResponseBean();
			responseBean.setMsg("alreadyVoted");
			responseBean.setYes(poll.getYesPercent());
		}else {
			 poll=pollService.findByIdAndUpdate(pollResponseBean.getQuestionId(),pollResponseBean.getResponse());
			PollTrack pollTrack=new PollTrack(userForTrack,pollForTrack);
			pollTrackService.saveTrack(pollTrack);
			//responseBean=calculatePercentage(poll);
			responseBean.setMsg("Response Noted");
		}
		responseBean.setYes(poll.getYesPercent());
		responseBean.setNo(poll.getNoPercent());
		responseBean.setTotalResponse(String.valueOf(poll.getYes()+poll.getNo()));
		return responseBean;

	}
	
//	 public ResponseBean calculatePercentage(Poll poll) {
//		 int yes=poll.getYes();
//		 int no=poll.getNo();
//		 int total= (yes+no);
//		 int yesPercentage=(yes/total)*100;
//		 int noPercentage=(no/total)*100;
//         ResponseBean responseBean=new ResponseBean();
//         responseBean.setNo(noPercentage);
//         responseBean.setYes(yesPercentage);
//         responseBean.setTotalResponse(total);
//		return responseBean;
//		 
//	 }
	
}
