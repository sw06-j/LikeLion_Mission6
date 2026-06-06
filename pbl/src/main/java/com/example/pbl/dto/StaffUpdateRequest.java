// StaffUpdateRequest.java
package com.example.pbl.dto;

public record StaffUpdateRequest(
        String major,
        int generation,
        String part,
        String position
) {}