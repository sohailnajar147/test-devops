package tn.esprit.studentmanagement.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    private IDepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDepartment() {
        // Arrange
        Department dept1 = new Department();
        dept1.setId(1L);
        dept1.setName("Computer Science");

        Department dept2 = new Department();
        dept2.setId(2L);
        dept2.setName("Mathematics");

        List<Department> expectedDepartments = Arrays.asList(dept1, dept2);
        when(departmentService.getAllDepartments()).thenReturn(expectedDepartments);

        // Act
        List<Department> result = departmentController.getAllDepartment();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Computer Science", result.get(0).getName());
        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    void testGetDepartmentById() {
        // Arrange
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);
        department.setName("Engineering");

        when(departmentService.getDepartmentById(departmentId)).thenReturn(department);

        // Act
        Department result = departmentController.getDepartment(departmentId);

        // Assert
        assertNotNull(result);
        assertEquals(departmentId, result.getId());
        assertEquals("Engineering", result.getName());
        verify(departmentService, times(1)).getDepartmentById(departmentId);
    }

    @Test
    void testCreateDepartment() {
        // Arrange
        Department newDepartment = new Department();
        newDepartment.setName("Physics");

        Department savedDepartment = new Department();
        savedDepartment.setId(1L);
        savedDepartment.setName("Physics");

        when(departmentService.saveDepartment(newDepartment)).thenReturn(savedDepartment);

        // Act
        Department result = departmentController.createDepartment(newDepartment);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Physics", result.getName());
        verify(departmentService, times(1)).saveDepartment(newDepartment);
    }

    @Test
    void testUpdateDepartment() {
        // Arrange
        Department existingDepartment = new Department();
        existingDepartment.setId(1L);
        existingDepartment.setName("Updated Department");

        when(departmentService.saveDepartment(existingDepartment)).thenReturn(existingDepartment);

        // Act
        Department result = departmentController.updateDepartment(existingDepartment);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Department", result.getName());
        verify(departmentService, times(1)).saveDepartment(existingDepartment);
    }

    @Test
    void testDeleteDepartment() {
        // Arrange
        Long departmentId = 1L;
        doNothing().when(departmentService).deleteDepartment(departmentId);

        // Act
        departmentController.deleteDepartment(departmentId);

        // Assert
        verify(departmentService, times(1)).deleteDepartment(departmentId);
    }
}
