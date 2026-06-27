package com.example.pbl.domain;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Major major;

    private int generation;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String studentId;

    @Enumerated(EnumType.STRING)
    private Position position;

    protected Member() {}

    public Member(String name, Major major, int generation, Part part, RoleType roleType, String studentId, Position position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleType = roleType;
        this.studentId = studentId;
        this.position = position;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Major getMajor() { return major; }
    public int getGeneration() { return generation; }
    public Part getPart() { return part; }
    public RoleType getRoleType() { return roleType; }
    public String getStudentId() { return studentId; }
    public Position getPosition() { return position; }

    public void updateInfo(Major major, int generation, Part part) {
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }
}
