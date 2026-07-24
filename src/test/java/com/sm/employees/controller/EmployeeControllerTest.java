package com.sm.employees.controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.sm.employees.dto.EmployeeSummaryDTO;
import com.sm.employees.service.EmployeeService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    @DisplayName("Debe retornar lista de empleados con status 200 y JSON correcto")
    void shouldReturnEmployees() throws Exception {
        // 1. ARRANGE (Preparación de datos)
        EmployeeSummaryDTO employee1 = new EmployeeSummaryDTO(1L, "Juan Perez", "IT");
        EmployeeSummaryDTO employee2 = new EmployeeSummaryDTO(2L, "Ana Lopez", "HR");
        List<EmployeeSummaryDTO> employees = List.of(employee1, employee2);

        // Simulamos la respuesta del servicio
        when(employeeService.getAllEmployeesSummary()).thenReturn(employees);

        // 2. ACT & ASSERT (Petición HTTP y Verificaciones)
        mockMvc.perform(get("/employees"))
                // Verificar HTTP 200 OK
                .andExpect(status().isOk())
                // Verificar Content-Type application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Verificar cantidad de elementos (2)
                .andExpect(jsonPath("$.length()").value(2))
                // Verificar contenido del primer objeto JSON
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fullName").value("Juan Perez"))
                .andExpect(jsonPath("$[0].department").value("IT"))
                // Verificar contenido del segundo objeto JSON
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].fullName").value("Ana Lopez"))
                .andExpect(jsonPath("$[1].department").value("HR"));
    }
}
