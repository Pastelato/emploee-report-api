package com.sm.employees.service;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sm.employees.dto.EmployeeSummaryDTO;
import com.sm.employees.entity.Employee;
import com.sm.employees.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        // Preparamos datos de prueba antes de cada test
        employee1 = Employee.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .department("IT")
                .salary(2500.0)
                .city("Buenos Aires")
                .active(true)
                .build();

        employee2 = Employee.builder()
                .id(2L)
                .firstName("Ana")
                .lastName("Lopez")
                .department("HR")
                .salary(3000.0)
                .city("Córdoba")
                .active(true)
                .build();
    }

    @Test
    @DisplayName("Debe devolver una lista con empleados mapeados a DTO")
    void getAllEmployeesSummary_ShouldReturnEmployeeList() {
        // 1. ARRANGE (Preparación): Simulamos que el repositorio devuelve 2 empleados
        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2));

        // 2. ACT (Ejecución): Llamamos al método que estamos probando
        List<EmployeeSummaryDTO> result = employeeService.getAllEmployeesSummary();

        // 3. ASSERT (Verificaciones): Validamos el resultado
        assertNotNull(result, "La lista no debe ser nula");
        assertEquals(2, result.size(), "Debe retornar exactamente 2 elementos");

        // Validamos la transformación del primer empleado
        EmployeeSummaryDTO dto1 = result.get(0);
        assertEquals(1L, dto1.id());
        assertEquals("Juan Perez", dto1.fullName(), "Debe concatenar correctamente el nombre y apellido");
        assertEquals("IT", dto1.department());

        // Verificamos que el método findAll() del repository fue llamado exactamente 1 vez
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe devolver una lista vacía cuando no existen registros en la BD")
    void getAllEmployeesSummary_ShouldReturnEmptyList_WhenNoRecordsExist() {
        // 1. ARRANGE: Simulamos que la base de datos está vacía
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        // 2. ACT
        List<EmployeeSummaryDTO> result = employeeService.getAllEmployeesSummary();

        // 3. ASSERT
        assertNotNull(result, "La lista no debe ser nula");
        assertTrue(result.isEmpty(), "La lista debe estar vacía");

        // Verificamos que el repositorio fue consultado
        verify(employeeRepository, times(1)).findAll();
    }
}
