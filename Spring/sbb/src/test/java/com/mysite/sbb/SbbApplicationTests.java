package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.Option;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

@SpringBootTest
class SbbApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;

	// @Transactional
	@Test
	void testJpa() {
		
		// 3-1
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content);	
		}
		
		/*
		// 1-1
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		*/
		
		/*
		// 1-2
		List<Question> all = this.questionRepository.findAll();
		assertEquals(3, all.size());
		
		Question q = all.get(0);
		assertEquals("스프링부트 모델 질문입니다.", q.getSubject());
		*/
		
		/*
		// 1-3
		Optional<Question> oq = this.questionRepository.findById(9);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb에 대해서 알고 싶습니다.", q.getContent());
		}
		*/
		
		/*
		// 1-4
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(9, q.getId());
		*/
		
		/*
		// 1-5
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?",
		"sbb에 대해서 알고 싶습니다.");
		assertEquals(9, q.getId());
		*/
		
		/*
		// 1-6
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		*/
		
		/*
		// 1-7
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목2");
		this.questionRepository.save(q);
		*/
	
		/*
		// 1-8
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
		*/
		
		// Q끝.
		
		/*
		// 2-1
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		*/
		
		/*
		// 2-2
		Optional<Answer> oa = this.answerRepository.findById(2);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(10, a.getQuestion().getId());
		*/
		
		/*
		// 2-3
		Optional<Question> oq = this.questionRepository.findById(10);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		List<Answer> answerList = q.getAnswerList();
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		*/
	}
}
