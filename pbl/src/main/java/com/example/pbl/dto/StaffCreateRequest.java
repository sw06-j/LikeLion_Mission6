// StaffCreateRequest.java
package com.example.pbl.dto;

public record StaffCreateRequest(
        String name,
        String major,
        int generation,
        String part,
        String position
) {}