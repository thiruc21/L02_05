package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

public class Create_mc {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create_mc window = new Create_mc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Create_mc() {
		initialize();
	}
	
	public void mc_to_db(String title, String question, String[] answers, int answer) throws IndexOutOfBoundsException {
		
		if(answer > answers.length) {
			
			throw new IndexOutOfBoundsException();
			
		}
		
		//Here is where some Database stuff will be done... IDK
		
		backend.DataFillTool.insert(title, question, answers, answer);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(119, 136, 153));
		frame.setBounds(100, 100, 487, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreate = new JButton("Create Question");
	
		btnCreate.setBackground(new Color(70, 130, 180));
		btnCreate.setForeground(new Color(128, 0, 0));
		btnCreate.setBounds(81, 321, 133, 50);
		frame.getContentPane().add(btnCreate);
		
		JRadioButton rdbtnA2 = new JRadioButton("Answer 2");
		rdbtnA2.setBounds(81, 138, 312, 23);
		frame.getContentPane().add(rdbtnA2);
		
		JRadioButton rdbtnA1 = new JRadioButton("Answer 1");
		rdbtnA1.setBounds(81, 102, 312, 23);
		frame.getContentPane().add(rdbtnA1);
		
		JRadioButton rdbtnA3 = new JRadioButton("Answer 3");
		rdbtnA3.setBounds(81, 170, 312, 23);
		frame.getContentPane().add(rdbtnA3);
		
		JRadioButton rdbtnA4 = new JRadioButton("Answer 4");
		rdbtnA4.setBounds(81, 206, 312, 23);
		frame.getContentPane().add(rdbtnA4);
		
		JRadioButton rdbtnA5 = new JRadioButton("Answer 5");
		rdbtnA5.setBounds(81, 242, 312, 23);
		frame.getContentPane().add(rdbtnA5);
		
		JRadioButton rdbtnA6 = new JRadioButton("Answer 6");
		rdbtnA6.setBounds(81, 277, 312, 23);
		frame.getContentPane().add(rdbtnA6);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setVerticalAlignment(SwingConstants.TOP);
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuestion.setForeground(new Color(0, 255, 0));
		lblQuestion.setBounds(81, 11, 350, 84);
		frame.getContentPane().add(lblQuestion);
		
		JLabel lblAnswer = new JLabel("Answer: ");
		lblAnswer.setForeground(new Color(0, 255, 0));
		lblAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnswer.setBounds(224, 321, 237, 50);
		frame.getContentPane().add(lblAnswer);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = new String[6];
				boolean validAnswer = false;
				String question = "";
				while (!validAnswer) {
					question = JOptionPane.showInputDialog(null, "What is the question?", "Multiple Choice Question Creation (2-6 options)", JOptionPane.INFORMATION_MESSAGE);
					if (question == null) {
						return;
					}
					if (question.isEmpty()) {
						JOptionPane.showMessageDialog(new JLabel(), "Question is mandatory.", "Question needed", JOptionPane.INFORMATION_MESSAGE);
						validAnswer = false;
						
					} else {
						validAnswer = true;
					}
				}
				validAnswer = false;
				while (!validAnswer) {
					options[0] = JOptionPane.showInputDialog(null, "What is the first option?", "Option 1", JOptionPane.INFORMATION_MESSAGE);
					if (options[0] == null) {
						return;
					}
					if (options[0].isEmpty()) {
						JOptionPane.showMessageDialog(new JLabel(), "First option is mandatory.", "First option needed", JOptionPane.INFORMATION_MESSAGE);
						validAnswer = false;
						
					} else {
						validAnswer = true;
					}
				}
				validAnswer = false;
				while (!validAnswer) {
				options[1] = JOptionPane.showInputDialog(null, "What is the second option?", "Option 2", JOptionPane.INFORMATION_MESSAGE);
				if (options[1] == null) {
					return;
				}
				if (options[1].isEmpty()) {
					JOptionPane.showMessageDialog(new JLabel(), "Second option is mandatory.", "Second option needed", JOptionPane.INFORMATION_MESSAGE);
					validAnswer = false;
					
				} else {
					validAnswer = true;
				}	
				}
				validAnswer = false;
				options[2] = JOptionPane.showInputDialog(null, "What is the third option? (optional)", "Option 3", JOptionPane.INFORMATION_MESSAGE);
				if (options[2] == null) {
					return;
				}
				options[3] = JOptionPane.showInputDialog(null, "What is the fourth option? (optional)", "Option 4", JOptionPane.INFORMATION_MESSAGE);
				if (options[3] == null) {
					return;
				}
				options[4] = JOptionPane.showInputDialog(null, "What is the fifth option? (optional)", "Option 5", JOptionPane.INFORMATION_MESSAGE);
				if (options[4] == null) {
					return;
				}
				options[5] = JOptionPane.showInputDialog(null, "What is the sixth option? (optional)", "Option 6", JOptionPane.INFORMATION_MESSAGE);
				if (options[5] == null) {
					return;
				}
				while (!validAnswer) {
					String answer = JOptionPane.showInputDialog(null, "What is the answer? Pick a number 1-6 as below.\n 1:" + options[0] + "\n 2:" + options[1] + "\n 3:" + options[2] + "\n 4:" + options[3] + "\n 5:" + options[4] + "\n 6:" + options[5], "Pick an answer", JOptionPane.INFORMATION_MESSAGE);
					if (answer == null) {
						return;
					}
					try {
					int answer_index = Integer.parseInt(answer);
					if ((answer_index < 1) || (answer_index > 6)) {
						throw new NumberFormatException();
					}
					validAnswer = true;
					lblQuestion.setText("<html><body style='width: 133px'>"+question);
					lblAnswer.setText("<html><body style='width: 237px'>Answer: "+options[answer_index-1]);
					rdbtnA1.setText(options[0]);
					rdbtnA2.setText(options[1]);
					rdbtnA3.setText(options[2]);
					rdbtnA4.setText(options[3]);
					rdbtnA5.setText(options[4]);
					rdbtnA6.setText(options[5]);
					// Call function to insert into database using back-end here 
					
					mc_to_db("TITLE GOES HERE", question, options, answer_index);
					
					JOptionPane.showMessageDialog(new JLabel(), "Question was successfully created", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(new JLabel(), "Please input a number between 1-6", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
						
					}
					
				}
			
				
			}
		});
	}
}
