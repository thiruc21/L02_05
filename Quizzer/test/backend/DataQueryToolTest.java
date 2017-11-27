package backend;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import backend.DataFillTool;
import backend.DataQueryTool;
import backend.DataSetupTool;

public class DataQueryToolTest {

	@Before
	public void setup() {
		
		DataSetupTool.initialize();
		
		String[] options1 = {"one", "two", "three", "four", "five", "six"};
		String[] options2 = {"one1", "two2", "three3", "four4", "five5", "six6"};
		String[] options3 = {"111", "222", "333", "444", "555", "666"};
		// label, title, answer options, correct answers text
		DataFillTool.insert("Q1", "answer is one", options1, "one");
		DataFillTool.insert("Q2", "answer is two", options2, "two2");
		DataFillTool.insert("Q3", "answer is three", options3, "404"); //answer does not exist
		
	}
	
	@Test
	public void QuestionQueryTest() {
		
		int result = DataQueryTool.question_query("Q1");
		//since Q1 is inserted first, it's ID is 1
		assertEquals("Question Queried correctly",result,1);
		
	}
	
	@Test
	public void answerQueryOneTest() {
		
		int result = DataQueryTool.answer_query("one", 1);
		
		//System.out.print(result);
		//first answer is always void, so the second inserted answer has ID 2.
		assertEquals("correct answer queried", result, 2);
	}
	
	@Test
	public void answerQueryTwoTest() {
		
		int result = DataQueryTool.answer_query("two2", 2);
		
		//System.out.print(result);
		//since the first answer is void, Q1 has 6 answers, and Q2's correct answer is the second one, the correct answer of Q2 should be 9 = 1 + 6 + 2
		assertEquals("correct answer queried", result, 9);
	}
	
	@Test
	public void StartUnassignedTest() {
		
		List<String> result_1 = DataQueryTool.get_unassigned(1);
		List<String> result_2 = DataQueryTool.get_unassigned(2);
		
		//System.out.println(result_1);
		//System.out.println(result_2);
		
		//since assignments start out empty, then for both assignments, the unassigned questions to each are the same
		assertEquals("All questions start unassigned", result_1, result_2 );
	}
	
	@Test
	public void StartAssignedTest() {
		
		List<String> result_1 = DataQueryTool.get_assigned(1);
		List<String> result_2 = DataQueryTool.get_assigned(2);
		
		//since assignments start out empty, then when we ask what questions are assigned, there should be none for both
		assertTrue("All assignments start empty", result_1.isEmpty() && result_2.isEmpty());
		
	}
	
	@Test
	public void AssignmentTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		DataFillTool.addAssignedQuestion(1, Integer.toString(q1_id));
		
		List<String> result = DataQueryTool.get_assigned(1);
		
		//System.out.println(result);
		
		//When a question is assigned, it appears in the list of questions that are assigned.
		assertTrue("Questions are assigned correctly",result.contains(Integer.toString(q1_id)));
		
	}
	
	@Test
	public void MultipleAssignmentTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		int q2_id = DataQueryTool.question_query("Q2");
		
		DataFillTool.addAssignedQuestion(1, Integer.toString(q1_id));
		
		DataFillTool.addAssignedQuestion(1, Integer.toString(q2_id));
		
		List<String> result = DataQueryTool.get_assigned(1);
		
		//System.out.println(result);
		
		//When two questions are assigned to an assignment, they both appear in the list of questions assigned.
		assertTrue("Multiple Questions are assigned correctly",result.contains(Integer.toString(q1_id))&&result.contains(Integer.toString(q2_id)));
		
	}
	
	@Test
	public void QuestionTextOneTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		//question_query returns the correct question text for Q1
		assertEquals("Question Text properly queried",DataQueryTool.question_text_query(q1_id),"answer is one");
		
	}
	
	@Test
	public void QuestionTextTwoTest() {
		
		int q2_id = DataQueryTool.question_query("Q2");
		
		//question_query returns the correct question text for Q2
		assertEquals("Question Text properly queried",DataQueryTool.question_text_query(q2_id),"answer is two");
		
	}
	
	@Test
	public void QuestionAnswersQueries() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		List<Integer> result = DataQueryTool.get_question_answer_ids(q1_id);
		
		List<Integer> expected_result = new ArrayList<Integer>();
		expected_result = Arrays.asList(2, 3, 4, 5, 6, 7);
		
		//System.out.println(result);
		
		//since first answer is always void, the IDs for the first 6 answers are 2-7
		assertEquals("All answer IDs are properly returned",result, expected_result);
		
	}
	
	@Test
	public void GetAnswerTextTest() {
		
		try {
			
			int q1_id = DataQueryTool.question_query("Q1");
			
			List<Integer> result = DataQueryTool.get_question_answer_ids(q1_id);
			
			//System.out.println(result);
			
			//get_question_answer_ids returns the text of all answers for Q1.
			assertEquals("Answer 1 text properly queried",DataQueryTool.get_answer_text(result.get(0)),"one");
			assertEquals("Answer 2 text properly queried",DataQueryTool.get_answer_text(result.get(1)),"two");
			assertEquals("Answer 3 text properly queried",DataQueryTool.get_answer_text(result.get(2)),"three");
			assertEquals("Answer 4 text properly queried",DataQueryTool.get_answer_text(result.get(3)),"four");
			
		} catch (Exception e) {
			fail("unexpected error occurred when querying answer texts");
		}
	}
	
	@Test
	public void GetCorrectAnswerIDTest() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		
		//System.out.println(DataQueryTool.get_correct_answer_id(q1_id));
		
		int result = DataQueryTool.get_correct_answer_id(q1_id);
		//The correct answer for Q1 is it's first answer. Since the first answer is always void, it's ID is 2.
		assertEquals("Correct answer ID is correct",result, 2);
		
	}
	
	@Test
	public void AnsweridExists() {
		
		int q1_id = DataQueryTool.question_query("Q1");
		int ans_id = DataQueryTool.get_correct_answer_id(q1_id);
		List<Integer> possible_answer_ids = DataQueryTool.get_question_answer_ids(q1_id);
		//the correct answer's id is in fact one of the possible answers
		assertTrue("The correct answer is in fact one of the answers",possible_answer_ids.contains(ans_id));
	}
	
}
