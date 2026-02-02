package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setIdStudent(1L);
        student.setFirstName("John Doe");

        Mockito.when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);

        Assertions.assertNotNull(savedStudent);
        Assertions.assertEquals("John Doe", savedStudent.getFirstName());
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setIdStudent(1L);
        student.setFirstName("Jane Doe");

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(1L);

        Assertions.assertNotNull(foundStudent);
        Assertions.assertEquals(1L, foundStudent.getIdStudent());
        Mockito.verify(studentRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        List<Student> foundStudents = studentService.getAllStudents();

        Assertions.assertEquals(2, foundStudents.size());
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
    }
}
